package cn.tedu.store.service.imp;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.UserService;
import cn.tedu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Version: 2021年03月20日 星期六  12:05:20
 * @Author: 程Sir
 * @Description: 该类标识员工业务层的处理类
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Override
    public List<User> findUser() {
        List<User> list = userMapper.selectAll();
        return list;
    }

    @Override
    public User findUserByUid(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        System.out.println("查询到的用户是："+user);
        if (user == null){
            throw new UserNotFoundException("查询的用户不存在！");
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        //创建示例对象，example用于添加条件，相当where后面的部分
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        /*
         等效：Select * from t_user where username = #{username}
         这里的property表示实体类里的属性。
         crireria中还可以继续添加多个属性进行查询的。
         */
        criteria.andEqualTo("username",username);
        User user = userMapper.selectOneByExample(example);
        System.out.println("查询到的User是：" +user);
        return user;
    }

    @Override
    public void register(User user) {
        // 注册之前需要检查用户是否存在
        User result = findUserByUsername(user.getUsername());
        // 如果查到了用户信息，说明用户已经存在
        if(result != null){
            throw new UsernameDuplicationException("用户已存在！");
        }
        // 程序能执行下来，说明用户信息数据库中不存在，可以执行注册
        /*
            注册时，需要注意：用户信息中的密码，是私密信息，应该是密文保存在数据库中，
            所以应该是对用户输入的明文密码进行加密，使用的加密算法是：MD5摘要算法
         */
        // 获取用户提交信息中的明文密码
        String password = user.getPassword();
        // 生成加密时需要的盐值salt
        String salt = UUID.randomUUID().toString();
        // 使用MD5摘要算法进行加密，加密格式：循环3次，salt + password +salt
        String md5Password = getMD5Password(salt,password);

        // 将生成的盐值和加密之后的密文放入用户信息中
        user.setSalt(salt);
        user.setPassword(md5Password);
        // 将该用户标注为 0 ，也就是非限定用户
        user.setIsDelete(0);
        // 设置四个必要的日志信息
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        // 执行插入
        Integer row = userMapper.insert(user);
        // 如果返回受影响的行数不为 1，注册时失败的
        if(row != 1){
            throw new InsertException("注册失败！注册时发生未知异常，稍后请重试。");
        }
    }

    @Override
    public User login(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null){
            throw new UserNotFoundException("查询的用户不存在！");
        }

        //程序能走下来，证明用户一定是存在的
        // 用户存在，需要判断用户是否被标注了: isDelete为1，表示用户被标注限制
        if(user.getIsDelete() == 1){
            throw new UserNotFoundException("查询的用户不存在！");
        }
        //程序能走下来，证明用户一定是没有被标记的
        /*
        用户名正确，密码因为在数据库中是密文保存的，所有是需要对传入的密码进行相同加密技术加密后，
        再进行密码的比对（比对的是密文）
         */
        // 加密方式：MD5摘要加密技术
        String md5Password = getMD5Password(user.getSalt(),password);

        if(!user.getPassword().equals(md5Password)){
            System.out.println("密码不正确");
            throw new PasswordMisMatchException("用户登录失败，密码错误！");
        }
        /*
            程序能够执行下来，说明密码也是正确的。
            此时需要返回当前用户的信息，需要注意的是，我们只需要返回一个迷你型的User，
            其中只需要装入后续操作中我们需要用的到的属性值即可，不必全部返回。因为该User是存在
            session中的，会影响服务的存储压力。
         */
        User result = new User();
        result.setAvatar(user.getAvatar());       // 头像
        result.setUid(user.getUid());             // 用户id
        result.setUsername(user.getUsername());   // 用户名
        System.out.println("result"+result);
        return result;
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User user = findUserByUid(uid);
        System.out.println(user);
        user.setAvatar(avatar);
        // 设置四个必要的日志信息
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        // 执行数据的修改
        userMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public void changePassword(Integer uid, String odlPassword, String newPassword) {
        // 先判断uid是否为null
        if(uid == null){
            // 如果为null，一般就是用户未登录，或者session过时，没拿到值
            throw new RuntimeException("用户未登录，请先登录后再试！");
        }
        //  再通过uid找到当前用户
        User user = findUserByUid(uid);
        // 如果用户不存在，
        if(user == null){
            throw new UserNotFoundException("用户不存在，请先注册！");
        }
        // 对传入的明文旧密码进行加密
        String password = getMD5Password(user.getSalt(), odlPassword);
        // 将加密后的旧密码和数据库中的密码进行比对
        if(!user.getPassword().equals(password)){
            throw new PasswordMisMatchException("修改失败！密码不正确");
        }
        // 程序能够执行下来，说明旧密码是正确的，通过校验，那对明文新密码进行加密
        newPassword = getMD5Password(user.getSalt(), newPassword);

        // 执行密码更新
        user = new User();
        user.setUid(uid);
        user.setPassword(newPassword);
        updateUserInfo(user);

    }

    @Override
    public void updateUserInfo(User user) {
        int row = userMapper.updateByPrimaryKeySelective(user);
        if(row != 1){
            throw new UpdateException("更新失败！更新数据过程中发生未知错误，稍后重试！");
        }
    }

    /**
     * 密码加密
     * @param salt 密码加密的盐值
     * @param password 用户密码
     * @return 返回加密之后的密码
     */
    public String getMD5Password(String salt, String password){
        /*
        加密方式：MD5摘要算法加密
        加密规则：循环3次，satl + password + salt
         */
        String MD5Pwd = password;
        for (int i = 0; i < 3; i++) {
            MD5Pwd = DigestUtils.md5DigestAsHex((salt + MD5Pwd + salt).getBytes());
        }

        System.out.println("\t 数据库中盐值："+salt);
        System.out.println("\t 加密后的密码："+MD5Pwd);
        return MD5Pwd;
    }

}

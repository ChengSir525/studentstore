package cn.tedu.store.controller;

import cn.tedu.store.comment.BaseResult;
import cn.tedu.store.comment.JsonResult;
import cn.tedu.store.controller.ex.*;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Version: 2021年03月20日 星期六  10:51:39
 * @Author: 程Sir
 * @Description: 这个是商城的员工控制层，主要实现数据的接受和请求的分配
 */
@RestController// 该注解标识前端发送的字符串或者json格式的数据由该控制器处理
@JsonInclude(value = JsonInclude.Include.NON_NULL)// 该注解标识 去掉响应给客户端json数据中的null值
@RequestMapping("/user")//该注解标识具体是以什么名称开始的才被此类处理
public class UserController extends BaseResult {
    @Autowired
    private UserService userService;

    // 存放图片格式的集合
    public static final List<String>  AVATAR_TYPE = new ArrayList<>();
    {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/gif");
    }
    // http://localhost:8080/user/avatar/change
    @RequestMapping(value = "/avatar/change", method = RequestMethod.POST)
    public JsonResult<String> chageAvatar(@RequestParam("file")MultipartFile file, HttpSession session){
        // 判断文件的类型是否是 图片格式：image
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("文件格式不正确！");
        }
        // 判断文件大小是否为 空
        if(file.isEmpty()){
            throw new FileIsEmptyException("文件大小不能为0！");
        }
        // 设置图片的最大值为 2M
        int avatarMax = 1 * 1024 * 1024;
        // 判断文件大小是否超过最大值限制
        if(file.getSize() > avatarMax){
    //读取绝对路径前缀
            throw new FileSizeOutOfBoundsException("文件大小超过了限制，上传文件的最大 "+avatarMax+"M !");
        }
        // 程序能执行下来，说明大小和类型都是符合要求的
        /*
            保存思路：
               1.获取传入的文件名称
               2.获取最后一个 . 号的位置下标
               3.截取.号及以后的所有内容，就是后缀
               4.重新统一规范命名：UUID
               5.创建本地保存路径
               6.执行文件保存
               7.将文件的保存路径插入到数据库中
         */
        // 获取传入的文件名称
        String originalName = file.getOriginalFilename();
        // 获取最后一个 . 的下标
        int index = originalName.lastIndexOf(".");
        String suff = "";
        if(index > 0){ //说明后缀是存在的
            // 截取文件后缀
            suff = originalName.substring(index);
        }
        /*
           或者也可使用时间策略
            String filename = "" + System.currentTimeMillis() + System.nanoTime();
         */
        // 创建保存文件的名字
        String name = UUID.randomUUID().toString();
        // 保存文件的真实名称：名字+后缀
        String fileName = name + suff;
        // 获取服务器的容器相对位置
//        String dirRealPath = session.getServletContext().getRealPath("avatarUpload");
//        String dirRealPath = System.getProperty("user.dir")+File.separator+"avatarUpload";
//        String dirRealPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();

        // 获取当前项目的绝对路径  D:\01-software\IntelliJ IDEA 2020.1\Idea-workspace\tedu-studentstore-202103
        final String dirRealPath =  System.getProperty("user.dir");
        System.out.println("\t 获得到的容器路径："+dirRealPath);

        // 创建本地的存储文件夹：项目的绝对路径 + 指定文件夹
        File parent = new File(dirRealPath+"\\src\\main\\resources\\static\\avatarUpload\\");
        // 如果文件不存在就先创建
        if(!parent.exists()){
            parent.mkdirs();
        }
        // 创建上传的头像文件对象
        File dest = new File(parent,fileName);
        try {
            // 执行上传操作
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileIOException("读写文件时发生未知错误，请稍后重试！");
        } catch (IllegalStateException e){
            throw new FileStateException("文件上传失败，文件可能被删除或转移（非法状态异常）！");
        }

        // 将文件的保存路径插入到数据库中
        String avatar =  "/avatarUpload/" + fileName;
        System.out.println("\t 数据库中保存的路径是："+avatar);
        // 在session中同步更新
        session.setAttribute("avatar",  avatar);
        String username = getUsernameFromSession(session);
        Integer uid = getUidFromSession(session);
        // 执行数据库的保存
        userService.changeAvatar(uid,username,avatar);

        return new JsonResult<String>(SUCCESS, MESSAGE, avatar);
    }


    // http://localhost:8080/user/register?username=cy&password=111111
    @RequestMapping("/register")
    public JsonResult<Void> register(User user){
        System.out.println(user);
        userService.register(user);
        return new JsonResult(SUCCESS,MESSAGE);
    }

    // http://localhost:8080/user/login?username=cy&password=111111
    @RequestMapping("/login")
    public JsonResult<User> login(String username,String password, HttpSession session){
        System.out.println("登录的账号和密码："+username+", "+password);
        // 通过用户名查询用户信息是否存在,不存在的话，service抛出UserNotFoundException异常
        User user = userService.login(username, password);
        // 将查询到的用户信息存入session中
        session.setAttribute("username", user.getUsername());
        session.setAttribute("uid", user.getUid());
        session.setAttribute("avatar", user.getAvatar());
        System.out.println(user);
        return new JsonResult(SUCCESS,MESSAGE,user);
    }
    //访问路径 http://localhost:8080/user/findAll
    @RequestMapping("/findAll")
    public JsonResult<User> findAll(){
        List<User> list = userService.findUser();
        return new JsonResult(SUCCESS,MESSAGE,list);
    }
    // 访问路径 http://localhost:8080/user/findByUid?id=10
    @RequestMapping("/findByUid")
    public JsonResult<User> findById(Integer id){
        User user = userService.findUserByUid(id);
        return new JsonResult<>(SUCCESS,MESSAGE,user);
    }

    // 访问路径 http://localhost:8080/user/findByUsername?username=admin
    @RequestMapping("/findByUsername")
    public JsonResult<User> findByUsername(String username){
        User user = userService.findUserByUsername(username);
        return new JsonResult<>(SUCCESS,MESSAGE,user);
    }

    // 访问路径：hppt://localhost:8080/user/changePassword?oldPwd=111111&newPwd=123456
    @RequestMapping("/changePassword")
    public JsonResult<Void> changePassword(String odlPassword, String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        userService.changePassword(uid,odlPassword,newPassword);
        return new JsonResult<>(SUCCESS,MESSAGE);
    }

    //访问路径： http://localhost:8080/user/showUserData
    @RequestMapping("/showUserData")
    public JsonResult<User> showUserInfo(HttpSession session){
        Integer uid = getUidFromSession(session);
        User user  = userService.findUserByUid(uid);
        return new JsonResult<>(SUCCESS,MESSAGE,user);
    }
    //访问路径： http://localhost:8080/user/changeUserData
    @RequestMapping("/changeUserData")
    public JsonResult<User> changeUserInfo(String phone,String email,String gender,HttpSession session){
        Integer uid = getUidFromSession(session);
        User user  = new User();
        user.setUid(uid);
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(Integer.parseInt(gender));
        userService.updateUserInfo(user);
        user = userService.findUserByUid(uid);
        return new JsonResult<>(SUCCESS,MESSAGE,user);
    }
}

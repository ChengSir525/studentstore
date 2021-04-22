package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Version: 2021年03月20日 星期六  12:08:12
 * @Author: 程Sir
 * @Description: 该类标识用户数据处理的持久层
 * 其中 @Repository注解的作用就是为了消除，在service层中，
 * 通过@Autowired注解注入UserMapper值时所出现的错误提示
 *
 * 该层的读取是在启动类TeduStudentStore类上面添加@MapperScan的扫描注解
 * 通过属性 basePackage进行包名路径的指定
 *
 * 其中，Mapper接口指导入的通用Mapper，里面含有大量的增删改查的方法，
 * 当前的UserMapper接口只需要继承就可以获得这些方法的使用权
 */
@Repository
public interface UserMapper extends Mapper<User> {

}

package cn.tedu.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Version: 2021年03月20日 星期六  11:12:17
 * @Author: 程Sir
 * @Description: 该类标识用户的实体类
 */
@Data
@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
// @Data 等价于@Getter、@Setter和@toString
// 实体类需要添加无参构造，这样才会被spring容器所管理，
// 不添加会出现异常： Cause: java.lang.IndexOutOfBoundsException: Index: 12, Size: 12
// 全参构造是方便开发使用，也需要手动添加
@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -6797059395002874772L;

    @Id //标记字段为数据库表的主键
    @KeySql(useGeneratedKeys = true) // 标识该字段可以使用JDBC的方式获取数据库表自增的主键值
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Integer gender;
    private String phone;
    private String email;
    private String avatar;
    private Integer isDelete;
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}

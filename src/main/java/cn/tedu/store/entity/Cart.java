package cn.tedu.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Version: 2021年04月24日 星期六  09:51:27
 * @Author: 程Sir
 * @Description: 该类标识 购物车信息的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_cart")
public class Cart {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Integer num;
    private Long price;
    private String created_user;
    private Date created_time;
    private String modified_user;
    private Date modified_time;
}
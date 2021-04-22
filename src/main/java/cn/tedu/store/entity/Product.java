package cn.tedu.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Version: 2021年04月07日 星期三  14:55:15
 * @Author: 程Sir
 * @Description: 该类标识商品实体类
 */
@Table(name = "t_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -5369264974251784998L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private String price;
    private String num;
    private String image;
    private String status;
    private String priority;
    private String createdTime;
    private String modifiedTime;
    private String createdUser;
    private String modifiedUser;


}

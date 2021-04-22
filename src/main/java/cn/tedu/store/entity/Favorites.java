package cn.tedu.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Version: 2021年04月12日 星期一  16:32:56
 * @Author: 程Sir
 * @Description: 该类标识 收藏商品信息的实体类
 */
@Table(name = "t_favorites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorites implements Serializable {
    private static final long serialVersionUID = 2942012913700567349L;
    private Integer fid;// 收藏夹中商品的id
    private Integer uid;// 用户id
    private Integer pid;// 商品id
    private String created_user;
    private Date created_time;
    private String modified_user;
    private Date modified_time;
}

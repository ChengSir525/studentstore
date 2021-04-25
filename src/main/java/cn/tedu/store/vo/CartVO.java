package cn.tedu.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Version: 2021年04月24日 星期六  19:22:19
 * @Author: 程Sir
 * @Description: 该类标识购物车数据的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVO implements Serializable {

    private static final long serialVersionUID = 6386309442974137447L;
    private Integer cid;
    private Integer pid;
    private Integer uid;
    private String title;
    private String image;
    private Integer num;
    private Long price;
    private Long realPrice;

}

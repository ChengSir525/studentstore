package cn.tedu.store.service;

import cn.tedu.store.vo.CartVO;

import java.util.List;

/**
 * @Version: 2021年04月24日 星期六  10:03:13
 * @Author: 程Sir
 * @Description: 该类标识 收藏夹信息的业务层接口
 */
public interface CartService {

//    void insertCart(Integer uid, String username, Integer pid,Long price);
    /**
     * 向购物车中添加一天商品信息
     * @param uid 用户id
     * @param username 用户姓名
     * @param pid 商品id
     * @return 返回是否添加成功的提示语
     */
    String addCart(Integer uid, String username, Integer pid, Integer count);

    /**
     * 删除购物车中商品id为cid的商品
     * @param cid 购物车cid
     */
    void deleteCart(Integer cid);

    /**
     * 通过用户的id查询该用户购物车值对象信息
     * @param uid 用户id
     * @return 返回查询到的VO对象数据
     */
    List<CartVO> findVOByUid(Integer uid);
}

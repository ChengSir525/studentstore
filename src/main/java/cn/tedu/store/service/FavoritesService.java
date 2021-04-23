package cn.tedu.store.service;

import cn.tedu.store.entity.Product;

import java.util.List;

/**
 * @Version: 2021年04月12日 星期一  16:55:54
 * @Author: 程Sir
 * @Description: 该类标识 收藏夹信息处理的业务层接口
 */
public interface FavoritesService {

    /**
     * 给收藏夹添加一件商品
     * @param uid 用户id
     * @param pid 商品id
     * @param username 用户名称
     * @return 返回收藏相关的信息
     */
    String addFavorites(Integer uid, Integer pid, String username);

    /**
     * 从收藏夹中删除一件商品
     * @param uid 用户id
     * @param pid 商品id
     * @return 返回受影响的行数
     */
    Integer deleteFavorites(Integer uid, Integer pid);

    /**
     * 查询用户收藏的所有商品
     * @param uid 用户id
     * @return 返回该用户收藏的所有商品集合
     */
    List<Product> showFavorites(Integer uid,Integer pageNum,Integer pageSize);
}

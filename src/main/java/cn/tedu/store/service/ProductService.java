package cn.tedu.store.service;

import cn.tedu.store.entity.Product;

import java.util.List;

/**
 * @Version: 2021年04月07日 星期三  15:13:40
 * @Author: 程Sir
 * @Description: 该类标识 商品信息处理的业务层接口
 */
public interface ProductService {
    /**
     * 查询新到商品的数量
     * @return 返回查询到的所有商品信息
     */
    public List<Product> getNewArrival();

    /**
     * 查询热销商品
     * @return 返回查询到的所有商品信息
     */
    public List<Product> getHotList();

    /**
     * 根据商品id查询商品信息
     * @param id 商品id
     * @return 返回查询到的商品信息
     */
    public Product findProductById(Integer id);

}

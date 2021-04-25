package cn.tedu.store.mapper;


import cn.tedu.store.entity.Cart;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Version: 2021年04月24日 星期六  10:10:14
 * @Author: 程Sir
 * @Description: 该类标识 处理购物车信息的持久层接口
 */
@Repository
public interface CartMapper extends Mapper<Cart> {
}

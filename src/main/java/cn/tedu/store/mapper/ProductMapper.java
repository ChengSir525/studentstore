package cn.tedu.store.mapper;

import cn.tedu.store.entity.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Version: 2021年04月07日 星期三  15:22:40
 * @Author: 程Sir
 * @Description: 该类标识 商品信息数据处理的持久层接口
 */
@Repository
public interface ProductMapper extends Mapper<Product> {

}

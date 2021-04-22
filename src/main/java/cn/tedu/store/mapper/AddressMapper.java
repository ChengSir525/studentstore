package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Version: 2021年04月04日 星期日  19:02:25
 * @Author: 程Sir
 * @Description: 该接口标识 是地址信息处理的持久层
 */
@Repository
public interface AddressMapper extends Mapper<Address> {

}

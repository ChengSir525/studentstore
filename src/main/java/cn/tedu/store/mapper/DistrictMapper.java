package cn.tedu.store.mapper;


import cn.tedu.store.entity.District;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Version: 2021年04月05日 星期一  18:50:49
 * @Author: 程Sir
 * @Description: 该接口标识省市区持久层的信息处理类
 */
@Repository
public interface DistrictMapper extends Mapper<District> {
}

package cn.tedu.store.mapper;

import cn.tedu.store.entity.Favorites;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Version: 2021年04月12日 星期一  17:00:59
 * @Author: 程Sir
 * @Description: 该类标识 处理收藏夹信息的持久层接口
 */
@Repository
public interface FavoritesMapper extends Mapper<Favorites> {
}

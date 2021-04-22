package cn.tedu.store.service.imp;

import cn.tedu.store.entity.Favorites;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.FavoritesMapper;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.FavoritesService;
import cn.tedu.store.service.ex.AddFavoritesException;
import cn.tedu.store.service.ex.DeleteFavoritesException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Version: 2021年04月12日 星期一  16:58:31
 * @Author: 程Sir
 * @Description: 该类标识 处理收藏夹信息的业务层处理实现类
 */
@Service
public class FavoritesServiceImp implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer addFavorites(Integer uid, Integer pid, String username) {
        Favorites favorites = new Favorites();
        favorites.setUid(uid);
        favorites.setPid(pid);
        Date date = new Date();
        favorites.setCreated_user(username);
        favorites.setCreated_time(date);
        favorites.setModified_user(username);
        favorites.setModified_time(date);
        Integer row = favoritesMapper.insert(favorites);
        if(row != 1){
            throw new AddFavoritesException("操作失败！未成功将物品添加到收藏夹。");
        }
        return row;
    }

    @Override
    public Integer deleteFavorites(Integer uid, Integer pid) {
//        Example example = new Example(Favorites.class);
//        Example.Criteria ctiteria = example.createCriteria();
//        // 添加的删除where条件就是指定uid和pid的商品
//        ctiteria.andEqualTo("uid",uid);
//        ctiteria.andEqualTo("pid",pid);
        Favorites favorites = new Favorites();
        favorites.setUid(uid);
        favorites.setPid(pid);
        System.out.println("删除的是："+favorites);
        Integer row = favoritesMapper.delete(favorites);
        if(row != 1){
            throw new DeleteFavoritesException("操作失败！未成功将物品从收藏夹删除。");
        }
        return row;
    }

    @Override
    public List<Product> showFavorites(Integer uid,Integer pageNum,Integer pageSize) {
        Example example = new Example(Favorites.class);
        Example.Criteria ctiteria = example.createCriteria();
        // 添加的查询where条件就是指定uid的所有收藏信息
        ctiteria.andEqualTo("uid",uid);
        PageHelper.startPage(pageNum,pageSize);
        List<Favorites> list = favoritesMapper.selectByExample(example);
        List<Product> products = new ArrayList<>();
        for ( Favorites f: list) {
            // 通过遍历拿到该uid用户收藏的所有商品id
            Integer pid = f.getPid();
            // 通过每个pid查询对应的商品详情
            Product product = productMapper.selectByPrimaryKey(pid);
            // 查询到的商品信息添加到集合中
            products.add(product);
            System.out.println("购物车中的是："+product);
        }
        return products;
    }
}

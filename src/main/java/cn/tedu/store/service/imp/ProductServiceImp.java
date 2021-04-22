package cn.tedu.store.service.imp;

import cn.tedu.store.entity.Favorites;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.ProductService;
import cn.tedu.store.service.ex.ProductNotFoundException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Version: 2021年04月07日 星期三  15:20:45
 * @Author: 程Sir
 * @Description: 该类标识 商品数据信息的业务层处理实现类
 */
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductMapper productMapper;
//    @Override
   public List<Product> getNewArrival() {
        /*
        select * from t_product where status=1 order by created_time Desc
         */
        Example example = new Example(Product.class);
        example.setOrderByClause("created_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        PageHelper.startPage(1, 5);
        List<Product> list = productMapper.selectByExample(example);
        //stream（）方法得到一个流，调用foreach方法，输出其中的每个元素
        list.stream().forEach(p->{System.out.println(p);});
        return list;

   }

    @Override
    public List<Product> getHotList() {
        Example example = new Example(Product.class);
        example.setOrderByClause("priority DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status",1);
        PageHelper.startPage(1, 5);
        List<Product> list = productMapper.selectByExample(example);
        //stream（）方法得到一个流，调用foreach方法，输出其中的每个元素
        list.stream().forEach(p->{System.out.println(p);});
        return list;
    }

    @Override
    public Product findProductById(Integer id) {
       Product product = productMapper.selectByPrimaryKey(id);
       if(product == null){
           throw new ProductNotFoundException("抱歉，未找到该商品信息！稍后重试。");
       }
        return product;
    }



//    /**
//     * 分页查询
//     *
//     * @param key    关键字查询
//     * @param page   页数
//     * @param size   每页数量
//     * @param desc   排序方式
//     * @param sortBy 需要排序的字段
//     * @param status 用户状态
//     * @return
//     */
//    public PageInfo<Product> queryProductByPage(String key, int page, int size, Boolean desc, String sortBy, Boolean status) {
//        //分页
//        PageHelper.startPage(page, size);
//        //过滤
//        Example example = new Example(Product.class);
//        if (status != null) {
//            example.setDistinct(status);
//        }
////        if (StringUtils.isNotBlank(key)) {
////            //过滤条件
////            example.createCriteria().orLike("username", "%" + key.toLowerCase() + "%").orEqualTo("phone",key.toLowerCase());
////        }
//        //根据相应的字段进行排序
//        if (StringUtils.isNotBlank(sortBy)) {
//            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
//            example.setOrderByClause(orderByClause);
//        }
//        //查询
//        List<Product> products = productMapper.selectByExample(example);
//        if (CollectionUtils.isEmpty(products)) {
//            return null;
//        }
//        products.stream().forEach(i -> System.out.println(i));
//        //解析分页结果
//        return new PageInfo<>(products);
//    }
}

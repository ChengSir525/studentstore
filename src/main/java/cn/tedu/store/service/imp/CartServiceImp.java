package cn.tedu.store.service.imp;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.CartService;
import cn.tedu.store.service.ProductService;
import cn.tedu.store.service.ex.AddCartException;
import cn.tedu.store.service.ex.DeleteCartException;
import cn.tedu.store.service.ex.ProductNotFoundException;
import cn.tedu.store.service.ex.UpdateCartException;
import cn.tedu.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Version: 2021年04月24日 星期六  10:07:25
 * @Author: 程Sir
 * @Description: 该类标识 处理购物车业务层信息的实现类
 */
@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductService productService;

    @Override
    public String addCart(Integer uid, String username, Integer pid, Integer count) {
        Product product = productService.findProductById(pid);
        if(product == null){
            throw new ProductNotFoundException("添加购物车失败，添加的商品数据不存在或者添加的过程中出现了错误！");
        }
        // 通过pid查询商品信息，获取其中的价格
        String price = product.getPrice();
        Cart cart = new Cart();
        Date date = new Date();
        cart.setCreated_user(username);
        cart.setCreated_time(date);
        cart.setModified_user(username);
        cart.setModified_time(date);
        // 查询一次购物车的所有商品，如果商品存在，则只进行数量的增加
        Cart c= findCartByPid(pid);
        System.err.println(c);
        if (c!=null){//商品数据不为null，表示商品存在
            //获取当前的数量
            Integer num = c.getNum();
            //如果么有传收藏的个数，
            if (count == null){
                // 默认收藏一个
                num++;
                cart.setNum(num);
            }else{
                //传了收藏的个数，则为存在的加要收藏的
                cart.setNum(num+count);
            }
            // 执行更新
            Integer row =  updateCart(pid,cart);
            if (row == null){
                throw new UpdateCartException("添加收藏夹失败！添加过程中出现了位置错误，稍后重试！");
            }
        }else {
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setPrice(Long.parseLong(price));
            cart.setNum(1);
            insertCart(cart);

        }
        return "商品已添加到购物车!";
    }
    /**
     * 向购物车中添加一天商品信息
     * @param cart 需要添加的购物车数据
     */
    public void insertCart(Cart cart) {
        Integer row = cartMapper.insertSelective(cart);
        if(row != 1){
            throw new AddCartException("购物车数据添加过程中发送未知异常，稍后重试！");
        }
    }

    /**
     * 查询所有的购物车商品数据
     * @return 返回购物车信息
     */
    public List<Cart> findAll(){
        List<Cart> list = cartMapper.selectAll();
        return list;
    }

    /**
     * 通过商品的pid查询购物车中的对应的商品
     * @param pid 商品id
     * @return 查询到了返回购物车信息，没有返回null
     */
    public Cart findCartByPid(Integer pid){
        System.out.println("pid = "+pid);
        Cart cart = new Cart();
        cart.setPid(pid);
        cart = cartMapper.selectOne(cart);
        return cart;
    }
    /**
     * 更新购物车商品数据
     * @param cart 购物车商品对象
     * @return 返回受影响的行数
     */
    public Integer updateCart(Integer pid,Cart cart){
        Example example = new Example(Cart.class);
        Example.Criteria criteria = example.createCriteria();
        // 这个就是sql语句中的where子句，pid是需要传入的pid，而不能是从cart对象中获得的，不然就会更新所有数据
        criteria.andEqualTo("pid",pid);
        Integer row = cartMapper.updateByExampleSelective(cart,example);
        return row;
    }

    @Override
    public void deleteCart(Integer cid) {
/*      第一种方法：创建示例对象，将删除条件添加到示例对象中，调用通过示例对象删除的api
        Example example = new Example(Cart.class);
        Example.Criteria criteria = example.createCriteria();
        Cart cart = new Cart();
        cart.setCid(cid);
        criteria.andEqualTo(cart);
        System.err.println(cart);
        Integer row = cartMapper.deleteByExample(example);*/

/*      第二种方式：创建实体对象，将条件，也就是cid设置到实体中，调用直接删除的方法
        Cart cart = new Cart();
        cart.setCid(cid);
        Integer row = cartMapper.delete(cart);*/

        // 第三种方式：因为cid是主键，直接调用依据主键进行删除的api
        Integer row = cartMapper.deleteByPrimaryKey(cid);
        if(row != 1){
            throw new DeleteCartException("取消收藏失败！");
        }
    }

    @Override
    public List<CartVO> findVOByUid(Integer uid) {
        Example example = new Example(Cart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",uid);
        // 通过uid查询到的所有购物车信息
        List<Cart> list = cartMapper.selectByExample(example);

        // 通过遍历拿到购物车信息中的每个商品信息
        List<CartVO> cartVOS = new ArrayList<>();
        for (Cart cart:list) {
            // 创建中间类对象
            CartVO cartVO = new CartVO();
            // 获取购物车中的cid，设置到中间类中
            cartVO.setCid(cart.getCid());
            // 获取购物车中的数量，设置到中间类中
            cartVO.setNum(cart.getNum());
            // 获取购物车中的价格，设置到中间类中
            cartVO.setPrice(cart.getPrice());
            // 获取购物车中商品id
            Integer pid = cart.getPid();
            // 通过商品id获取商品信息
            Product product = productService.findProductById(pid);
            // 获取商品的pid，设置到中间类中
            cartVO.setPid(pid);
            // 获取商品的主题，设置到中间类中
            cartVO.setTitle(product.getTitle());
            // 获取商品的图片，设置到中间类中
            cartVO.setImage(product.getImage());
            // 获取商品的真实价格，设置到中间类中
            cartVO.setRealPrice(Long.parseLong(product.getPrice()));

            // 将设置好的中间类放置到集合中
            cartVOS.add(cartVO);
        }
        return cartVOS;
    }
}

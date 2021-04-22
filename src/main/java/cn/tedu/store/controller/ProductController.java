package cn.tedu.store.controller;

import cn.tedu.store.comment.BaseResult;
import cn.tedu.store.comment.JsonResult;
import cn.tedu.store.entity.Product;
import cn.tedu.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Version: 2021年04月07日 星期三  15:07:29
 * @Author: 程Sir
 * @Description: 该类标识 商品数据的控制层
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseResult {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findNew")
    public JsonResult<List<Product>> findNewArrival(){
        List<Product> list = productService.getNewArrival();
        return new JsonResult(SUCCESS,MESSAGE,list);
    }

    @RequestMapping("/findHot")
    public JsonResult<List<Product>> findHostList(){
        List<Product> list = productService.getHotList();
        return new JsonResult(SUCCESS,MESSAGE,list);
    }

    @RequestMapping("/findProduct")
    public JsonResult<Product> findProductById(Integer id){
        Product product = productService.findProductById(id);
        System.out.println(product);
        return new JsonResult<>(SUCCESS,MESSAGE,product);
    }

}

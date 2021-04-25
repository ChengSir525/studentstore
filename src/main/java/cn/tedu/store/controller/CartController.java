package cn.tedu.store.controller;

import cn.tedu.store.comment.BaseResult;
import cn.tedu.store.comment.JsonResult;
import cn.tedu.store.service.CartService;
import cn.tedu.store.service.ProductService;
import cn.tedu.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Version: 2021年04月24日 星期六  09:56:25
 * @Author: 程Sir
 * @Description: 该类标识 购物车信息处理的控制器类
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseResult {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/findCart")
    public JsonResult<List<CartVO>> findAll(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<CartVO> list = cartService.findVOByUid(uid);
        return new JsonResult(SUCCESS,MESSAGE,list);
    }


    @RequestMapping("/addCart")
    public JsonResult<String> addCart(Integer pid, Integer count, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String message = cartService.addCart(uid, username, pid, count);
        return new JsonResult(SUCCESS,message);
    }

    @RequestMapping("/deleteCart")
    public JsonResult<Void> deleteCart(Integer cid, HttpSession session){
        System.err.println("cid="+cid );
        cartService.deleteCart(cid);
        return new JsonResult(SUCCESS,MESSAGE);
    }
}

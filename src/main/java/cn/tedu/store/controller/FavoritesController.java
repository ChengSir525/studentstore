package cn.tedu.store.controller;

import cn.tedu.store.comment.BaseResult;
import cn.tedu.store.comment.JsonResult;
import cn.tedu.store.entity.Product;
import cn.tedu.store.service.FavoritesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Version: 2021年04月12日 星期一  18:47:26
 * @Author: 程Sir
 * @Description: 该类标识 处理商品收藏信息的控制层类
 */
@RestController
@RequestMapping("/favorites")
public class FavoritesController extends BaseResult {

    @Autowired
    private FavoritesService favoritesService;

    @RequestMapping("/addFavorites")
    public JsonResult<Product> addFavorites(Integer pid, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String message = favoritesService.addFavorites(uid, pid, username);
        return new JsonResult<>(SUCCESS,message);
    }

    @RequestMapping("/deleteFavorites")
    public JsonResult<Void> deleteFavorites(Integer pid, HttpSession session){
        Integer uid = getUidFromSession(session);
        Integer row = favoritesService.deleteFavorites(uid,pid);
        return new JsonResult<>(SUCCESS,MESSAGE);
    }

    // 由于反馈给前端展示的数据中，含有当前页数、总页数等信息，所有返回值类型应为PageHelper插件中的PageInfo对象
    @RequestMapping("/showFavorites")
    public JsonResult<PageInfo> showFavorites(@PathParam("pageNum") Integer pageNum, @PathParam("pageSize") Integer pageSize, HttpSession session){
        System.out.println(pageNum+","+pageSize);
        Integer uid = getUidFromSession(session);
        List<Product> products = favoritesService.showFavorites(uid,pageNum,pageSize);
        // 讲查询到的商品信息集合添加到pageInfo中
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return new JsonResult<>(SUCCESS,MESSAGE,pageInfo);
    }

}

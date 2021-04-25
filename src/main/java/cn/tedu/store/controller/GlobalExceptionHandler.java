package cn.tedu.store.controller;

import cn.tedu.store.comment.JsonResult;
import cn.tedu.store.controller.ex.*;
import cn.tedu.store.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Version: 2021年03月21日 星期日  21:40:45
 * @Author: 程Sir
 * @Description: 该类标识 处理所有控制层的异常处理。可以达到统一处理异常代码的作用
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult jsonResult = new JsonResult(e);

        //判断异常的类型
        if(e instanceof UserNotFoundException){
            jsonResult.setState(2001);//用户名不存在
        }else if(e instanceof UsernameDuplicationException){
            jsonResult.setState(2002);// 用户名重复
        }else if(e instanceof PasswordMisMatchException){
            jsonResult.setState(2003);// 文件为空
        }else if(e instanceof FileIsEmptyException){
            jsonResult.setState(3001);// 文件为空
        }else if(e instanceof FileSizeOutOfBoundsException){
            jsonResult.setState(3002);// 文件超过限制
        }else if(e instanceof FileTypeException){
            jsonResult.setState(3003);// 文件类型异常
        }else if(e instanceof FileStateException){
            jsonResult.setState(3004);// 文件状态异常
        }else if(e instanceof FileIOException){
            jsonResult.setState(3005);// 文件读写异常
        }else if(e instanceof DistrictNotFoundException){
            jsonResult.setState(4001);// 省市区数据不存在异常
        }else if(e instanceof AddFavoritesException){
            jsonResult.setState(5001);// 增加收藏夹数据异常
        }else if(e instanceof DeleteFavoritesException){
            jsonResult.setState(5002);// 删除收藏夹数据异常
        }else if(e instanceof AddCartException){
            jsonResult.setState(6001);// 增加购物车数据异常
        }else if(e instanceof DeleteCartException){
            jsonResult.setState(6002);// 删除购物车数据异常
        }else{
            jsonResult.setState(9999);//系统发现未知错误
            jsonResult.setMessage("操作失败！系统出现未知异常，请联系系统管理员！");
        }
        return jsonResult;
    }
}

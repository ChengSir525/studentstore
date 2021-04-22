package cn.tedu.store.comment;

import javax.servlet.http.HttpSession;

/**
 * @Version: 2021年03月22日 星期一  18:55:46
 * @Author: 程Sir
 * @Description: 该类标识 所有控制器的超类，也就是可以撰写所有controller公有的内容
 */
public class BaseResult {
    public final static Integer SUCCESS = 2000;
    public final static String MESSAGE = "OK";

    /**
     * 从session中获取用户名
     * @param session
     * @return
     */
    protected final String getUsernameFromSession(HttpSession session){
        String username = (String) session.getAttribute("username");
        return username;
    }

    /**
     * 从session中获取用户id
     * @param session
     * @return
     */
    protected final Integer getUidFromSession(HttpSession session){
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        return uid;
    }
    /**
     * 从session中获取用户aid
     * @param session
     * @return
     */
    protected final Integer getAidFromSession(HttpSession session){
        Integer aid = Integer.valueOf(session.getAttribute("aid").toString());
        return aid;
    }


}

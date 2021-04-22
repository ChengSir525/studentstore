package cn.tedu.store.conf;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Version: 2021年04月07日 星期三  09:42:56
 * @Author: 程Sir
 * @Description: 该类标识 登录拦截器配置类
 */
public class LoginInterceptor implements HandlerInterceptor {
    /*
        处理请求之前，完成是否登录的判断
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("uid") == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}

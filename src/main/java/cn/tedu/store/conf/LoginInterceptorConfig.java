package cn.tedu.store.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version: 2021年04月07日 星期三  09:57:13
 * @Author: 程Sir
 * @Description: 该类标识 登录拦截器的配置适配器
 */

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    /*
        需要被放行的静态资源集合
     */
    static List<String> releaseList = new ArrayList<>();
    static{
        releaseList.add("/web/login.html");
        releaseList.add("/web/register.html");
        releaseList.add("/web/index.html");
        releaseList.add("/web/product.html");
        releaseList.add("/bootstrap3/**");
        releaseList.add("/css/**");
        releaseList.add("/images/**");
        releaseList.add("/js/**");
        releaseList.add("/user/login");
        releaseList.add("/user/register");
        releaseList.add("/product/findNew");
        releaseList.add("/product/findHot");
        releaseList.add("/product/findProduct");

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(releaseList);
    }


}


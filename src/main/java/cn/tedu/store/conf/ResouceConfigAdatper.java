package cn.tedu.store.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Version: 2021年03月26日 星期五  12:00:48
 * @Author: 程Sir
 * @Description: 该类标识 静态资源的访问配置
 *
 * 如果不做这样的配置，那么上传的静态资源如图片，就无法通过localhost:8080这样的方式去访问
 * 因为服务器开启的是资源保护模式，但程序启动之后，加载过去的资源才可被访问，如后续上传的图片
 * 这些静态资源不是启动同时部署上来的，就不能够访问，要访问就需要授权，作如下配置。
 *
 */
@Configuration
public class ResouceConfigAdatper implements WebMvcConfigurer {
    // 对静态资源路径的访问设置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取静态资源上传后的存放位置，是决定路径，只适应于windows系统
        String realPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\avatarUpload\\";
        // 注册器中添加资源路径，先授权放行，再指定具体位置
        registry.addResourceHandler("/avatarUpload/**").addResourceLocations("file:"+realPath);
        System.out.println("\t 配置内容执行结束！realPath= "+realPath);
    }
}

import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.AddressService;
import cn.tedu.store.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;

/**
 * @Version: 2021年03月23日 星期二  18:22:07
 * @Author: 程Sir
 * @Description:
 */

public class test {
    @Resource
    private UserService  userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AddressService addressService;
    @Resource
    private AddressMapper addressMapper;
    @Test
    public void test() {
        userService.changeAvatar(18,"cyy","/myUpload/1.png");
    }
    @Test
    public void test1(){
        String dirRealPath = System.getProperty("user.dir");
        System.out.println("\t 获得到的Servlet容器并设置的路径："+dirRealPath);
        dirRealPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        System.out.println("\t 获得到的路径："+dirRealPath+"/src/main/resource/avatarUpload");

    }

    @Test
    public void test2(){
    }
}

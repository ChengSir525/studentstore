package cn.tedu.store.service;

import cn.tedu.store.entity.User;

import java.util.List;

/**
 * @Version: 2021年03月20日 星期六  12:02:50
 * @Author: 程Sir
 * @Description: 该类标识员工业务层
 */
public interface UserService {

    List<User> findUser();

    User findUserByUid(Integer id);

    User findUserByUsername(String username);

    void register(User user);

    User login(String username,String password);

    void changeAvatar(Integer uid, String username, String avatar);

    void changePassword(Integer uid, String odlPassword, String newPassword);

    void updateUserInfo(User user);
}

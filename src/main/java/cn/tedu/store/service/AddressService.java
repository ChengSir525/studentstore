package cn.tedu.store.service;

import cn.tedu.store.entity.Address;

import java.util.List;

/**
 * @Version: 2021年04月04日 星期日  19:03:45
 * @Author: 程Sir
 * @Description: 该类标识 地址信息管理的业务层处理接口
 */
public interface AddressService {

    /**
     * 通过用户的uid查询收货地址信息
     * @param uid 用户的uid
     * @return 返回该用户的所有收货地址信息
     */
    public List<Address> findAddressByUid(Integer uid);

    /**
     * 保存一条收货地址信息
     * @param address 待保存的收货地址信息
     */
    public Integer addAddress(Address address);

    /**
     * 删除一条收货地址信息
     * @param aid 收货地址的id号
     * @return 返回受影响的函数
     */
    public Integer deleteAdderss(Integer aid);

    /**
     * 设置一条收货地址为默认收货地址
     * @param aid 要被设置为默认收货地址的id
     * @param uid 当前的用户id
     * @return 返回受影响的行数
     */
    public Integer setDefaultByAid(Integer aid, Integer uid);

    /**
     * 通过收货地址信息的id查找该收货地址的全部信息
     * @param aid 收货地址信息id
     * @return 返回aid的全部收货地址信息
     */
    public Address findAddressByAid(Integer aid);

    /**
     * 通过收货地址的id，进行更新收货地址信息
     * @param address 需要更新的收货地址信息
     * @return 返回受影响的函数
     */
    public Integer updateAddressByAid(Address address);
}

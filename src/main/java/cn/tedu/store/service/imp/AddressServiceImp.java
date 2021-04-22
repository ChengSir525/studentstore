package cn.tedu.store.service.imp;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.AddressService;
import cn.tedu.store.service.DistrictService;
import cn.tedu.store.service.UserService;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Version: 2021年04月04日 星期日  19:06:06
 * @Author: 程Sir
 * @Description: 该类标识 地址信息管理的业务层实现类
 */
@Service

public class AddressServiceImp implements AddressService {
    @Autowired
    private AddressMapper addressMapper ;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    /**
     * 业务层实现类自己的私有方法，就是从address中的省市区的code获取省市区的name
     * @param address
     */
    private void getNameByCode(Address address) {
         /*
            在新的收货地址信息插入之前，就需要对传入的code查询到对应的name
         */
        String provinceCode = address.getProvinceCode();
        String cityCode = address.getCityCode();
        String areaCode  = address.getAreaCode();
        System.out.println(provinceCode +", "+cityCode+", "+areaCode);

        address.setProvinceName(districtService.getNameByCode(provinceCode));
        address.setCityName(districtService.getNameByCode(cityCode));
        address.setAreaName(districtService.getNameByCode(areaCode));
        System.out.println("省市区名称设置后：" + address);
    }

    /**
     * 设置必要的是个参数
     * @param address 当前增加或者修改的收货地址
     */
    private void setAddressParam(Address address) {
        // 设置四个必要的日志信息
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);
        address.setCreatedUser(userService.findUserByUid(address.getUid()).getUsername());
        address.setModifiedUser(userService.findUserByUid(address.getUid()).getUsername());
        System.out.println("四个必要参数设置后：" + address);
    }

    @Override
    public List<Address> findAddressByUid(Integer uid) {
        Example example = new Example(Address.class);
        /*
            实现数据的降序输出：
            按字段“is_default”降序排列，这样就可以实现默认输出时，此条地址信息总在第一条
            还可以继续添加排序的字段，使用逗号隔开即可
         */
        example.setOrderByClause("is_default DESC");
        Example.Criteria critera = example.createCriteria();
        critera.andEqualTo(uid);
        List<Address> list=  addressMapper.selectByExample(example);
         /*
            前端数据的展示只有aid和XXXname，那么其他字段的展示就可以取消，值设置为null，减小流量传输和网络压力
         */
        for (Address address: list) {
            // 将以下属性设置为null：uid,province_code,city_code,area_code,is_default,4个日志属性
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }

        return list;
    }

    @Override
    public Integer addAddress(Address address) {
        // 调用该类的私有方法，获取省市区对应的名称,并给address设置
        getNameByCode(address);
        // 调用该类的私有方法，设置四个必要的参数
        setAddressParam(address);
        // 设置该新的收货地址非默认收货地址
        address.setIsDefault(0);
        Integer row = addressMapper.insert(address);
        // 如果返回受影响的行数不为 1，增加地址信息失败的
        if(row != 1){
            throw new InsertException("地址信息添加失败！添加时发生未知异常，稍后请重试。");
        }
        return row;
    }


    @Override
    public Integer deleteAdderss(Integer aid) {
        Integer row = addressMapper.deleteByPrimaryKey(aid);
        // 如果返回受影响的行数不为 1，删除地址信息失败的
        if(row != 1){
            throw new InsertException("地址信息删除失败！删除时发生未知异常，稍后请重试。");
        }
        return row;
    }

    @Override
    public Integer setDefaultByAid(Integer aid,Integer uid) {
        // 创建执行实例，不对实例做任何限定，那就是全局更新，  xxxByExampleSelective，只针对非零字段操作
        Example example = new Example(Address.class);
        Example.Criteria critera = example.createCriteria();
        Address address = new Address();
        address.setAid(aid);
        address.setUid(uid);
        // 先将所有的收货地址设置为 非默认的
        address.setIsDefault(0);
        addressMapper.updateByExampleSelective(address,example);

        // 调用本类的私有方法，设置必要的四个参数
        setAddressParam(address);
        address.setIsDefault(1);
        Integer row = addressMapper.updateByPrimaryKeySelective(address);
        return row;
    }

    @Override
    public Address findAddressByAid(Integer aid) {
        Address address = new Address();
        address.setAid(aid);
        address = addressMapper.selectOne(address);
        if(address == null){
            throw new AddressNotFoundException("id为"+aid+"的收货地址找不到！");
        }
        System.err.println(address);
        return address;
    }

    @Override
    public Integer updateAddressByAid(Address address) {
        // 通过本类的私有方法，获取省市区对应的名称
        getNameByCode(address);
        // 调用本类的私有方法，设置四个必要的参数
        setAddressParam(address);
        // 执行数据的修改
        Integer row = addressMapper.updateByPrimaryKeySelective(address);
        if(row != 1){
            throw new UpdateException("收货地址更新失败！");
        }
        return row;
    }
}

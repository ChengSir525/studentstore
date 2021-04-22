package cn.tedu.store.service;

import cn.tedu.store.entity.District;

import java.util.List;

/**
 * @Version: 2021年04月05日 星期一  18:42:36
 * @Author: 程Sir
 * @Description: 该接口标识 省市区数据字典业务处理层
 */
public interface DistrictService {
    /**
     * 查询所有的省、市、区的列表
     * @param parent 父级单位的代号，如果查询全国所有的省份，那么parent的代号就是 “86”
     * @return 返回父级代号下的所有下级市、或区、或县信息
     */
    public List<District> showList(String parent);

    /**
     * 根据省、市、区的代号，查询对应的名称
     * @param code 省、市、区的代号
     * @return 返回查询到的名称
     */
    public String getNameByCode(String code);


}

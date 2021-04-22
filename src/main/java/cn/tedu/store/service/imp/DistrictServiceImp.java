package cn.tedu.store.service.imp;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version: 2021年04月05日 星期一  18:46:29
 * @Author: 程Sir
 * @Description: 该类标识 省市区业务层信息处理的实现类
 */
@Service
public class DistrictServiceImp implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> showList(String parent) {
        District district = new District();
        district.setParent(parent);
        List<District> list = districtMapper.select(district);
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        District district = new District();
        district.setCode(code);
        district = districtMapper.selectOne(district);
        String name = district.getName();
        System.out.println("name= "+name);
        return name;
    }
}

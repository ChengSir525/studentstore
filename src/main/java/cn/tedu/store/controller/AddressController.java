package cn.tedu.store.controller;

import cn.tedu.store.comment.BaseResult;
import cn.tedu.store.comment.JsonResult;
import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.service.AddressService;
import cn.tedu.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Version: 2021年04月04日 星期日  19:18:10
 * @Author: 程Sir
 * @Description: 该类标识 地址信息管理的控制层处理类
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseResult {
    @Autowired
    private AddressService addressService;
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/addresses")
    public JsonResult<Address> findAll(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> list = addressService.findAddressByUid(uid);
        return new JsonResult(SUCCESS,MESSAGE,list);
    }

    @RequestMapping("/addAddress")
    public JsonResult<Address> addAddress(Address address){
        addressService.addAddress(address);
        return new JsonResult(SUCCESS,"增加成功！");
    }

    @RequestMapping("/{aid}/delete")
    public JsonResult<Address> deleteAddress(@PathVariable("aid")Integer aid) {
        addressService.deleteAdderss(aid);
        System.out.println("进行删除操作！");
        return new JsonResult(SUCCESS, "删除成功！");
    }

    // localhost:8080/address/18/setDefault      使用了RESTful请求路径风格
    @RequestMapping("/{aid}/setDefault")
    public JsonResult<Address> setDefaultByAid(@PathVariable("aid")Integer aid,HttpSession session){
        Integer uid = getUidFromSession(session);
        System.out.println("\t uid"+uid);
        addressService.setDefaultByAid(aid,uid);
        return new JsonResult(SUCCESS,"设置成功！");
    }

     // localhost:8080/address/28/toChangeAddress      使用了RESTful请求路径风格
    @RequestMapping("/{aid}/toChangeAddress")
    public JsonResult<Void> toChangeAddress(@PathVariable("aid")Integer aid,HttpSession session){
        /*
            页面跳转前，将要修改地址的aid存储到session中
         */
        session.setAttribute("aid",aid);
        return new JsonResult(SUCCESS,MESSAGE);
    }

    // localhost:8080/address/28/toChangeAddress      使用了RESTful请求路径风格
    @RequestMapping("/changeAddress")
    public JsonResult<Void> changeAddress(Address address, HttpSession session){
        /*
            页面跳转前，aid存储到session中,去获取
         */
        Integer aid = getAidFromSession(session);
        Integer uid = getUidFromSession(session);
        // 将获取到的aid和uid设置到当前修改的收货地址信息信息中
        address.setAid(aid);
        address.setUid(uid);
        Integer row = addressService.updateAddressByAid(address);
        return new JsonResult(SUCCESS,MESSAGE);
    }


    // localhost:8080/address/getAddress
    @RequestMapping("/getAddress")
    public JsonResult<Address> getAddress(HttpSession session){
        /*
            从session'中获取aid，查询该aid的所有地址信息，发给修改页面回显
         */
        Integer aid =getAidFromSession(session);
        System.out.println(aid);
        Address address = addressService.findAddressByAid(aid);
        return new JsonResult(SUCCESS,MESSAGE,address);
    }

    // localhost:8080/address/districts
    @RequestMapping("/districts")
    public JsonResult<District> getDistict(String parent){
       List<District> list = districtService.showList(parent);
        return new JsonResult(SUCCESS,MESSAGE,list);
    }

    // localhost:8080/address/saveAddress
    @RequestMapping("/saveAddress")
    public JsonResult<District> saveAddress(Address address,HttpSession session){
        // 从session中回去要给具体哪个用户添加新的收货地址信息
        address.setUid(getUidFromSession(session));
        addressService.addAddress(address);
        return new JsonResult(SUCCESS,MESSAGE);
    }






}

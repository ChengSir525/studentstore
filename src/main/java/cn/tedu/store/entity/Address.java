package cn.tedu.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Version: 2021年04月04日 星期日  18:45:07
 * @Author: 程Sir
 * @Description: 该类吧标识 收货人地址管理信息实体类
 */
@Table(name = "t_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 6788998945598790570L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer aid;
    private Integer uid;
    private String receiver;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String detail;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}

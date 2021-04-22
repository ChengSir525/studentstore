package cn.tedu.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Version: 2021年04月04日 星期日  21:40:00
 * @Author: 程Sir
 * @Description: 该类标识 省市区的实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_dict_district")
public class District implements Serializable {

    private static final long serialVersionUID = 7163518227726721642L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
	private String parent;
	private String code;
	private String name;
}

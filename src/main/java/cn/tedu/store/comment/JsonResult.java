package cn.tedu.store.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Version: 2021年03月20日 星期六  10:56:48
 * @Author: 程Sir
 * @Description: 用于封装Ajax调用以后的JSON返回值
 * 其返回值格式：
 *      {state:0, data:返回数据, message:提示信息}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -5897359547481100008L;

    private int state;
    private String message;
    private T data;

    public JsonResult(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }
}

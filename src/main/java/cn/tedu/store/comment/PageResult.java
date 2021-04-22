package cn.tedu.store.comment;

import java.util.List;

/**
 * @Version: 2021年04月07日 星期三  20:36:18
 * @Author: 程Sir
 * @Description:
 */
public class PageResult<T> {
    private Long total;
    private Integer totalPage;
    private List<T> items;

    public PageResult(){

    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}

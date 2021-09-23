package cn.smart.common.utils;

import cn.smart.common.xss.SQLFilter;

import java.io.Serializable;

/**
 * @author Ye
 */
public class QueryCondition implements Serializable{

    private int page;

    private int limit;

    private int offset;

    private String orderField;

    private String order;

    public int getOffset() {
        return (this.page - 1 ) * this.limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrderField() {
        return SQLFilter.sqlInject(orderField);
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = SQLFilter.sqlInject(order);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

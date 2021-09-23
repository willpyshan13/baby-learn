package cn.smart.common.utils;

import cn.smart.common.xss.SQLFilter;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query extends LinkedHashMap<String, Object>{

    // 当前页面
    private int page;
    // 每页条数
    private int limit;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        // 分页参数
        String page = String.valueOf(params.get("page"));
        String limit = String.valueOf(params.get("limit"));
        this.page = page == null ? Integer.valueOf(page) : 1;
        this.limit = limit == null ? Integer.valueOf(limit) : 10;

        this.put("offset", (this.page - 1) * this.limit);
        this.put("limit", this.limit);
        this.put("page", this.page);

        //防止SQL注入（因为orderId、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = (String)(params.get("orderField"));
        String order = (String)(params.get("order"));

        if (!StringUtils.isEmpty(orderField)) {
            this.put("orderField",SQLFilter.sqlInject(orderField));
        }

        if (!StringUtils.isEmpty(order)) {
            this.put("order", SQLFilter.sqlInject(order));
        }
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

    public static void main (String[] as){
        System.out.println((0-1) * 1);
    }
}

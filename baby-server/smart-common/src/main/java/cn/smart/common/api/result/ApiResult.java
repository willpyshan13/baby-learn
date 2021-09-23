package cn.smart.common.api.result;

/**
 * 接口返回参数
 */
public class ApiResult {

    /**
     * 结果 0 成功 -1 失败
     */
    private Integer result;
    /**
     * 消息
     */
    private String message;
    /**
     * 状态码
     * {@link ApiStatus} 状态码列表
     */
    private Integer status;
    /**
     * 数据
     */
    private Object data;

    public ApiResult() {
        this.result = 0;
        this.status = 200;
        this.message = "操作成功";
    }

    public ApiResult(Object data) {
        this.result = 0;
        this.status = 200;
        this.message= "操作成功";
        this.data = data;
    }

    public ApiResult(String message) {
        this.result = 0;
        this.status = 200;
        this.message = message;
    }

    public ApiResult(Integer status, String message) {
        this.result = 0;
        this.status = status;
        this.message = message;
    }

    public ApiResult(String message, Object data) {
        this.result = 0;
        this.status = 200;
        this.message = message;
        this.data = data;
    }

    public ApiResult(Integer result, Integer status, String message) {
        this.result = result;
        this.message = message;
        this.status = status;
    }

    public ApiResult(Integer status, String message, Object data) {
        this.result = 0;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}

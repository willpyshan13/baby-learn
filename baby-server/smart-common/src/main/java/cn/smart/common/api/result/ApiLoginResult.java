package cn.smart.common.api.result;

public class ApiLoginResult extends ApiResult {

    public ApiLoginResult(String token) {
        this.token = token;
    }

    public ApiLoginResult(Object data, String token) {
        super(data);
        this.token = token;
    }

    public ApiLoginResult(Integer status, String message) {
        super(status,message);
    }

    public ApiLoginResult(String message, Object data, String token) {
        super(message, data);
        this.token = token;
    }

    public ApiLoginResult(Integer result, Integer status, String message) {
        super(result,status,message);
    }

    public ApiLoginResult(Integer status, String message, String token) {
        super(status, message);
        this.token = token;
    }

    public ApiLoginResult(Integer status, String message, Object data, String token) {
        super(status, message, data);
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

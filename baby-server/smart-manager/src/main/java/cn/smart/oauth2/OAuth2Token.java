package cn.smart.oauth2;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * token
 * @Date 2018-3-27 15:16:45
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

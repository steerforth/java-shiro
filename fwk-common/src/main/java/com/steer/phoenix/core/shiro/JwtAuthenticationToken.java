package com.steer.phoenix.core.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtAuthenticationToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String token;

    public JwtAuthenticationToken(String token) {
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

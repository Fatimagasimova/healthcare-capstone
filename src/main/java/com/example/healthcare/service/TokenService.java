package com.example.healthcare.service;

import com.example.healthcare.util.TokenUtil;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenUtil tokenUtil;

    public TokenService(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    public String generateToken(String username, String role) {
        return tokenUtil.generateToken(username, role);
    }

    public boolean validateToken(String token) {
        return tokenUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return tokenUtil.extractUsername(token);
    }

    public String getUserRoleFromToken(String token) {
        return tokenUtil.extractUserRole(token);
    }
}

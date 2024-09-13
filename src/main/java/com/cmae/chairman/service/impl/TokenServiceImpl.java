package com.cmae.chairman.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenServiceImpl {

    // 模拟一个黑名单
    private Set<String> tokenBlacklist = new HashSet<>();

    // 将 Token 置为失效
    public void invalidateToken(String token) {
        tokenBlacklist.add(token);
    }

    // 检查 Token 是否在黑名单中
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}


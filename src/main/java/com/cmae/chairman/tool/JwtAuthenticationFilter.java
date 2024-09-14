package com.cmae.chairman.tool;

import com.cmae.chairman.service.impl.TokenServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7); // 去掉"Bearer "前缀
//            System.out.println("token:" + token);
//            try {
//                Claims claims = JwtTokenUtil.parseToken(token);  // 调用parseToken解析JWT
//
//                // 你可以在这里使用claims中的信息设置认证信息，例如用户名和权限
//                String username = claims.getSubject();
//
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UsernamePasswordAuthenticationToken authentication =
//                            new UsernamePasswordAuthenticationToken(username, null, null);
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);  // 设置用户认证信息
//                }
//            } catch (Exception e) {
//                System.out.println("Token解析失败: " + e.getMessage());
//            }
//        }
//
//        filterChain.doFilter(request, response);  // 继续执行过滤器链
//    }
//}

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenServiceImpl tokenServiceImpl;

    // 构造函数注入 TokenServiceImpl
    public JwtAuthenticationFilter(TokenServiceImpl tokenServiceImpl) {
        this.tokenServiceImpl = tokenServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        // 获取请求路径
        String requestPath = request.getRequestURI();

        // 检查是否为公共路径，如果是公共路径，跳过 JWT 验证
        if (requestPath.startsWith("/User") || requestPath.startsWith("/Case") ||
                requestPath.startsWith("/Tool") || requestPath.startsWith("/News") ||
        requestPath.startsWith(("/Honor")) || requestPath.startsWith("/Job")) {
            filterChain.doFilter(request, response);
            return; // 直接跳过JWT验证，继续执行下一个过滤器
        }

        // 获取 Authorization 头信息
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // 去掉"Bearer "前缀
            // 检查 Token 是否已失效
            if (tokenServiceImpl.isTokenBlacklisted(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token 已失效");
                return;
            }

            try {
                Claims claims = JwtTokenUtil.parseToken(token);  // 调用parseToken解析JWT

                // 从claims中获取用户名等信息
                String username = claims.getSubject();

                // 检查用户是否已被认证
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 创建一个新的认证对象，并将其存储在SecurityContext中
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, null);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);  // 设置用户认证信息
                }
            } catch (Exception e) {
                System.out.println("Token解析失败: " + e.getMessage());
            }
        }

        // 继续执行过滤器链
        filterChain.doFilter(request, response);
    }
}


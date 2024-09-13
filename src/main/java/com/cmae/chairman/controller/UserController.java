package com.cmae.chairman.controller;

import com.cmae.chairman.entity.User;
import com.cmae.chairman.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cmae.chairman.tool.JwtTokenUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("GetUserAll")
    public List<User> listUsers() {
        return userService.list();
    }

    //用户登录
    @GetMapping("/Login")
    public Map<String, Object> login(@RequestParam String strUser, @RequestParam String strPwd, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();

        // 从数据库中查找用户
        User user = userService.findByUsername(strUser);
        if (user == null) {
            result.put("bRes", false);
            result.put("message", "用户不存在");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return result;
        }

        // 校验密码
        if (passwordEncoder.matches(strPwd, user.getPassword())) {
            String token = JwtTokenUtil.generateToken(strUser);
            result.put("bRes", true);
            result.put("Ticket", token);
            result.put("message", "登录成功");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            result.put("bRes", false);
            result.put("message", "密码错误");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return result;
    }

}

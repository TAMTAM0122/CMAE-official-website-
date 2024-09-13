package com.cmae.chairman.controller;

import com.cmae.chairman.entity.User;
import com.cmae.chairman.service.IUserService;
import com.cmae.chairman.service.impl.TokenServiceImpl;
import com.cmae.chairman.tool.PasswordEncoderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    private TokenServiceImpl tokenService;

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

    @PostMapping("/CreateUser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        user.setPassword(PasswordEncoderUtil.encode(user.getPassword()));
        userService.addUser(user);
        return ResponseEntity.ok("用户创建成功");
    }

    @PostMapping("/ModifiedUser")
    public ResponseEntity<String> modifiedUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("修改成功");
    }

    @PostMapping("/DeleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id) {
        boolean result = userService.removeById(id);
        if (result) {
            return ResponseEntity.ok("删除成功！");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到！");
        }
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

    @PostMapping("/Logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 "Bearer " 前缀
            // 将该 token 加入黑名单
            tokenService.invalidateToken(token);
        }

        // 发送成功响应
        return ResponseEntity.ok("成功退出登录");
    }
}

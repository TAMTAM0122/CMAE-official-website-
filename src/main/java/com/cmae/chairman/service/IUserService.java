package com.cmae.chairman.service;

import com.cmae.chairman.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
public interface IUserService extends IService<User> {
    User findByUsername(String username);

    public void addUser(User user);

    boolean validatePassword(String rawPassword, String encodedPassword);

    public void updateUser(User user);
}

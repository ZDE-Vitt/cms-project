package com.gem.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gem.back.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  前台用户服务类
 * </p>
 */
public interface UserService extends IService<User> {

    User getUserById(Integer id );

    void saveUser(User user);

    void deleteUser(Integer id);
}

package com.gem.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;
import com.gem.back.mapper.UserMapper;
import com.gem.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jzhang
 * @since 2020-02-19
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private  UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() != null){
            userMapper.updateById(user);
        }else {
            userMapper.insert(user);
        }

    }

    @Override
    public void deleteUser(Integer id) {
         userMapper.deleteById(id);
    }
}

package com.gem.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;
import com.gem.back.mapper.SysUserMapper;
import com.gem.back.mapper.UserMapper;
import com.gem.back.service.ISysUserService;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jzhang
 * @since 2020-02-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveSysUser(SysUser sysUser) {
        if (sysUser.getId() != null) {
            sysUserMapper.updateById(sysUser);
        } else {
            //新增用户设置默认密码123456
            String pwd = new BCryptPasswordEncoder().encode("123456");
            sysUser.setPassword(pwd);
            sysUser.setStatus(1);//用户状态
            Integer index = sysUserMapper.insert(sysUser);
        }
        //更新角色
        updateSysUserRoles(sysUser);
    }

    /**
     * 更新用户角色
     */
    private void updateSysUserRoles(SysUser sysUser) {
        if (sysUser != null && sysUser.getRoles() != null) {
            //删除原有角色
            sysUserMapper.deleteRoles(sysUser);
            //更新角色
            sysUserMapper.insertRoles(sysUser);
        }
    }

    @Override
    public SysUser findUserById(Integer userId) {
        return sysUserMapper.findUserById(userId);
    }

    @Override
    public Integer countfrontUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.selectCount(wrapper);
    }
}

package com.gem.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.back.entity.Sucai;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;
import com.gem.back.mapper.ConsoleMapper;
import com.gem.back.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    @Autowired
    private ConsoleMapper consoleMapper;

    @Override
    public List<User> selectAllUser() {
        List<User> users = consoleMapper.selectAllUser();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (User s : users) {
            s.setFormatetime(simpleDateFormat.format(s.getRegTime()));
        }
        return users;
    }

    @Override
    public List<SysUser> selectAllSysUser() {
        return consoleMapper.selectAllSysUser();
    }

    @Override
    public List<Sucai> selectNotPreSuCai() {
        return consoleMapper.selectNotPreSuCai();
    }

    @Override
    public List<Sucai> selectpreSuCai() {
        return consoleMapper.selectPreSuCai();
    }
}

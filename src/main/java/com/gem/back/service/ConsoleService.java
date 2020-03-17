package com.gem.back.service;

import com.gem.back.entity.Sucai;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;

import java.util.List;

public interface ConsoleService {

    List<User> selectAllUser();

    List<SysUser> selectAllSysUser();

    List<Sucai> selectNotPreSuCai();

    List<Sucai> selectpreSuCai();
}

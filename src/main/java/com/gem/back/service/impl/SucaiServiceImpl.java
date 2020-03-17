package com.gem.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.back.entity.Sucai;
import com.gem.back.mapper.SucaiMapper;
import com.gem.back.service.ISucaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Auther: jzhang
 * @Date: 2018/10/23 22:33
 * @Description:
 */
@Service
public class SucaiServiceImpl extends ServiceImpl<SucaiMapper, Sucai> implements ISucaiService {
    @Autowired
    SucaiMapper sucaiMapper;

    @Override
    public Integer setpassnull(Integer id){
        return sucaiMapper.setpassnull(id);
    }

    @Override
    public Integer setinpassnull(Integer id){
        return sucaiMapper.setinpassnull(id);
    }
}

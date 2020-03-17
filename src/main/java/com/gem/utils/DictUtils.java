package com.gem.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.back.entity.SysDict;
import com.gem.back.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/27 09:09
 * @Description: 字典值工具类
 */
@Component
public class DictUtils {
    //需要在工具类中调用service，查询表中记录
    @Autowired
    private ISysDictService service;
    private static ISysDictService sysDictService;
    @PostConstruct //完成对service的注入
    public void init() {
        sysDictService = service;
    }

    /**
     * 根据type和value获取对应的中文label
     */
    public String getDictLabel(String type, Integer value) {
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("typecode",type);
        queryWrapper.eq("value",value);
        SysDict sysDict = sysDictService.getOne(queryWrapper);
        return sysDict.getName();
    }
}

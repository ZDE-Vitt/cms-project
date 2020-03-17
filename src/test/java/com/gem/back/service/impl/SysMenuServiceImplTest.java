package com.gem.back.service.impl;

import com.gem.back.service.ISysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 18:24
 * @Description:
 */
@SpringBootTest
class SysMenuServiceImplTest {

    @Autowired
    private ISysMenuService iSysMenuService;

    @Test
    void queryMenuTree() {
        System.out.println(

        iSysMenuService.queryMenuTree()
        );
    }
}
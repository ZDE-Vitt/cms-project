package com.gem.back.service.impl;

import com.gem.back.service.ConsoleDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 22:34
 * @Description:
 */
@SpringBootTest
class ConsoleDataServiceImplTest {

    @Autowired
    ConsoleDataService consoleDataService;

    @Test
    void getdata() {
        System.out.println(
        consoleDataService.getdata()
        );
    }
}
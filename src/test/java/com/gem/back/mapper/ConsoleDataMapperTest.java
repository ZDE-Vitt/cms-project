package com.gem.back.mapper;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 21:46
 * @Description:
 */
@SpringBootTest
class ConsoleDataMapperTest {

    @Autowired
    ConsoleDataMapper consoleDataMapper;

    @Test
    void getUserStartDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //开始时间
        DateTime starttime = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getUserStartDate()));
        //结束时间
        DateTime endttime = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getUserEndDate()));
        //步长
        DateField day = DateField.DAY_OF_MONTH;
        //生成数组
        List<DateTime> dateTimes = DateUtil.rangeToList(starttime,endttime,day);
        List<String> list = new ArrayList<>();
        for (DateTime dateTime:dateTimes) {
            //格式化时间
            list.add(DateUtil.format(dateTime,"yyyy-MM-dd"));
        }
        list.forEach(System.out::println);

    }

    @Test
    void getUserEndDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //开始时间
        DateTime start = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getSucaiCreateDate()));

        //结束时间
        DateTime end = null;
        if (consoleDataMapper.getSucaiInpasstime().compareTo(consoleDataMapper.getSucaiPasstime()) == 1 ){
            end = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getSucaiInpasstime()));
        } else {
            end = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getSucaiPasstime()));
        }

        //步长
        DateField day = DateField.DAY_OF_MONTH;

        List<DateTime> dateTimes = DateUtil.rangeToList(start,end,day);
        List<String> list = new ArrayList<>();
        for (DateTime dateTime:dateTimes) {
            //格式化时间
            list.add(DateUtil.format(dateTime,"yyyy-MM-dd"));
        }
        list.add(0,"product");
        list.forEach(System.out::println);
        System.out.println("index=1==========================>" + list.get(0));
    }
}
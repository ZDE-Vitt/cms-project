package com.gem.back.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.gem.back.mapper.ConsoleDataMapper;
import com.gem.back.service.ConsoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 21:30
 * @Description:
 */
@Service
public class ConsoleDataServiceImpl implements ConsoleDataService {

    @Autowired
    private ConsoleDataMapper consoleDataMapper;


    @Override
    public Map<String, List<String>> getdata() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //开始时间
        DateTime starttime = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getUserStartDate()));
        //结束时间
        DateTime endttime = DateUtil.parse(simpleDateFormat.format(consoleDataMapper.getUserEndDate()));
        //步长
        DateField day = DateField.DAY_OF_MONTH;
        //生成数组
        List<DateTime> dateTimes = DateUtil.rangeToList(starttime,endttime,day);
        List<String> datelist = new ArrayList<>();

        for (DateTime dateTime:dateTimes) {
            //格式化时间
            datelist.add(DateUtil.format(dateTime,"yyyy-MM-dd"));
        }

        List<String> userlist = new ArrayList<>();

        for (String str: datelist) {
            userlist.add(consoleDataMapper.getNum(str));
        }

        Map<String,List<String>> map = new HashMap<>();

        map.put("datelist" , datelist);
        map.put("userlist" , userlist);

        return map;
    }

    @Override
    public Map<String, List<String>> getSucaiMap() {
        List<String> list = this.getSucaiDate();
        List<String> sucaiNumList =new ArrayList<>();
        for (String s:list) {
            sucaiNumList.add(consoleDataMapper.getSucaiNum(s));
        }
        Map<String, List<String>> sucaimap = new HashMap<>();
        sucaimap.put("sucaiDate",list);
        sucaimap.put("sucaiCNum",sucaiNumList);
        return sucaimap;
    }

    @Override
    public Map<String, List<String>> getPassMap() {
        List<String> list = this.getSucaiDate();
        List<String> sucaiPassNum = new ArrayList<>();
        List<String> sucaiInpassNum = new ArrayList<>();

        for (String s :list){
            sucaiPassNum.add(consoleDataMapper.getPassNum(s));
            sucaiInpassNum.add(consoleDataMapper.getInPassNum(s));
        }
        Map<String, List<String>> sucaiPass = new HashMap<>();
        list.add(0,"product");
        sucaiPassNum.add(0,"审核通过");
        sucaiInpassNum.add(0,"审核未通过");
        sucaiPass.put("sucaipassDate",list);
        sucaiPass.put("sucaiPassNum",sucaiPassNum);
        sucaiPass.put("sucaiInpassNum",sucaiInpassNum);
        return sucaiPass;
    }

    private List<String>  getSucaiDate(){
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
        return list;
    }
}

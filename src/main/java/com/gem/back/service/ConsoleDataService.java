package com.gem.back.service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 21:30
 * @Description:
 */
public interface ConsoleDataService {

    Map<String , List<String>> getdata();
    Map<String , List<String>> getSucaiMap();
    Map<String , List<String>> getPassMap();
}

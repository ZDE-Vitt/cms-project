package com.gem.back.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gem.back.entity.Sucai;
import com.gem.back.entity.SysDict;
import com.gem.back.service.ISucaiService;
import com.gem.back.service.ISysDictService;
import com.gem.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Controller
@RequestMapping("/a/sucai")
public class SucaiController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ISucaiService sucaiService;
    @Autowired
    private ISysDictService sysDictService;

    @ModelAttribute("sucai")
    public Sucai get(Integer id) {
        if (id != null){
            return sucaiService.getById(id);
        }else{
            return new Sucai();
        }
    }

    @RequestMapping("/list")
    public String list() {
        return "sucai/sucaiList";
    }

    @RequestMapping("/shenHeList")
    public String shenHeList() {
        return "sucai/sucaiShenHeList";
    }

    /**
     * 异步返回列表数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/getListData")
    public Map<String, Object> getListData(Integer pageIndex, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Sucai> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("status",2);
        queryWrapper.ne("status",1);
        IPage<Sucai> sucaiIPage = sucaiService.page(new Page<>(pageIndex,pageSize),queryWrapper);
        map.put("data",sucaiIPage.getRecords());
        map.put("count",sucaiIPage.getTotal());
        return map;
    }

    @ResponseBody
    @RequestMapping("/getShenheList")
    public Map<String, Object> getShenheList(Integer pageIndex, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Sucai> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",1);
        IPage<Sucai> sucaiIPage = sucaiService.page(new Page<>(pageIndex,pageSize),queryWrapper);
        map.put("data",sucaiIPage.getRecords());
        map.put("count",sucaiIPage.getTotal());
        return map;
    }


    @RequestMapping("/form")
    public String form(Sucai sucai, Model model) {
        List<SysDict> typeDicts = sysDictService.list(new QueryWrapper<SysDict>()
                .eq("type_code","sucaiStatus"));
        List<SysDict> lnDicts = sysDictService.list(new QueryWrapper<SysDict>()
                .eq("type_code","sucaiStatus"));
        model.addAttribute("typeDicts",typeDicts);
        model.addAttribute("lnDicts",lnDicts);
        model.addAttribute("sucai",sucai);
        return "sucai/sucaiForm";
    }

    @ResponseBody
    @RequestMapping("/reinpass")
    public String reinpass(Integer id){
        Sucai sucai = sucaiService.getById(id);

        QueryWrapper<Sucai> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);

        sucai.setStatus(2);
        sucai.setInpasstime(simpleDateFormat.format(new Date()));

        sucaiService.update(sucai,queryWrapper);

        sucaiService.setpassnull(id);


        return "success";
    }

    @ResponseBody
    @RequestMapping("/pass")
    public String pass(Integer id){
        Sucai sucai = sucaiService.getById(id);

        QueryWrapper<Sucai> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);

        sucai.setStatus(3);
        sucai.setPasstime(simpleDateFormat.format(new Date()));

        sucaiService.update(sucai,queryWrapper);

        return "success";
    }

    @ResponseBody
    @RequestMapping("/inpass")
    public String inpass(Integer id){
        Sucai sucai = sucaiService.getById(id);

        QueryWrapper<Sucai> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);

        sucai.setStatus(2);
        sucai.setInpasstime(simpleDateFormat.format(new Date()));

        sucaiService.update(sucai,queryWrapper);

        return "success";
    }

}

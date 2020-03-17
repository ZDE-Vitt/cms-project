package com.gem.back.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gem.back.entity.Sucai;
import com.gem.back.entity.SysMenu;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;
import com.gem.back.service.*;
import com.gem.back.service.ConsoleService;
import com.gem.utils.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/18 16:23
 * @Description: 后台主页面控制器
 */
@Controller
@RequestMapping("/a")
public class SysIndexController {
    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ConsoleDataService consoleDataService;
    @Autowired
    private ISucaiService sucaiService;
    @Autowired
    private ConsoleService consoleService;

    @RequestMapping("/index")
    public String index(Model model) {
        //读取当前登录成功用户的角色，并根据角色加载菜单
        //获得spring security用户
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        //读取当前用户管理的菜单
        List<SysMenu> menuList = sysMenuService.findListByName(userDetails.getUsername());

        //获取当前登录的系统用户
        model.addAttribute("menus",menuList);
        return "sys/index";
    }

    /**
     * 读取控制台数据
     */
    @RequestMapping("/console")
    public String console(Model model) {

        //前台用户数
        Integer frontUserCount = sysUserService.countfrontUser();

        //管理人员数
        Integer userCount = sysUserService.count();

        //角色数
        Integer roleCount = sysRoleService.count();

        //菜单数
        Integer menuCount = sysMenuService.count();

        QueryWrapper<Sucai> creNum =new QueryWrapper<>();
        creNum.eq("status",1);
        Integer cresucainum = sucaiService.count(creNum);

        QueryWrapper<Sucai> passNum =new QueryWrapper<>();
        creNum.eq("status",3);
        Integer passsucainum = sucaiService.count(passNum);

        model.addAttribute("userCount",userCount);
//        model.addAttribute("roleCount",roleCount);
//        model.addAttribute("menuCount",menuCount);
        model.addAttribute("cresucainum",cresucainum);
        model.addAttribute("passsucainum",passsucainum);
        model.addAttribute("frontUserCount",frontUserCount);
        return "sys/console";
    }

    @RequestMapping("/sucaiadd")
    public String sucaiadd(){
        return "sys/sucaiadd";
    }

    @RequestMapping("/useradd")
    public String useradd(){
        return "sys/useradd";
    }

    @ResponseBody
    @RequestMapping("/userreg")
    public Map<String,List<String>> userreg(){
        return consoleDataService.getdata();
    }

    @ResponseBody
    @RequestMapping("/sucaiNum")
    public Map<String,List<String>> sucaiup(){
        return consoleDataService.getSucaiMap();
    }

    @ResponseBody
    @RequestMapping("/pass")
    public Map<String,List<String>> sucaipass(){
        return consoleDataService.getPassMap();
    }

    @RequestMapping("/UserToExcel")
    public void UserToExcel(HttpServletResponse response){
        List<User> users = consoleService.selectAllUser();
        String excelName ="用户信息表";
        LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id","编号");
        fieldMap.put("username","用户名");
        fieldMap.put("telephone","电话");
        fieldMap.put("account","积分");
        fieldMap.put("formatetime","注册时间");
//        fieldMap.put("sex","性别");
        new ExportExcelUtils().export(excelName,users,fieldMap, response);
    }
    @RequestMapping("/SysUserToExcel")
    public void SysUserToExcel(HttpServletResponse response){
        List<SysUser> sysUsers = consoleService.selectAllSysUser();
        String excelName ="管理员信息表";
        LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id","编号");
        fieldMap.put("username","用户名");
        fieldMap.put("name","真实姓名");
        fieldMap.put("email","邮箱");
        fieldMap.put("mobile","手机号");
        fieldMap.put("status","状态");
        new ExportExcelUtils().export(excelName,sysUsers,fieldMap, response);
    }
    @RequestMapping("/notPreSuCaiToExcel")
    public void notPreSuCaiToExcel(HttpServletResponse response){
        List<Sucai>  sucais= consoleService.selectNotPreSuCai();
        String excelName ="未通过素材";
        LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id","编号");
        fieldMap.put("name","素材名称");
        fieldMap.put("money","价格");
        fieldMap.put("downnum","下载次数");
        fieldMap.put("favnum","收藏人数");
        fieldMap.put("tagid","分类id");
        fieldMap.put("size","大小");
        fieldMap.put("createtime","申请时间");
        fieldMap.put("author","申请人");
        fieldMap.put("userid","申请人id");
/*        fieldMap.put("passtime","审核通过时间");
        fieldMap.put("inpasstime","审核不通过时间");*/
        fieldMap.put("description","相关描述");
        new ExportExcelUtils().export(excelName,sucais,fieldMap, response);
    }
    @RequestMapping("/preSuCaiToExcel")
    public void preSuCaiToExcel(HttpServletResponse response){
        List<Sucai>  sucais= consoleService.selectpreSuCai();
        String excelName ="审核通过素材";
        LinkedHashMap<String,String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id","编号");
        fieldMap.put("name","素材名称");
        fieldMap.put("money","价格");
        fieldMap.put("downnum","下载次数");
        fieldMap.put("favnum","收藏人数");
        fieldMap.put("tagid","分类id");
        fieldMap.put("size","大小");
        fieldMap.put("createtime","申请时间");
        fieldMap.put("author","申请人");
        fieldMap.put("userid","申请人id");
/*        fieldMap.put("passtime","审核通过时间");
        fieldMap.put("inpasstime","审核不通过时间");*/
        fieldMap.put("description","相关描述");
        new ExportExcelUtils().export(excelName,sucais,fieldMap, response);
    }
}

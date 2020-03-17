package com.gem.back.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gem.back.entity.SysRole;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;
import com.gem.back.service.UserService;
import com.gem.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: Fei Peng
 * @CreateDate: 2020/3/16 14:43
 * @Description:
 */
@Controller
@RequestMapping("/a/front")
public class UserFrontListController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userlist")
    public String Userlist(Integer curr,Integer size,User User, Model model){
        //默认配置
        Integer current = 1;
        Integer pageSize = 10;
        //判断前台传递的页码和每页记录数
        if (curr != null) current = curr;
        if (size != null) pageSize = size;

        //查询用户的数据，并分页
        //列表数据，总记录数，当前页
        //分页对象
        //查询对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //绑定查询条件
        if (User != null && User.getUsername() != null && User.getUsername() != "") {
            queryWrapper.eq("username",User.getUsername());
        }
        if (User != null && User.getTelephone() != null && User.getTelephone() != "") {
            queryWrapper.eq("telephone",User.getTelephone());
        }

        IPage<User> page = userService.page(new Page<>(current,pageSize),queryWrapper);

        model.addAttribute("userList",page.getRecords());//获取某一页的记录数
        model.addAttribute("total",page.getTotal());    //获取记录总数
        model.addAttribute("current",page.getCurrent());//当前页
        return "sys/FrontUserList";
    }
   /* @RequestMapping("/form")
    public String form(Model model, Integer id, SysUser sysUser) {
        //获取所有的角色
        List<SysRole> roleList = sysRoleService.list();
        if (id != null) {
            sysUser = sysUserService.findUserById(id);
        }
        model.addAttribute("roleList", roleList);
        System.out.println(sysUser);
        model.addAttribute("sysUser", sysUser);
        return "sys/userForm";
    }*/

    @RequestMapping("/form")
    public String form(Model model, Integer id, User user) {
        if (id != null) {
            user = userService.getUserById(id);
        }
        System.out.println(user);
        model.addAttribute("UserList", user);
        return "sys/FrontUserForm";
    }

    @RequestMapping("/save")
    public String save(User user) {
      userService.saveUser(user);
        return "redirect:userlist";
    }

    @RequestMapping("/delete")
    public String deleteUser(Integer id){
        userService.deleteUser(id);
    return "redirect:userlist";
    }

    /**
     * 修改用户的状态
     */
    @ResponseBody
    @RequestMapping("/changeStatus")
    public ResponseResult changeStatus(User user) {
        userService.updateById(user);
        ResponseResult result = null;
        if (user.getStatus() == 2) {
            result = new ResponseResult("200","账户已锁定");
        } else {
            result = new ResponseResult("200","账户已启动");
        }
        return result;
    }
}

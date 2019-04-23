package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Cliam;
import com.fdy.exception.NotFoundException;
import com.fdy.service.AccountService;
import com.fdy.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/** 申请认领记录管理控制器
 * @author fdy
 */
@Controller
@RequestMapping("/cliam")
public class CliamController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ShiroUtil shiroUtil;

    /**跳转到申请认领管理首页
     * @return
     */
    @GetMapping
    public String petManager(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false)String username,
                             @RequestParam(required = false)String mobile,
                             @RequestParam(required = false)String state,Model model){

        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("username",username);
        selectMap.put("mobile",mobile);
        selectMap.put("state",state);

        PageInfo<Cliam> pageInfo = accountService.findAllCLiamsByMapandPageNo(pageNo,selectMap);

        model.addAttribute("pageInfo",pageInfo);
        return "cliam/home";
    }

    /**跳转到申请记录详情页
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String look(@PathVariable Integer id, Model model){
        Cliam cliam = accountService.findCliamById(id);
        if(cliam == null){
            throw new NotFoundException();
        }
        Account account = accountService.findByMobile(cliam.getMobile());
        model.addAttribute("account",account);
        model.addAttribute("cliam",cliam);
        return "cliam/look";
    }
}

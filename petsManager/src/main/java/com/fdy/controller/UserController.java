package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Cliam;
import com.fdy.exception.NotFoundException;
import com.fdy.exception.ServiceException;
import com.fdy.filestore.Qiniustore;
import com.fdy.service.AccountService;
import com.fdy.util.AjaxResponseData;
import com.fdy.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 客户端用户管理控制器
 * @author fdy
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private Qiniustore qiniustore;

    /**跳转到用户管理首页
     * @return
     */
    @GetMapping
    public String petManager(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false)String username,
                             @RequestParam(required = false)String mobile,
                             @RequestParam(required = false)String role,Model model){

        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("username",username);
        selectMap.put("mobile",mobile);
        selectMap.put("role",role);

        PageInfo<Account> pageInfo = accountService.findAllByMapandPageNo(pageNo,selectMap);

        model.addAttribute("role",shiroUtil.getCurrAcc().getRole());
        model.addAttribute("pageInfo",pageInfo);
        return "user/home";
    }

    /**跳转到用户编辑页
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editpets(@PathVariable Integer id, Model model){
        Account account = accountService.findById(id);

        model.addAttribute("account",account);
        model.addAttribute("token",qiniustore.getUploadToken());
        return "user/edit";
    }

    /** 保存更新后的用户信息
     * @return
     */
    @PostMapping("/{accountId:\\d+}/edit")
    public String updatepet(@PathVariable Integer accountId,Account account,RedirectAttributes redirectAttributes){
        try{
            accountService.updateAcc(accountId, account);
            redirectAttributes.addFlashAttribute("message","修改成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/user";
    }

    /**删除用户
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            accountService.del(id,shiroUtil.getCurrAcc());
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }

    /**跳转到用户注册页面
     * @return
     */
    @GetMapping("/regist")
    public String userRigst(Model model){
        String token = qiniustore.getUploadToken();
        model.addAttribute("token",token);
        return "user/regist_user";
    }

    /**保存注册用户
     * @return
     */
    @PostMapping("/regist")
    public String saveUser(Account account,RedirectAttributes redirectAttributes){
        try {
            accountService.saveAcc(account);
            redirectAttributes.addFlashAttribute("message", "用户注册成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/user";
    }

    /**跳转到用户详情页
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String look(@PathVariable Integer id,Model model){
        Account account = accountService.findById(id);
        if(account == null){
            throw new NotFoundException();
        }
        model.addAttribute("account",account);
        return "user/look";
    }
}

package com.fdy.controller;

import com.fdy.entity.Cliam;
import com.fdy.entity.Pets;
import com.fdy.entity.Userpet;
import com.fdy.exception.NotFoundException;
import com.fdy.exception.ServiceException;
import com.fdy.filestore.Qiniustore;
import com.fdy.service.AccountService;
import com.fdy.service.PetsService;
import com.fdy.util.AjaxResponseData;
import com.fdy.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/** 用户宠物发布管理控制器
 * @author fdy
 */
@Controller
@RequestMapping("/userpet")
public class UserPetController {

    @Autowired
    private PetsService petsService;
    @Autowired
    private ShiroUtil shiroUtil;

    /**跳转到流浪宠物管理首页
     * @return
     */
    @GetMapping
    public String petManager(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false)String petname,
                             @RequestParam(required = false)String state,Model model){

        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("petname",petname);
        selectMap.put("state",state);

        PageInfo<Userpet> pageInfo = petsService.findAllUserPetsByMapandPageNo(pageNo,selectMap);
        model.addAttribute("pageInfo",pageInfo);
        return "userpet/home";
    }

    /**删除流浪宠物
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            petsService.delUserPet(id);
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }

    /**跳转到宠物详情页
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String look(@PathVariable Integer id,Model model){
        Userpet userpet = petsService.findUserPetById(id);
        if(userpet == null){
            throw new NotFoundException();
        }
        Cliam cliam = petsService.findBymobileAndname(userpet.getPetname(),userpet.getMobile());
        model.addAttribute("userpet",userpet);
        model.addAttribute("mobile",shiroUtil.getCurrAcc().getMobile());
        model.addAttribute("cliam",cliam);
        return "userpet/look";
    }
}

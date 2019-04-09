package com.fdy.controller;

import com.fdy.entity.Pets;
import com.fdy.exception.NotFoundException;
import com.fdy.exception.ServiceException;
import com.fdy.filestore.Qiniustore;
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

/** 流浪宠物控制器
 * @author fdy
 */
@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetsService petsService;
    @Autowired
    private Qiniustore qiniustore;
    @Autowired
    private ShiroUtil shiroUtil;

    /**跳转到流浪宠物管理首页
     * @return
     */
    @GetMapping
    public String petManager(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false)String petname,
                             @RequestParam(required = false)String state,
                             @RequestParam(required = false)String age,Model model){

        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("petname",petname);
        selectMap.put("state",state);
        selectMap.put("age",age);

        PageInfo<Pets> pageInfo = petsService.findAllByMapandPageNo(pageNo,selectMap);

        model.addAttribute("role",shiroUtil.getCurrAcc().getRole());
        model.addAttribute("pageInfo",pageInfo);
        return "pet/home";
    }

    /**跳转到宠物新增页面
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String newpets(Model model){

        String token = qiniustore.getUploadToken();
        model.addAttribute("token",token);
        model.addAttribute("role",shiroUtil.getCurrAcc().getRole());
        return "pet/new";
    }

    /**保存新增的流浪宠物信息
     * @return
     */
    @PostMapping("/new")
    public String savepets(Pets pets,RedirectAttributes redirectAttributes){
        try {
            petsService.savepets(pets);
            redirectAttributes.addFlashAttribute("message", "新增售票点成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/pet";
    }

    /**跳转到流浪宠物编辑页
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editpets(@PathVariable Integer id,Model model){
        Pets pets = petsService.findById(id);

        model.addAttribute("pets",pets);
        model.addAttribute("token",qiniustore.getUploadToken());
        model.addAttribute("role",shiroUtil.getCurrAcc().getRole());
        return "pet/edit";
    }

    /** 保存更新后的宠物信息
     * @return
     */
    @PostMapping("/{id:\\d+}/edit")
    public String updatepet(Pets pets,RedirectAttributes redirectAttributes){
        try{
            petsService.updatepet(pets);
            redirectAttributes.addFlashAttribute("message","修改成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/pet";
    }

    /**删除流浪宠物
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            petsService.del(id);
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
        Pets pets = petsService.findById(id);
        if(pets == null){
            throw new NotFoundException();
        }
        model.addAttribute("pets",pets);
        model.addAttribute("role",shiroUtil.getCurrAcc().getRole());
        return "pet/look";
    }
}

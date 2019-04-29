package com.fdy.controller;

import com.fdy.entity.Sick;
import com.fdy.exception.ServiceException;
import com.fdy.service.PetsService;
import com.fdy.util.AjaxResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/** 宠物就医控制器
 * @author fdy
 */
@Controller
@RequestMapping("/sick")
public class SickController {

    @Autowired
    private PetsService petsService;

    /** 跳转到宠物就医首页
     * @return
     */
    @GetMapping
    public String home(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                       Model model){
        PageInfo<Sick> pageInfo = petsService.findAllSickByPageNo(pageNo);
        model.addAttribute("pageInfo",pageInfo);
        return "sick/home";
    }

    /** 跳转到新增宠物就医记录
     * @return
     */
    @GetMapping("/news")
    public String newSick(){
        return "sick/new";
    }

    /** 保存宠物就医记录
     * @return
     */
    @PostMapping("/news")
    public String saveSick(Sick sick, RedirectAttributes redirectAttributes){
        petsService.saveSick(sick);
        redirectAttributes.addFlashAttribute("message","新增宠物就医记录成功");
        return "redirect:/sick";
    }

    /**删除宠物就医记录
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            petsService.delSick(id);
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }

}

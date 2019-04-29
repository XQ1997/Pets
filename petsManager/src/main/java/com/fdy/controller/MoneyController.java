package com.fdy.controller;

import com.fdy.entity.Money;
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

/** 捐助记录控制器
 * @author fdy
 */
@Controller
@RequestMapping("/mom")
public class MoneyController {

    @Autowired
    private PetsService petsService;

    /** 跳转到捐助记录首页
     * @return
     */
    @GetMapping
    public String home(Model model){
        List<Money> moneyList = petsService.findAllMoney();
        Double moneys = petsService.countmoney();
        System.out.println(moneys);
        model.addAttribute("moneys",moneys);
        model.addAttribute("moneyList",moneyList);
        return "money/home";
    }

    /** 跳转到新增捐助记录
     * @return
     */
    @GetMapping("/new")
    public String newSick(){
        return "money/new";
    }

    /** 保存捐助记录
     * @return
     */
    @PostMapping("/new")
    public String saveSick(Money money, RedirectAttributes redirectAttributes){
        petsService.saveMoney(money);
        redirectAttributes.addFlashAttribute("message","新增捐助记录成功");
        return "redirect:/mom";
    }

    /**删除捐助记录
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            petsService.delMoney(id);
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }

    /**跳转到捐助记录编辑页
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editMoney(@PathVariable Integer id, Model model){
        Money money = petsService.findMoneyById(id);

        model.addAttribute("money",money);
        return "money/edit";
    }

    /** 保存更新后的用户信息
     * @return
     */
    @PostMapping("/{id:\\d+}/edit")
    public String updateMoney(Money money,RedirectAttributes redirectAttributes){
        try{
            petsService.updateMoney(money);
            redirectAttributes.addFlashAttribute("message","修改成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/mom";
    }


}

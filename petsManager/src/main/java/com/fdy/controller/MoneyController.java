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

/** 捐助记录控制器
 * @author fdy
 */
@Controller
@RequestMapping("/money")
public class MoneyController {

    @Autowired
    private PetsService petsService;

    /** 跳转到捐助记录首页
     * @return
     */
    @GetMapping
    public String home(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                       Model model){
        PageInfo<Money> pageInfo = petsService.findAllMoneyByPageNo(pageNo);
        Double money = petsService.countmoney();
        model.addAttribute("money",money);
        model.addAttribute("pageInfo",pageInfo);
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
        return "redirect:/money";
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

}

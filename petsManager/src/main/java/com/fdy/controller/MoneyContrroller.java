/*
package com.fdy.controller;

import com.fdy.entity.Money;
import com.fdy.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mom")
public class MoneyContrroller {

    @Autowired
    private PetsService petsService;

    @GetMapping
    public String home(Model model){
        List<Money> moneyList = petsService.findAllMoney();
       */
/* Double moneys = petsService.countmoney();
        model.addAttribute("moneys",moneys);*//*

        model.addAttribute("moneyList",moneyList);
        return "money/home";
    }


}
*/

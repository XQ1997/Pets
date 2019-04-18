package com.nf.controller;

import com.nf.entity.Account;
import com.nf.entity.Cliam;
import com.nf.entity.Pets;
import com.nf.entity.Words;
import com.nf.exception.NotFoundException;
import com.nf.exception.ServiceException;
import com.nf.service.AccountService;
import com.nf.service.PetsService;
import com.nf.util.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private PetsService petsService;
    @Autowired
    private ShiroUtil shiroUtil;

    /**跳转到宠物
     * @return
     */
    @GetMapping("/pet")
    public String petManager(Model model){
        List<Pets> petsList = petsService.findAllPets();
        model.addAttribute("petsList",petsList);
        return "manager/pet";
    }

    /**跳转到宠物详情页
     * @param model
     * @return
     */
    @GetMapping("/pet/{id:\\d+}")
    public String look(@PathVariable Integer id, Model model){
        Pets pets = petsService.findById(id);
        if(pets == null){
            throw new NotFoundException();
        }
        model.addAttribute("pets",pets);
        return "manager/petlook";
    }

    /**跳转到宠物领养申请页面
     * @return
     */
    @GetMapping("/cliam")
    public String cliam(){
        return "manager/cliam";
    }

    /**保存宠物认领申请记录
     * @param cliam 认领申请记录对象
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/cliam")
    public String saveCliam(Cliam cliam, RedirectAttributes redirectAttributes){
        try{
            accountService.saveCliam(cliam);
            redirectAttributes.addFlashAttribute("message", "提交成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/cliam";
    }

    /**查看领养申请状态
     * @param model
     * @return
     */
    @GetMapping("/cliam/state")
    public String state(Model model){
        Account account = shiroUtil.getCurrAcc();
        //根据当前登录账户的电话获取申请记录对象
        List<Cliam> cliamList = accountService.findCliamBymobile(account.getMobile());
        model.addAttribute("cliamList",cliamList);
        return "manager/cliamstate";
    }

    /**查看用户信息
     * @return
     */
    @GetMapping("/user")
    public String usermanager(Model model){
        model.addAttribute("account",shiroUtil.getCurrAcc());
        return "manager/user";
    }

    /**跳转到关于我们页面
     * @return
     */
    @GetMapping("/about")
    public String about(){
        return "manager/about";
    }

    /**跳转到留言板页面
     * @return
     */
    @GetMapping("/words")
    public String words(){
        return "manager/words";
    }

    /**保存留言信息
     * @return
     */
    @PostMapping("/words")
    public String savewords(Words words,RedirectAttributes redirectAttributes){
        accountService.saveWords(words,shiroUtil.getCurrAcc());
        redirectAttributes.addFlashAttribute("words",words);
        redirectAttributes.addFlashAttribute("message","留言成功");
        return "redirect:/words";
    }
}

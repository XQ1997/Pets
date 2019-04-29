package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Cliam;
import com.fdy.entity.Pets;
import com.fdy.entity.Words;
import com.fdy.exception.NotFoundException;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import com.fdy.service.PetsService;
import com.fdy.util.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**客户端控制器
 * @author fdy
 */
@Controller
@RequestMapping("/client")
public class ClientController {

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
        return "clientPage/pet";
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
        return "clientPage/petlook";
    }

    /**跳转到宠物领养申请页面
     * @return
     */
    @GetMapping("/cliam")
    public String cliam(){
        return "clientPage/cliam";
    }

    /**保存宠物认领申请记录
     * @param cliam 认领申请记录对象
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/cliam")
    public String saveCliam(Cliam cliam, RedirectAttributes redirectAttributes){
        try{
            accountService.saveCliam(shiroUtil.getCurrAcc(),cliam);
            redirectAttributes.addFlashAttribute("message", "提交成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/client/cliam";
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
        return "clientPage/cliamstate";
    }

    /**查看用户信息
     * @return
     */
    @GetMapping("/user")
    public String usermanager(Model model){
        model.addAttribute("account",shiroUtil.getCurrAcc());
        return "clientPage/user";
    }

    /**跳转到关于我们页面
     * @return
     */
    @GetMapping("/about")
    public String about(){
        return "clientPage/about";
    }

    /**跳转到留言板页面
     * @return
     */
    @GetMapping("/words")
    public String words(){
        return "clientPage/words";
    }

    /**保存留言信息
     * @return
     */
    @PostMapping("/words")
    public String savewords(Words words, RedirectAttributes redirectAttributes){
        accountService.savewords(words,shiroUtil.getCurrAcc());
        redirectAttributes.addFlashAttribute("words",words);
        redirectAttributes.addFlashAttribute("message","留言成功");
        return "redirect:/client/words";
    }

    /**跳转到修改密码页面
     * @return
     */
    @GetMapping("/repassword")
    public String repassword(Model model){
        return "clientPage/repassword";
    }

    /**修改密码
     * @param oldpass 原密码
     * @param newpass 新密码
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/repassword")
    public String repassword(String oldpass,String newpass,RedirectAttributes redirectAttributes){
        try{
            //获取当前的登陆对象
            Account account = shiroUtil.getCurrAcc();
            accountService.repassword(account,oldpass,newpass);
            redirectAttributes.addFlashAttribute("message","修改成功");
            return "redirect:/logout";
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/client/repassword";
        }
    }

    /** 跳转到个人信息修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editUser(@PathVariable Integer id, Model model){
        Account account = accountService.findById(id);

        model.addAttribute("account",account);
        return "user/edit";
    }

    /** 保存更新后的用户信息
     * @return
     */
    @PostMapping("/{id:\\d+}/edit")
    public String updateUser(Account account,RedirectAttributes redirectAttributes){
        try{
            accountService.updateAcc(account);
            redirectAttributes.addFlashAttribute("message","修改成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/user";
    }
}

package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Words;
import com.fdy.service.AccountService;
import com.fdy.util.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Date;

/**留言板控制器
 * @author fdy
 */
@Controller
@RequestMapping("/words")
public class WordsController {

    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private AccountService accountService;
    /** 跳转到留言板首页
     * @param model
     * @return
     */
    @GetMapping
    public String home(Model model){
        model.addAttribute("role",shiroUtil.getCurrAcc().getRole());
        return "words/home";
    }

    /**保存留言信息
     * @return
     */
    @PostMapping
    public String savewords(Words words, RedirectAttributes redirectAttributes){
        Account account = shiroUtil.getCurrAcc();
        accountService.savewords(words,account);
        redirectAttributes.addFlashAttribute("message","留言成功");
        redirectAttributes.addFlashAttribute("words",words);
        return "redirect:/words";
    }
}

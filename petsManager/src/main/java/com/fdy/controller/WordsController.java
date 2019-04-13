package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Reply;
import com.fdy.entity.Words;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import com.fdy.util.AjaxResponseData;
import com.fdy.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String home(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false)String title,
                       @RequestParam(required = false)String createTime,Model model){
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("title",title);
        selectMap.put("createTime",createTime);

        PageInfo<Words> pageInfo = accountService.findAllWordsByMapandPageNo(pageNo,selectMap);

        model.addAttribute("pageInfo",pageInfo);

        return "words/home";
    }

    /**跳转到回复页
     * @return
     */
    @GetMapping("{id:\\d+}/reply")
    public String reply(){
        return "words/reply";
    }

    /**保存回复消息
     * @param reply
     * @param redirectAttributes
     * @return
     */
    @PostMapping("{id:\\d+}/reply")
    public String savereply(@PathVariable Integer id,Reply reply, RedirectAttributes redirectAttributes){
        accountService.saveReply(reply,id);
        redirectAttributes.addFlashAttribute("message","回复成功");
        return "redirect:/words";
    }

    /**删除留言
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            accountService.delWords(id);
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }

    /**跳转到留言详情页
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String look(@PathVariable Integer id,Model model){
        Words words = accountService.findWordsById(id);
        if(words != null){
            List<Reply> replyList = accountService.findALLReply(words.getUsername());
            model.addAttribute("replyList",replyList);
        }
        model.addAttribute("words",words);
        return "words/look";
    }
}

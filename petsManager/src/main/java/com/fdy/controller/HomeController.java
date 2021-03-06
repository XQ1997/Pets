package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Notice;
import com.fdy.entity.Pets;
import com.fdy.exception.NotFoundException;
import com.fdy.exception.ServiceException;
import com.fdy.filestore.Qiniustore;
import com.fdy.service.AccountService;
import com.fdy.service.PetsService;
import com.fdy.util.AjaxResponseData;
import com.fdy.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**用户的登录登出控制器
 * @author fdy
 */
@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private PetsService petsService;
    @Autowired
    private Qiniustore qiniustore;

    /**登录页面
     * @return 跳转到登录页面
     */
    @GetMapping("/")
    public String login(){
        //获得subject对象
        Subject subject = SecurityUtils.getSubject();
        //判断当前是否有已经认证的账户(该subject是否认证)，认证过的话就退出该账户
        if(subject.isAuthenticated()){
            subject.logout();
        }
        //如果当前为记住我，直接跳转到首页
        if(subject.isRemembered()){
            return "redirect:/home";
        }
        return "login";
    }

    /**用户登录
     * @param mobile 表单中的手机号
     * @param password 表单中的密码
     * @param request
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/")
    public String login(String mobile, String password, HttpServletRequest request,
                        HttpSession session, String rememberMe,RedirectAttributes redirectAttributes) {

        //1.创建Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2.生成账号密码认证对象
        //2.1获得当前登录用户的ip,可以作为参数传递
        String ip = request.getRemoteAddr();
        //2.2将电话，密码，ip作为认证对象参数传递
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(mobile,DigestUtils.md5Hex(password),rememberMe!=null,ip);
        try {
            //3.根据账号密码进行登录
            subject.login(usernamePasswordToken);
            //登录成功之后跳转的目标页面的判断
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";
            Account account = shiroUtil.getCurrAcc();
            if(Account.TYPE_USER.equals(account.getRole())){
                url = "/index";
            }
            if(savedRequest != null){
                url = savedRequest.getRequestUrl();
            }
            //登录成功重定向到首页
            return "redirect:" + url;
        }catch (UnknownAccountException | IncorrectCredentialsException e){
            redirectAttributes.addFlashAttribute("message","账号或者密码错误");
        }catch (LockedAccountException e){
            redirectAttributes.addFlashAttribute("message","账户已被锁定");
        }catch (AuthenticationException e){
            redirectAttributes.addFlashAttribute("message","账户或者密码错误");
        }
        return "redirect:/";
    }

    /**跳转到首页
     * @return
     */
    @GetMapping("/home")
    public String home(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false)String title,
                       @RequestParam(required = false)String createTime,Model model){
        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("title",title);
        selectMap.put("createTime",createTime);

        PageInfo<Notice> pageInfo = accountService.findAllNoticeByMapandPageNo(pageNo,selectMap);

        model.addAttribute("pageInfo",pageInfo);
        return "home";
    }

    /**注销操作-退出系统
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        redirectAttributes.addFlashAttribute("message","你已安全退出系统");
        return "/";
    }

    /**跳转到修改密码页面
     * @return
     */
    @GetMapping("/repassword")
    public String repassword(Model model){
        return "repassword";
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
            return "redirect:/repassword";
        }
    }

    /**跳转到公告详情页
     * @param model
     * @return
     */
    @GetMapping("/notice/{id:\\d+}")
    public String looknotice(@PathVariable Integer id, Model model){
        Notice notice = petsService.findNoticeById(id);
        if(notice == null){
            throw new NotFoundException();
        }
        model.addAttribute("notice",notice);
        return "noticelook";
    }

    /**跳转到公告编辑页
     * @return
     */
    @GetMapping("/notice/{id:\\d+}/edit")
    public String editnotice(@PathVariable Integer id, Model model){
        Notice notice = accountService.findNoticeByid(id);

        model.addAttribute("notice",notice);
        return "notice/edit";
    }

    /** 保存更新后的公告
     * @return
     */
    @PostMapping("/notice/{id:\\d+}/edit")
    public String updatenotice(Notice notice,RedirectAttributes redirectAttributes){
        try{
            accountService.updateNotice(notice);
            redirectAttributes.addFlashAttribute("message","修改成功");
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/home";
    }

    /**删除公告
     * @return
     */
    @GetMapping("/notice/{id:\\d+}/del")
    @ResponseBody
    public AjaxResponseData del(@PathVariable Integer id){
        try{
            accountService.delNotice(id);
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }

    /**跳转到新增公告页面
     * @return
     */
    @GetMapping("/notice/new")
    public String newNOtice(){
        return "notice/new";
    }

    /**保存新增公告
     * @return
     */
    @PostMapping("/notice/new")
    public String saveNotice(Notice notice,RedirectAttributes redirectAttributes){
        accountService.saveNotice(notice);
        redirectAttributes.addFlashAttribute("message", "新增公告成功");
        return "redirect:/home";
    }

    /**跳转到首页
     * @return
     */
    @GetMapping("/index")
    public String home(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                       Model model){
        PageInfo<Notice> pageInfo = accountService.findAllNoticeByPageNo(pageNo);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    /**跳转到注册页面
     * @return
     */
    @GetMapping("/regist")
    public String regist(Model model){
        model.addAttribute("token",qiniustore.getUploadToken());
        return "regist";
    }

    /**保存注册用户
     * @param account
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/regist")
    public String saveUser(Account account, RedirectAttributes redirectAttributes){
        try {
            accountService.saveAcc(account);
            redirectAttributes.addFlashAttribute("message", "用户注册成功");
            return "redirect:/";
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message","注册失败");
        }
        return "redirect:/regist";
    }

    /**跳转到忘记密码验证问题页面
     * @return
     */
    @GetMapping("/losepass")
    public String losepass(){
        return "validate";
    }

    /**获取回答的问题答案，回答正确之后进行设置新密码，问题有错误进行刷新
     * @param account
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/losepass")
    public String lose(Account account, RedirectAttributes redirectAttributes){
        try {
            accountService.validate(account);
            redirectAttributes.addFlashAttribute("message", "验证通过，请设置新密码");
            redirectAttributes.addFlashAttribute("mobile", account.getMobile());
            return "redirect:/pass";
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/losepass";
    }

    /**忘记密码之后直接设置新密码
     * @return
     */
    @GetMapping("/pass")
    public String newpass(){
        return "pass";
    }

    /**保存新设置的密码，
     * @param password
     * @return
     */
    @PostMapping("/pass")
    public String savenew(String password,String mobile){

        accountService.makepass(password,mobile);
        return "redirect:/";
    }
}

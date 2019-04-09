package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import com.fdy.util.ShiroUtil;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String home(Model model){
        Account account = shiroUtil.getCurrAcc();
        String role = account.getRole();
        model.addAttribute("role",role);
        return "home";
    }

    /**跳转到权限不足页面
     * @return
     */
    @GetMapping("/401")
    public String error(Model model){
        Account account = shiroUtil.getCurrAcc();
        String role = account.getRole();
        model.addAttribute("role",role);
        return "/error/401";
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
        Account account = shiroUtil.getCurrAcc();
        String role = account.getRole();
        model.addAttribute("role",role);
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
}

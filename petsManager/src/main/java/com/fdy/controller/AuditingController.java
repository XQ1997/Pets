package com.fdy.controller;

import com.fdy.entity.Account;
import com.fdy.entity.Cliam;
import com.fdy.entity.Pets;
import com.fdy.exception.NotFoundException;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import com.fdy.service.PetsService;
import com.fdy.util.AjaxResponseData;
import com.fdy.util.ShiroUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**宠物认领审核控制器
 * @author fdy
 */
@Controller
@RequestMapping("/auditing")
public class AuditingController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PetsService petsService;
    @Autowired
    private ShiroUtil shiroUtil;

    /**跳转到宠物申请认领审核管理首页
     * @return
     */
    @GetMapping
    public String petManager(@RequestParam(name = "pageNo",required = false,defaultValue = "1") Integer pageNo,
                             @RequestParam(required = false)String petname,
                             @RequestParam(required = false)String state,
                             @RequestParam(required = false)String age,Model model){

        Map<String,Object> selectMap = new HashMap<>();
        selectMap.put("petname",petname);
        selectMap.put("state",state);
        selectMap.put("age",age);

        PageInfo<Pets> pageInfo = petsService.findAllByMapandPageNo(pageNo,selectMap);
        model.addAttribute("pageInfo",pageInfo);
        return "auditing/home";
    }

    /**跳转到宠物认领审核页，根据当前的宠物名称获取申请记录对象
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String look(@PathVariable Integer id, Model model){
        try {
            Pets pets = petsService.findById(id);
            if(pets == null){
                throw new NotFoundException();
            }
            List<Cliam> cliamList = accountService.findCliamByPets(pets.getPetname());
            for(Cliam cliam : cliamList){
                Account account = accountService.findByMobile(cliam.getMobile());
                model.addAttribute("account", account);
                model.addAttribute("cliam", cliam);
            }
            model.addAttribute("cliamList",cliamList);
            model.addAttribute("pets", pets);
        }catch (ServiceException e){
            model.addAttribute("message",e.getMessage());
        }
        return "auditing/auditing";
    }

    /**审核
     * @param id
     * @param cliamId
     * @param state
     * @return
     */
    @GetMapping("/{id:\\d+}/auditing")
    @ResponseBody
    public AjaxResponseData changeState(@PathVariable Integer id, String cliamId,String state){
        try{
            Account account = shiroUtil.getCurrAcc();
            petsService.auditing(id,cliamId,account,state);
            return AjaxResponseData.success();
        }catch (ServiceException e){
            return AjaxResponseData.error(e.getMessage());
        }
    }
}

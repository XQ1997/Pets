package com.nf.util;

import com.nf.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**shiro工具类--用来获取当前对象
 * @author fdy
 */
@Component
public class ShiroUtil {

    public Account getCurrAcc(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return account;
    }

}

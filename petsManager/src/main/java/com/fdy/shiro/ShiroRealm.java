package com.fdy.shiro;

import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
    //获得日志对象
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private AccountService accountService;

    /**判断角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //从认证器中获得输入的电话
        String mobile = usernamePasswordToken.getUsername();
        if(mobile != null){
            //根据获得的电话获得用户对象
            Account account = accountService.findByMobile(mobile);
            if(account == null){
                throw new ServiceException("找不到账户" + mobile);
            }else{
               if(Account.TYPE_ADMIN.equals(account.getRole())){
                   //usernamePasswordToken.getHost()获得认证对象传过来的ip地址
                   logger.info("{},登录了系统{}",account,usernamePasswordToken.getHost());
                   //返回一个简单的认证消息 第一个参数为使用getPrincipal()方法时返回的值，第二个参数为凭证，第三个参数为realm类的名(实现Realm接口中getName()方法中声明)
                   return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
               }else{
                   throw new LockedAccountException("账户锁定或者禁用" + account.getRole());
               }
            }
        }
        return null;
    }
}

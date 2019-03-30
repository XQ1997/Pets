package com.fdy.service;

import org.apache.commons.codec.digest.DigestUtils;

import com.fdy.dao.AccountDao;
import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.util.Config;

/**用户业务类
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class AccountService {

	AccountDao accDao = new AccountDao();
	
	/**用户登录判断
	 * @param username 用户名
	 * @param password 密码
	 * @return account对象
	 */
	public Account login(String username, String password) {
		Account account = accDao.findByMobile(username);
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		
		if(account != null && md5Password.equals(account.getPassword())) {
			 return account;
		} else {
			throw new ServiceException("用户名或者密码错误");
		}
	}
	
}

package com.fdy.service;

import java.util.Date;

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

	/**保存新注册用户
	 * @param account
	 * @author fdy
	 * @date 2019年3月30日	
	 */
	public void saveAccount(Account account) throws ServiceException{
		 //先判断电话号码是否存在
		Account oldaccount = accDao.findByMobile(account.getMobile());
		if(oldaccount != null) {
			throw new ServiceException("该电话号码已经存在");
		}
		
		String md5Password = DigestUtils.md5Hex(account.getPassword() + Config.get("user.password.salt"));
		account.setPassword(md5Password);
		account.setUpdateTime(new Date());
		
        accDao.saveAccount(account); 		
	}

	/**修改密码
	 * @param oldpass
	 * @param newpass
	 * @author fdy
	 * @param account 
	 * @date 2019年3月31日	
	 */
	public void repass(Account account, String oldpass, String newpass) throws ServiceException{
		if(!(DigestUtils.md5Hex(oldpass + Config.get("user.password.salt"))).equals(account.getPassword())) {
			 throw new ServiceException("原始密码错误");
		 }
		 if(oldpass.equals(newpass)) {
			 throw new ServiceException("新密码不能和原始密码相同");
		 }
		 account.setPassword(DigestUtils.md5Hex(newpass + Config.get("user.password.salt")));
		 accDao.repass(account);
	}
	
}

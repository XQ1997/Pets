package com.fdy.service;

import org.apache.commons.codec.digest.DigestUtils;

import com.fdy.dao.AccountDao;
import com.fdy.entity.Account;
import com.fdy.exception.ServiceException;
import com.fdy.util.Config;

/**�û�ҵ����
 * @author fdy
 * @date 2019��3��27��
 * @Version
 */
public class AccountService {

	AccountDao accDao = new AccountDao();
	
	/**�û���¼�ж�
	 * @param username �û���
	 * @param password ����
	 * @return account����
	 */
	public Account login(String username, String password) {
		Account account = accDao.findByMobile(username);
		String md5Password = DigestUtils.md5Hex(password + Config.get("user.password.salt"));
		
		if(account != null && md5Password.equals(account.getPassword())) {
			 return account;
		} else {
			throw new ServiceException("�û��������������");
		}
	}
	
}

package com.fdy.service;

import java.util.Date;

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

	/**������ע���û�
	 * @param account
	 * @author fdy
	 * @date 2019��3��30��	
	 */
	public void saveAccount(Account account) throws ServiceException{
		 //���жϵ绰�����Ƿ����
		Account oldaccount = accDao.findByMobile(account.getMobile());
		if(oldaccount != null) {
			throw new ServiceException("�õ绰�����Ѿ�����");
		}
		
		String md5Password = DigestUtils.md5Hex(account.getPassword() + Config.get("user.password.salt"));
		account.setPassword(md5Password);
		account.setUpdateTime(new Date());
		
        accDao.saveAccount(account); 		
	}

	/**�޸�����
	 * @param oldpass
	 * @param newpass
	 * @author fdy
	 * @param account 
	 * @date 2019��3��31��	
	 */
	public void repass(Account account, String oldpass, String newpass) throws ServiceException{
		if(!(DigestUtils.md5Hex(oldpass + Config.get("user.password.salt"))).equals(account.getPassword())) {
			 throw new ServiceException("ԭʼ�������");
		 }
		 if(oldpass.equals(newpass)) {
			 throw new ServiceException("�����벻�ܺ�ԭʼ������ͬ");
		 }
		 account.setPassword(DigestUtils.md5Hex(newpass + Config.get("user.password.salt")));
		 accDao.repass(account);
	}
	
}

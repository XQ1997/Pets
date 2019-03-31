package com.fdy.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.fdy.entity.Account;
import com.fdy.util.DbHelp;

/**�û�ҵ��������ݿ���
 * @author fdy
 * @date 2019��3��27��
 * @Version
 */
public class AccountDao {

	/**���ݵ绰�����û�
	 * @param username
	 * @return
	 * @author fdy
	 * @date 2019��3��27��	
	 */
	public Account findByMobile(String mobile) {
		String sql = "select * from t_account where mobile = ?";
		return DbHelp.query(sql, new BeanHandler<>(Account.class, new BasicRowProcessor(new GenerousBeanProcessor())), mobile);
	}

	/** ������ע���û�
	 * @param account
	 * @author fdy
	 * @date 2019��3��30��	
	 */
	public void saveAccount(Account account) {
		String sql = "insert into t_account (role,username,age,sex,address,cardnum,job,password,user_photo,card_in_photo,card_out_photo,mobile,update_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, account.getRole(),account.getUsername(),account.getAge(),account.getSex(),account.getAddress(),account.getCardnum(),account.getJob(),account.getPassword(),account.getUserPhoto(),account.getCardInPhoto(),account.getCardOutPhoto(),account.getMobile(),account.getUpdateTime());		
	}

	/**�޸�����
	 * @param account
	 * @author fdy
	 * @date 2019��3��31��	
	 */
	public void repass(Account account) {
		String sql = "update t_account set password = ?";
		DbHelp.executeUpdate(sql, account.getPassword());		
	}
	
}

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
	
}

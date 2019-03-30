package com.fdy.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.fdy.entity.Account;
import com.fdy.util.DbHelp;

/**用户业务操作数据库类
 * @author fdy
 * @date 2019年3月27日
 * @Version
 */
public class AccountDao {

	/**根据电话查找用户
	 * @param username
	 * @return
	 * @author fdy
	 * @date 2019年3月27日	
	 */
	public Account findByMobile(String mobile) {
		String sql = "select * from t_account where mobile = ?";
		return DbHelp.query(sql, new BeanHandler<>(Account.class, new BasicRowProcessor(new GenerousBeanProcessor())), mobile);
	}
	
}

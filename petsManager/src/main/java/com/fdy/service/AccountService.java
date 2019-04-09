package com.fdy.service;

import com.fdy.entity.Account;
import com.fdy.entity.AccountExample;
import com.fdy.entity.Cliam;
import com.fdy.entity.CliamExample;
import com.fdy.exception.ServiceException;
import com.fdy.mapper.AccountMapper;
import com.fdy.mapper.CliamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CliamMapper cliamMapper;

    /**
     * 根据电话获得用户对象
     * @param mobile
     * @return
     */
    public Account findByMobile(String mobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andMobileEqualTo(mobile);

        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList != null && !accountList.isEmpty()){
            return accountList.get(0);
        }
        return null;
    }

    /**修改密码
     * @param account 当前登录账户
     * @param oldpass 旧密码
     * @param newpass 新密码
     * @throws ServiceException 将原因通过异常进行抛出
     */
    public void repassword(Account account, String oldpass, String newpass) throws ServiceException {
        if(DigestUtils.md5Hex(oldpass).equals(account.getPassword())){
            account.setPassword(DigestUtils.md5Hex(newpass));
            accountMapper.updateByPrimaryKeySelective(account);
        }else{
            throw new ServiceException("输入原密码错误，请重新输入");
        }
    }

    /**保存注册用户
     * @param account 用户信息
     */
    public void saveAcc(Account account)throws ServiceException {
        Account oldAcc = findByMobile(account.getMobile());
        if(oldAcc != null){
            throw new ServiceException("该电话号码已注册过，请检查！");
        }
        accountMapper.insertSelective(account);
        logger.info("{}注册成功",account);
    }

    /**保存新的宠物认领申请记录
     *
     * @param account
     * @param cliam　宠物认领申请记录对象
     * @throws ServiceException　错误原因通过异常抛出
     */
    public void saveCliam(Account account, Cliam cliam) throws ServiceException {
        Account oldAcc = findByMobile(cliam.getMobile());
        if(oldAcc == null){
            throw new ServiceException("该用户未注册，请注册后再申请宠物认领");
        }
        cliam.setUsername(account.getUsername());
        cliam.setState(Cliam.STATE_IN);
        cliamMapper.insertSelective(cliam);
        logger.info("{}申请提交成功",cliam);
    }

    /**根据电话查询宠物认领申请记录
     * @param mobile
     * @return
     */
    public List<Cliam> findCliamBymobile(String mobile) {
        CliamExample cliamExample = new CliamExample();
        cliamExample.createCriteria().andMobileEqualTo(mobile);
        List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
        return cliamList;
    }

    /**根据id查询当前的用户
     * @param id
     * @return
     */
    public Account findById(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    /**更新当前用户信息
     * @param account
     */
    public void updateAcc(Account account) {
        account.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);
        logger.info("{}用户更新成功",account);
    }

    /**删除用户根据id
     * @param id
     */
    public void del(Integer id)throws ServiceException {
        Account oldAcc = findById(id);
        if(oldAcc == null){
            throw new ServiceException("该用户不存在，请检查！");
        }
        accountMapper.deleteByPrimaryKey(id);
        logger.info("{}用户删除成功",oldAcc);
    }

    /** 根据页码和搜索条件进行查询
     * @param pageNo 页数
     * @param selectMap 搜索条件map
     * @return
     */
    public PageInfo<Account> findAllByMapandPageNo(Integer pageNo, Map<String, Object> selectMap) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);

        //接收传过来的搜索条件值
        String username = (String)selectMap.get("username");
        String mobile = (String)selectMap.get("mobile");
        String role = (String)selectMap.get("role");

        AccountExample accountExample = new AccountExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        AccountExample.Criteria  criteria = accountExample.createCriteria();
        if(StringUtils.isNotEmpty(username)){
            criteria.andUsernameLike("%" + username + "%");
        }
        if(StringUtils.isNotEmpty(mobile)){
            criteria.andMobileLike("%" + mobile + "%");
        }
        if(StringUtils.isNotEmpty(role)){
            criteria.andRoleEqualTo(role);
        }
        //设置降序
        accountExample.setOrderByClause("id desc");

        List<Account> accountList = accountMapper.selectByExample(accountExample);
        return new PageInfo<>(accountList);
    }

    /**根据搜索条件和页数返回相应的申请认领记录
     * @param pageNo 页数
     * @param selectMap 搜索条件
     * @return
     */
    public PageInfo<Cliam> findAllCLiamsByMapandPageNo(Integer pageNo, Map<String, Object> selectMap) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);

        //接收传过来的搜索条件值
        String username = (String)selectMap.get("username");
        String mobile = (String)selectMap.get("mobile");
        String state = (String)selectMap.get("state");

        CliamExample cliamExample = new CliamExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        CliamExample.Criteria  criteria = cliamExample.createCriteria();
        if(StringUtils.isNotEmpty(username)){
            criteria.andUsernameLike("%" + username + "%");
        }
        if(StringUtils.isNotEmpty(mobile)){
            criteria.andMobileLike("%" + mobile + "%");
        }
        if(StringUtils.isNotEmpty(state)){
            criteria.andStateEqualTo(state);
        }
        //设置降序
        cliamExample.setOrderByClause("id desc");

        List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
        return new PageInfo<>(cliamList);
    }

    /**根据id查询对应的申请认领记录
     * @param id
     * @return
     */
    public Cliam findCliamById(Integer id) {
        return cliamMapper.selectByPrimaryKey(id);
    }

    /**根据电话查询对应的用户
     * @param mobile 电话
     * @return
     */
    public Account findAccByMobile(String mobile) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andMobileEqualTo(mobile);
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList != null && !accountList.isEmpty()){
            return accountList.get(0);
        }
        return null;
    }

    /**根据宠物名获取对应的申请记录
     * @param petname
     * @return
     */
    public Cliam findCliamByPets(String petname)throws ServiceException {
        CliamExample cliamExample = new CliamExample();
        cliamExample.createCriteria().andPetnameEqualTo(petname);
        List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
        if(cliamList != null && !cliamList.isEmpty()){
            return cliamList.get(0);
        } else{
            throw new ServiceException("该宠物未被申请认领！");
        }

    }
}

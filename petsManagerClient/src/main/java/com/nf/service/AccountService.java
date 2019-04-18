package com.nf.service;

import com.nf.entity.*;
import com.nf.exception.ServiceException;
import com.nf.mapper.*;
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
    @Autowired
    private WordsMapper wordsMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ReplyMapper replyMapper;

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

        //根据注册角色查询已注册该角色的用户数量
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andRoleEqualTo(Account.TYPE_USER);
        List<Account> accountList = accountMapper.selectByExample(accountExample);


        account.setNumber(Account.TYPE_USER +(accountList.size() + 1) + "号");
        account.setRole(Account.TYPE_USER);
        account.setPassword(DigestUtils.md5Hex(account.getPassword()));
        accountMapper.insertSelective(account);
        logger.info("{}注册成功",account);
    }

    /**保存新的宠物认领申请记录
     * @param cliam　宠物认领申请记录对象
     * @throws ServiceException　错误原因通过异常抛出
     */
    public void saveCliam(Cliam cliam) throws ServiceException {
        Account oldAcc = findByMobile(cliam.getMobile());
        if(oldAcc == null){
            throw new ServiceException("该用户未注册，请注册后再申请宠物认领");
        }
        //根据申请记录中是否有该宠物的申请并且该申请的状态为未通过，如果有则提交失败，没有则提交成功
        CliamExample cliamExample = new CliamExample();
        cliamExample.createCriteria().andCliamNameEqualTo(cliam.getPetname()).andStateEqualTo(Cliam.STATE_PASS);
        List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
        if(cliamList != null && !cliamList.isEmpty()){
            throw new ServiceException("该宠物已经被认领，请重新选择宠物！");
        }
        cliam.setUsername(oldAcc.getUsername());
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

    /**对问题进行验证
     * @param account
     */
    public void validate(Account account)throws ServiceException {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andMobileEqualTo(account.getMobile());
        List<Account> accountList = accountMapper.selectByExample(accountExample);
        if(accountList != null && !accountList.isEmpty()){
            Account dacc = accountList.get(0);
            if(account.getJob() == null && account.getJob() != dacc.getJob()){
                throw new ServiceException("信息填写错误，验证失败！");
            }
            if(account.getAddress() == null && account.getAddress() != dacc.getAddress()){
                throw new ServiceException("信息填写错误，验证失败！");
            }
            if(account.getCardnum() == null && account.getCardnum() != dacc.getCardnum()){
                throw new ServiceException("信息填写错误，验证失败！");
            }
        }else{
            throw new ServiceException("该用户尚未注册，验证失败！");
        }


    }

    /**
     * 直接设置新密码
     * @param password
     * @param mobile
     */
    public void makepass(String password, String mobile) {
        Account account = findByMobile(mobile);
        account.setPassword(DigestUtils.md5Hex(password));
        account.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);
    }

    public void saveWords(Words words, Account currAcc) {
        words.setUsername(currAcc.getUsername());
        wordsMapper.insertSelective(words);
        logger.info("留言{}成功",words);
        //保存公告
        Notice notice = new Notice();
        notice.setTitle(words.getTitle() + "留言");
        notice.setContent(words.getContent());
        noticeMapper.insertSelective(notice);
        logger.info("{}公告新增成功");
    }

    /**根据页码显示相应的公告
     * @param pageNo
     * @return
     */
    public PageInfo<Notice> findAllNoticeByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,5);
        List<Notice> noticeList = noticeMapper.selectByExample(new NoticeExample());
        return new PageInfo<>(noticeList);
    }
}

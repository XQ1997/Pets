package com.fdy.service;

import com.fdy.entity.*;
import com.fdy.exception.ServiceException;
import com.fdy.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        accountExample.createCriteria().andRoleEqualTo(account.getRole());
        List<Account> accountList = accountMapper.selectByExample(accountExample);


        account.setNumber(account.getRole() +(accountList.size() + 1) + "号");
        account.setPassword(DigestUtils.md5Hex(account.getPassword()));
        account.setCreateTime(new Date());
        accountMapper.insertSelective(account);
        logger.info("{}注册成功",account);
    }

    /**保存新的宠物认领申请记录
     * @param account
     * @param cliam　宠物认领申请记录对象
     * @throws ServiceException　错误原因通过异常抛出
     */
    public void saveCliam(Account account, Cliam cliam) throws ServiceException {
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
        cliam.setUsername(account.getUsername());
        cliam.setState(Cliam.STATE_IN);
        cliam.setCreateTime(new Date());
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
     * @param account 当前登录用户
     */
    public void del(Integer id, Account account)throws ServiceException {
        Account oldAcc = findById(id);
        if(oldAcc == null){
            throw new ServiceException("该用户不存在，请检查！");
        }

        if(account.getMobile().equals(oldAcc.getMobile())) {
            throw new ServiceException("该账户正在使用，使用失败");
        }

        //根据注册角色查询已注册该角色的用户数量
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andRoleEqualTo(oldAcc.getRole());
        List<Account> accountList = accountMapper.selectByExample(accountExample);

        for(int i = 1; i <= accountList.size(); i++) {
            accountList.get(i).setNumber( accountList.get(i).getRole() + i + "号");
            accountMapper.updateByPrimaryKeySelective(accountList.get(i));
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

    /**保存留言信息
     * @param words 留言信息
     * @param account 当前用户
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void savewords(Words words, Account account) {
        words.setUsername(account.getUsername());
        wordsMapper.insertSelective(words);
        logger.info("{}留言{}成功",account,words);
        //保存公告
         Notice notice = new Notice();
         notice.setTitle(words.getTitle() + "留言");

         notice.setContent(words.getContent());
         notice.setCreateTime(new Date());
         noticeMapper.insertSelective(notice);
         logger.info("{}公告新增成功");
    }

    /**查询所有的公告 根据搜索条件和页码搜索
     * @param pageNo
     * @param selectMap
     * @return
     */
    public PageInfo<Notice> findAllNoticeByMapandPageNo(Integer pageNo, Map<String, Object> selectMap) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);

        //接收传过来的搜索条件值
        String title = (String)selectMap.get("title");
        String createTime = (String)selectMap.get("createTime");

        NoticeExample noticeExample = new NoticeExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        NoticeExample.Criteria  criteria = noticeExample.createCriteria();
        if(StringUtils.isNotEmpty(title)){
            criteria.andTitleLike("%" + title + "%");
        }
        if(StringUtils.isNotEmpty(createTime)){
            try {
                criteria.andCreateTimeEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse(createTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //设置降序
        noticeExample.setOrderByClause("id desc");

        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
        return new PageInfo<>(noticeList);
    }

    /**保存新增公告
     * @param notice
     */
    public void saveNotice(Notice notice) {
        notice.setCreateTime(new Date());
        noticeMapper.insertSelective(notice);
        logger.info("新增公告{}",notice);
    }

    /**删除公告
     * @param id
     */
    public void delNotice(Integer id)throws ServiceException {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        if(notice != null){
            noticeMapper.deleteByPrimaryKey(id);
        }else{
          throw new ServiceException("该用户不存在，删除失败！");
        }
    }

    /**根据id查询对应的notice公告
     * @param id
     * @return
     */
    public Notice findNoticeByid(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    /**保存更新后的公告信息
     * @param notice
     */
    public void updateNotice(Notice notice) {
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    /**查询所有的留言记录根据搜索条件和页数
     * @param pageNo 页数
     * @param selectMap 搜索条件
     * @return
     */
    public PageInfo<Words> findAllWordsByMapandPageNo(Integer pageNo, Map<String, Object> selectMap) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);

        //接收传过来的搜索条件值
        String title = (String)selectMap.get("title");
        String createTime = (String)selectMap.get("createTime");

        WordsExample wordsExample = new WordsExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        WordsExample.Criteria  criteria = wordsExample.createCriteria();
        if(StringUtils.isNotEmpty(title)){
            criteria.andTitleLike("%" + title + "%");
        }
        if(StringUtils.isNotEmpty(createTime)){
            try {
                criteria.andCreateTimeEqualTo(new SimpleDateFormat("yyyy-MM-dd").parse(createTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //设置降序
        wordsExample.setOrderByClause("id desc");

        List<Words> wordsList = wordsMapper.selectByExample(wordsExample);
        return new PageInfo<>(wordsList);
    }

    /** 保存回复消息
     * @param reply
     * @param id
     */
    public void saveReply(Reply reply, Integer id) {
        Words words = wordsMapper.selectByPrimaryKey(id);
        if(words != null){
            reply.setWordname(words.getUsername());
            reply.setCreateTime(new Date());
            replyMapper.insertSelective(reply);
            logger.info("{}回复成功",reply);
        }
    }

    /**删除留言
     * @param id
     */
    public void delWords(Integer id) throws ServiceException{
        Words words = wordsMapper.selectByPrimaryKey(id);
        if(words != null){
            wordsMapper.deleteByPrimaryKey(id);
        }else{
            throw new ServiceException("该留言不存在，删除失败！");
        }
    }

    /**根据id查询对应的留言记录
     * @param id
     * @return
     */
    public Words findWordsById(Integer id) {
        return wordsMapper.selectByPrimaryKey(id);
    }

    /**根据留言中的留言人姓名作为回复中的留言人查询所有回复内容
     * @param username
     * @return
     */
    public List<Reply> findALLReply(String username) {
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andWordnameEqualTo(username);
        List<Reply> replyList = replyMapper.selectByExample(replyExample);
        return replyList;
    }

    /**仅根据页码进行显示list
     * @param pageNo
     * @return
     */
    public PageInfo<Notice> findAllNoticeByPageNo(Integer pageNo) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);
        NoticeExample noticeExample = new NoticeExample();
        //设置降序
        noticeExample.setOrderByClause("id desc");

        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
        return new PageInfo<>(noticeList);
    }

    /**对忘记密码进行问题验证
     * @param account
     */
    public void validate(Account account) {
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

    /**直接根据电话号码进行设置新密码
     * @param password
     * @param mobile
     */
    public void makepass(String password, String mobile) {
        Account account = findByMobile(mobile);
        account.setPassword(DigestUtils.md5Hex(password));
        account.setUpdateTime(new Date());
        accountMapper.updateByPrimaryKeySelective(account);
    }
}

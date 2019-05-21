package com.fdy.service;

import com.fdy.entity.*;
import com.fdy.exception.ServiceException;
import com.fdy.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PetsService {

    private Logger logger = LoggerFactory.getLogger(PetsService.class);

    @Autowired
    private PetsMapper petsMapper;
    @Autowired
    private CliamMapper cliamMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private FodderMapper fodderMapper;
    @Autowired
    private UserpetMapper userpetMapper;

    /**根据页码和搜索条件查询符合条件的流浪宠物
     * @param pageNo
     * @param selectMap
     * @return
     */
    public PageInfo<Pets> findAllByMapandPageNo(Integer pageNo, Map<String, Object> selectMap) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);

        //接收传过来的搜索条件值
        String petname = (String)selectMap.get("petname");
        String state = (String)selectMap.get("state");
        String type = (String)selectMap.get("type");

        PetsExample petsExample = new PetsExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        PetsExample.Criteria criteria = petsExample.createCriteria();
        if(StringUtils.isNotEmpty(petname)){
            criteria.andPetnameLike("%" + petname + "%");
        }
        if(StringUtils.isNotEmpty(state)){
            criteria.andStateEqualTo(state);
        }
        if(StringUtils.isNotEmpty(type)){
            criteria.andTypeEqualTo(type);
        }
        //设置降序
        petsExample.setOrderByClause("id desc");

        List<Pets> petsList = petsMapper.selectByExample(petsExample);
        return new PageInfo<>(petsList);
    }

    /**保存新增的流浪宠物
     * @param pets 流浪宠物信息
     * @throws ServiceException 错误原因通过异常进行抛出
     */
    public void savepets(Pets pets) throws ServiceException {
        PetsExample petsExample = new PetsExample();
        petsExample.createCriteria().andPetnameEqualTo(pets.getPetname());
        List<Pets> petsList = petsMapper.selectByExample(petsExample);
        if(petsList != null && !petsList.isEmpty()){
            throw new ServiceException("该流浪宠物名已存在，请重新命名！");
        }

        pets.setState(Pets.STATE_NO);
        pets.setCreateTime(new Date());
        petsMapper.insertSelective(pets);

        Notice notice = new Notice();
        notice.setTitle("流浪宠物发布");
        notice.setContent(pets.getContent());
        notice.setCreateTime(new Date());
        noticeMapper.insertSelective(notice);
    }

    /**根据id查询该流浪宠物
     * @param id 宠物id
     * @return
     */
    public Pets findById(Integer id) {
        return petsMapper.selectByPrimaryKey(id);
    }

    /** 保存修改后的流浪宠物信息
     * @param pets
     */
    public void updatepet(Pets pets) throws ServiceException {
        PetsExample petsExample = new PetsExample();
        petsExample.createCriteria().andPetnameEqualTo(pets.getPetname());
        List<Pets> petsList = petsMapper.selectByExample(petsExample);
        if(petsList != null && !petsList.isEmpty()){
            throw new ServiceException("该宠物名称已存在，请重新命名！");
        }
        pets.setUpdateTime(new Date());
        petsMapper.updateByPrimaryKeySelective(pets);
    }

    /**删除流浪宠物
     * @param id
     * @throws ServiceException
     */
    public void del(Integer id)throws ServiceException {
        Pets pets = findById(id);
        if(pets == null){
            throw new ServiceException("该流浪宠物不存在，请检查！");
        }

        petsMapper.deleteByPrimaryKey(id);
        logger.info("{}宠物删除成功",pets);
    }
	
	/**删除饲料库存
     * @param id
     * @throws ServiceException
     */
    public void delFodder(Integer id)throws ServiceException {
        Fodder fodder = fodderMapper.selectByPrimaryKey(id); 
        if(fodder == null){
            throw new ServiceException("该饲料库存不存在，请检查！");
        }

        fodderMapper.deleteByPrimaryKey(id); 
    }

    /**审核该申请
     * @param id
     * @param cliamId 申请记录id
     * @param account
     * @param state
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void auditing(Integer id, String cliamId, Account account, String state)throws ServiceException {
       Cliam dbcliam = cliamMapper.selectByPrimaryKey(Integer.valueOf(cliamId));
       CliamExample cliamExample = new CliamExample();
       cliamExample.createCriteria().andStateEqualTo(Cliam.STATE_PASS);
       List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
       if(cliamList != null && !cliamList.isEmpty()){
           for(Cliam cliams : cliamList){
               if(cliams.getPetname().equals(dbcliam.getPetname())){
                   throw new ServiceException("该宠物已审核通过，其他请求自动放弃审核！");
               }
           }
       }

        //如果选择的是不通过的按钮，修改申请记录为不通过，否则则通过
        if("true".equals(state)){
            Cliam cliam = cliamMapper.selectByPrimaryKey(Integer.valueOf(cliamId));
            cliam.setState(Cliam.STATE_NO);
            cliam.setUpdateTime(new Date());
            cliam.setCliamName(account.getUsername());
            cliamMapper.updateByPrimaryKeySelective(cliam);
        }else {
            Cliam cliam = cliamMapper.selectByPrimaryKey(Integer.valueOf(cliamId));
            cliam.setState(Cliam.STATE_PASS);
            cliam.setUpdateTime(new Date());
            cliam.setCliamName(account.getUsername());
            cliamMapper.updateByPrimaryKeySelective(cliam);

            Pets pets = petsMapper.selectByPrimaryKey(id);
            if(pets == null){
                throw new ServiceException("该宠物不存在，请检查！");
            }
            //查询申请人的姓名，根据电话
            Account acc = new Account();
            AccountExample accountExample = new AccountExample();
            accountExample.createCriteria().andMobileEqualTo(cliam.getMobile());
            List<Account> accountList =  accountMapper.selectByExample(accountExample);
            if(accountList != null){
                acc = accountList.get(0);
            }
            pets.setAccname(acc.getUsername());
            pets.setState(Pets.STATE_CLIAM);
            pets.setUpdateTime(new Date());
            petsMapper.updateByPrimaryKeySelective(pets);

            //审核通过后，将信息存到公告中
            Notice notice = new Notice();
            notice.setTitle("领养成功");
            notice.setContent(acc.getUsername() + "成功领养" + pets.getPetname());
            notice.setCreateTime(new Date());
            noticeMapper.insertSelective(notice);
        }
    }

    /**查找所有的饲料使用情况
     * @return
     * @param selectMap
     */
    public List<Fodder> findAllFooder(Map<String, Object> selectMap) {
        //接收传过来的搜索条件值
        String type = (String)selectMap.get("type");
        FodderExample fodderExample = new FodderExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        FodderExample.Criteria criteria = fodderExample.createCriteria();
        if(StringUtils.isNotEmpty(type)){
            criteria.andTypeEqualTo(type);
        }
        //设置降序
        fodderExample.setOrderByClause("id desc");

        List<Fodder> fodderList = fodderMapper.selectByExample(fodderExample);

        return fodderList;
    }

    /**保存新增的饲料使用情况
     * @param fodder
     */
    public void saveFodder(Fodder fodder)throws ServiceException {
        List<Fodder> fodderList = fodderMapper.selectByExample(new FodderExample());
        if(fodderList!= null && !fodderList.isEmpty()){
            for(Fodder fodd : fodderList){
                if(fodder.getType().equals(fodd.getType())){
                    throw new ServiceException("该饲料已创建库存，请重新选择！");
                }
            }
        }
        Integer number = Integer.valueOf(fodder.getNumber());

        fodder.setNumber(number);
        fodderMapper.insertSelective(fodder);
        logger.info("新增饲料使用情况成功{}",fodder);
    }

    /**
     * 饲料使用数量增加响应数量，
     * @param id
     * @param val
     */
    public void addFodder(Integer id, String val) {
        Fodder fodder = fodderMapper.selectByPrimaryKey(id);
        if(fodder != null){
            Integer num = Integer.valueOf(val);
            Integer number = fodder.getNumber() + num;
            fodder.setNumber(number);
            fodder.setUpdateTime(new Date());
            fodderMapper.updateByPrimaryKeySelective(fodder);
        }
    }

    /**
     * 饲料使用数量减少相应数量
     * @param id
     * @param val
     */
    public void reduceFodder(Integer id, String val) throws ServiceException{
        Fodder fodder = fodderMapper.selectByPrimaryKey(id);
        if(fodder != null){
            Integer num = Integer.valueOf(val);
            Integer number = fodder.getNumber() - Integer.valueOf(val);
            if(number < 0){
                throw new ServiceException("减少数量大于库存数量，操作失败！");
            }
            fodder.setNumber(number);
            fodder.setUpdateTime(new Date());
            fodderMapper.updateByPrimaryKeySelective(fodder);
        }
    }

    /**查找所有的宠物
     * @return
     */
    public List<Pets> findAllPets() {
        return petsMapper.selectByExample(new PetsExample());
    }

    /**根据id查询到公告信息
     * @param id
     * @return
     */
    public Notice findNoticeById(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    /**用户确认领养宠物
     * @param id
     * @param account
     * @param val
     */
    public void confirm(Integer id, Account account, String val) {
        Userpet userpet = userpetMapper.selectByPrimaryKey(id);
        if(userpet != null){
            userpet.setState("已" + userpet.getSendtype());
            userpetMapper.updateByPrimaryKeySelective(userpet);
            Cliam cliam = new Cliam();
            cliam.setCliamName(account.getUsername());
            cliam.setUsername(val);
            cliam.setMobile(account.getMobile());
            cliam.setPetname(userpet.getPetname());
            cliam.setContent(account.getUsername() + "发布，学名为：\"" + userpet.getPetLanguageName() + "\"的宠物：" + userpet.getPetname()+ "现已让" + val + userpet.getSendtype());
            cliam.setCreateTime(new Date());
            cliamMapper.insertSelective(cliam);
        }
        CliamExample cliamExample = new CliamExample();
        cliamExample.createCriteria().andMobileEqualTo(userpet.getMobile());

        List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
        if(cliamList != null && !cliamList.isEmpty()){
            for(Cliam cliam : cliamList){
                if(userpet.getMobile().equals(cliam.getMobile()) && cliam.getState() == null){
                    cliam.setUsername(val);
                    cliam.setUpdateTime(new Date());
                    cliamMapper.updateByPrimaryKeySelective(cliam);
                }
            }
        }
    }

    /**查询所有的用户发布的宠物
     * @return
     */
    public List<Userpet> findAllUserPets() {
        return userpetMapper.selectByExample(new UserpetExample());
    }

    /**保存用户发布的宠物信息
     * @param userpet
     * @param username
     */
    public void saveUserpets(Userpet userpet, String username)throws ServiceException {
        userpet.setState("未" + userpet.getSendtype());
        userpet.setUsername(username);
        userpet.setCreateTime(new Date());
        userpetMapper.insertSelective(userpet);
    }

    /**根据id查询到对应的用户发布的宠物信息
     * @param id
     * @return
     */
    public Userpet findUserPetById(Integer id) {
        return userpetMapper.selectByPrimaryKey(id);
    }

    /**根据发布人的电话以及发布的宠物名称获取相应的记录
     * @param petname
     * @param mobile
     * @return
     */
    public Cliam findBymobileAndname(String petname, String mobile) {
        CliamExample cliamExample = new CliamExample();
        cliamExample.createCriteria().andMobileEqualTo(mobile);
        List<Cliam> cliamList = cliamMapper.selectByExample(cliamExample);
        if(cliamList != null && !cliamList.isEmpty()){
            for(Cliam cliam : cliamList){
                if(cliam.getPetname().equals(petname)){
                    return cliam;
                }
            }
        }
        return null;
    }

    /**根据搜索条件和页码获取相应的用户发布的宠物信息
     * @param pageNo
     * @param selectMap
     * @return
     */
    public PageInfo<Userpet> findAllUserPetsByMapandPageNo(Integer pageNo, Map<String, Object> selectMap) {
        //因为是单表查询，所以使用Example类中的方法也可以实现过滤查询
        PageHelper.startPage(pageNo,5);

        //接收传过来的搜索条件值
        String petname = (String)selectMap.get("petname");
        String state = (String)selectMap.get("state");

        UserpetExample userpetExample = new UserpetExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        UserpetExample.Criteria criteria = userpetExample.createCriteria();
        if(StringUtils.isNotEmpty(petname)){
            criteria.andPetnameLike("%" + petname + "%");
        }
        if(StringUtils.isNotEmpty(state)){
            criteria.andStateEqualTo(state);
        }
        //设置降序
        userpetExample.setOrderByClause("id desc");

        List<Userpet> userpetList = userpetMapper.selectByExample(userpetExample);
        return new PageInfo<>(userpetList);
    }

    public void delUserPet(Integer id)throws ServiceException {
        Userpet userpet = findUserPetById(id);
        if(userpet == null){
            throw new ServiceException("该宠物不存在，请检查！");
        }

        userpetMapper.deleteByPrimaryKey(id);
        logger.info("{}宠物删除成功",userpet);
    }
}

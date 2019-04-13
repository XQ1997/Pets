package com.fdy.service;

import com.fdy.entity.*;
import com.fdy.exception.ServiceException;
import com.fdy.mapper.AccountMapper;
import com.fdy.mapper.CliamMapper;
import com.fdy.mapper.NoticeMapper;
import com.fdy.mapper.PetsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        int num;
        //根据种类查询已存在的数目
        PetsExample example = new PetsExample();
        example.createCriteria().andTypeEqualTo(pets.getType());
        List<Pets> petsnum = petsMapper.selectByExample(example);
        if(petsnum == null){
            num = 1;
        }
         num = petsnum.size();
        pets.setNum(pets.getType() + num + "号");
        pets.setState(Pets.STATE_NO);
        petsMapper.insertSelective(pets);
        logger.info("{}流浪宠物登记成功",pets);

        Notice notice = new Notice();
        notice.setTitle("新增流浪宠物" + pets.getPetname());
        notice.setContent(pets.getContent());
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

    /**审核该申请
     * @param id
     * @param cliamId 申请记录id
     * @param account
     * @param state
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void auditing(Integer id, String cliamId, Account account, String state)throws ServiceException {
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
            noticeMapper.insertSelective(notice);
        }
    }
}

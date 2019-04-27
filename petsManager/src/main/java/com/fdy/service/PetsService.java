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
    private SickMapper sickMapper;
    @Autowired
    private MoneyMapper moneyMapper;

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

        //根据种类查询已存在的数目
        PetsExample example = new PetsExample();
        example.createCriteria().andTypeEqualTo(pets.getType());
        List<Pets> petsnum = petsMapper.selectByExample(example);

        pets.setNum(pets.getType() + (petsnum.size() + 1) + "号");
        pets.setState(Pets.STATE_NO);
        pets.setCreateTime(new Date());
        petsMapper.insertSelective(pets);
        logger.info("{}流浪宠物登记成功",pets);
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
        //根据种类查询已存在的数目
        PetsExample example = new PetsExample();
        example.createCriteria().andTypeEqualTo(pets.getType());
        List<Pets> petsnum = petsMapper.selectByExample(example);

        for(int i = 1; i<petsnum.size(); i++) {
            petsnum.get(i).setNum(petsnum.get(i).getType() + i + "号");
            petsMapper.updateByPrimaryKeySelective(petsnum.get(i));
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
            notice.setCreateTime(new Date());
            noticeMapper.insertSelective(notice);
        }
    }

    /**查找所有的饲料使用情况
     * @return
     */
    public List<Fodder> findAllFooder() {
        return fodderMapper.selectByExample(new FodderExample());
    }

    /**对类型和所对应所花费总价进行一对一对应并进行封装
     * @return
     */
    public List<Map<String, Object>> couunt() {
        return fodderMapper.countByType();
    }

    /**保存新增的饲料使用情况
     * @param fodder
     */
    public void saveFodder(Fodder fodder) {
        Integer number = Integer.valueOf(fodder.getNumber());
        Double price = fodder.getPrice();
        Double totalnum = number * price;

        fodder.setNumber(number);
        fodder.setPrice(price);
        fodder.setTotalnum(totalnum);
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
            Double totalnum = number * fodder.getPrice();
            fodder.setNumber(number);
            fodder.setTotalnum(totalnum);
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
            Double totalnum = number * fodder.getPrice();
            if(number < 0){
                throw new ServiceException("减少数量大于库存数量，操作失败！");
            }
            fodder.setNumber(number);
            fodder.setTotalnum(totalnum);
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

    /** 保存宠物就医记录
     * @param sick
     */
    public void saveSick(Sick sick) {
        sick.setCreateTime(new Date());
        sickMapper.insertSelective(sick);
        logger.info("{}就医记录新增成功",sick);
    }

    /**宠物就医记录根据页码
     * @param pageNo
     * @return
     */
    public PageInfo<Sick> findAllSickByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,5);
        List<Sick> sickList = sickMapper.selectByExample(new SickExample());
        return new PageInfo<>(sickList);
    }

    /**删除宠物就医记录
     * @param id
     */
    public void delSick(Integer id)throws ServiceException {
        Sick sick = sickMapper.selectByPrimaryKey(id);
        if(sick != null){
            sickMapper.deleteByPrimaryKey(id);
        }else{
            throw new ServiceException("删除失败，该记录并不存在！");
        }

    }

    /**根据页码获取对应的捐助记录
     * @param pageNo
     * @return
     */
    public PageInfo<Money> findAllMoneyByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,5);
        List<Money> moneyList = moneyMapper.selectByExample(new MoneyExample());
        return new PageInfo<>(moneyList);
    }

    /**删除对应的捐助记录
     * @param id
     */
    public void delMoney(Integer id)throws ServiceException {
        Money money = moneyMapper.selectByPrimaryKey(id);
        if(money != null){
            moneyMapper.deleteByPrimaryKey(id);
        }else{
            throw new ServiceException("删除失败，该捐助记录不存在！");
        }
    }

    /**保存新增的捐助记录
     * @param money
     */
    public void saveMoney(Money money) {
        money.setCreateTime(new Date());
        moneyMapper.insertSelective(money);
    }

    /**计算当前的花费
     * @return
     */
    public Double countmoney() {
        //查询所有的饲料花费
        List<Fodder> fodderList = fodderMapper.selectByExample(new FodderExample());
        Double totolprice = 0D;
        if(fodderList != null && !fodderList.isEmpty()){
            for(Fodder fodder : fodderList){
                Double price = fodder.getTotalnum();
                totolprice += price;
            }
        }
        //查询所有的宠物就医花费
        List<Sick> sickList = sickMapper.selectByExample(new SickExample());
        Double total = 0D;
        if(sickList != null && !sickList.isEmpty()){
            for(Sick sick : sickList){
                Double money = sick.getMoney();
                total += money;
            }
        }
        //查询所有的捐助款
        List<Money> moneyList = moneyMapper.selectByExample(new MoneyExample());
        Double totalmoney = 0D;
        if(moneyList != null && !moneyList.isEmpty()){
            for(Money money : moneyList){
                Double m = money.getPrice();
                totalmoney += m;
            }
        }
        Double overplus = totalmoney - total - totolprice;
        if(overplus < 0){
            overplus = 0D;
        }
        return overplus;
    }
}

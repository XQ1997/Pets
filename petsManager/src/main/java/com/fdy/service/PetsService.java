package com.fdy.service;

import com.fdy.entity.Cliam;
import com.fdy.entity.Pets;
import com.fdy.entity.PetsExample;
import com.fdy.exception.ServiceException;
import com.fdy.mapper.CliamMapper;
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
        String age = (String)selectMap.get("age");

        PetsExample petsExample = new PetsExample();
        //Criteria是Example类的内部类----这种形式只限于单表查询
        PetsExample.Criteria criteria = petsExample.createCriteria();
        if(StringUtils.isNotEmpty(petname)){
            criteria.andPetnameLike("%" + petname + "%");
        }
        if(StringUtils.isNotEmpty(state)){
            criteria.andStateEqualTo(state);
        }
        if(StringUtils.isNotEmpty(age)){
            Integer a = Integer.valueOf(age);
            criteria.andAgeEqualTo(a);
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
        petsMapper.deleteByPrimaryKey(id);
        logger.info("{}宠物删除成功",pets);
    }

    /**审核该申请
     * @param id
     * @param cliamId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void auditing(Integer id, String cliamId)throws ServiceException {
        Pets pets = petsMapper.selectByPrimaryKey(id);
        if(pets == null){
            throw new ServiceException("该宠物不存在，请检查！");
        }
        pets.setState(Pets.STATE_CLIAM);
        pets.setUpdateTime(new Date());
        petsMapper.updateByPrimaryKeySelective(pets);

        Cliam cliam = cliamMapper.selectByPrimaryKey(Integer.valueOf(cliamId));
        cliam.setState(Cliam.STATE_PASS);
        cliam.setUpdateTime(new Date());
        cliamMapper.updateByPrimaryKeySelective(cliam);
    }
}

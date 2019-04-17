package com.nf.service;

import com.nf.entity.*;
import com.nf.exception.ServiceException;
import com.nf.mapper.*;
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

    /**根据id查询该流浪宠物
     * @param id 宠物id
     * @return
     */
    public Pets findById(Integer id) {
        return petsMapper.selectByPrimaryKey(id);
    }

    /**返回所有的宠物信息
     * @return
     */
    public List<Pets> findAllPets() {
        return petsMapper.selectByExample(new PetsExample());
    }


}

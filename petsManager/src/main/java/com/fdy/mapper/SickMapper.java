package com.fdy.mapper;

import com.fdy.entity.Sick;
import com.fdy.entity.SickExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SickMapper {
    long countByExample(SickExample example);

    int deleteByExample(SickExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sick record);

    int insertSelective(Sick record);

    List<Sick> selectByExample(SickExample example);

    Sick selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sick record, @Param("example") SickExample example);

    int updateByExample(@Param("record") Sick record, @Param("example") SickExample example);

    int updateByPrimaryKeySelective(Sick record);

    int updateByPrimaryKey(Sick record);
}
package com.fdy.mapper;

import com.fdy.entity.Pets;
import com.fdy.entity.PetsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PetsMapper {
    long countByExample(PetsExample example);

    int deleteByExample(PetsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pets record);

    int insertSelective(Pets record);

    List<Pets> selectByExample(PetsExample example);

    Pets selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pets record, @Param("example") PetsExample example);

    int updateByExample(@Param("record") Pets record, @Param("example") PetsExample example);

    int updateByPrimaryKeySelective(Pets record);

    int updateByPrimaryKey(Pets record);
}
package com.nf.mapper;

import com.nf.entity.Cliam;
import com.nf.entity.CliamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CliamMapper {
    long countByExample(CliamExample example);

    int deleteByExample(CliamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cliam record);

    int insertSelective(Cliam record);

    List<Cliam> selectByExample(CliamExample example);

    Cliam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cliam record, @Param("example") CliamExample example);

    int updateByExample(@Param("record") Cliam record, @Param("example") CliamExample example);

    int updateByPrimaryKeySelective(Cliam record);

    int updateByPrimaryKey(Cliam record);
}
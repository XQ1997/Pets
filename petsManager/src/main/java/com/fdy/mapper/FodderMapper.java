package com.fdy.mapper;

import com.fdy.entity.Fodder;
import com.fdy.entity.FodderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FodderMapper {
    long countByExample(FodderExample example);

    int deleteByExample(FodderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Fodder record);

    int insertSelective(Fodder record);

    List<Fodder> selectByExample(FodderExample example);

    Fodder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Fodder record, @Param("example") FodderExample example);

    int updateByExample(@Param("record") Fodder record, @Param("example") FodderExample example);

    int updateByPrimaryKeySelective(Fodder record);

    int updateByPrimaryKey(Fodder record);
}
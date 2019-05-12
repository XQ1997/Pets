package com.fdy.mapper;

import com.fdy.entity.Userpet;
import com.fdy.entity.UserpetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserpetMapper {
    long countByExample(UserpetExample example);

    int deleteByExample(UserpetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userpet record);

    int insertSelective(Userpet record);

    List<Userpet> selectByExample(UserpetExample example);

    Userpet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userpet record, @Param("example") UserpetExample example);

    int updateByExample(@Param("record") Userpet record, @Param("example") UserpetExample example);

    int updateByPrimaryKeySelective(Userpet record);

    int updateByPrimaryKey(Userpet record);
}
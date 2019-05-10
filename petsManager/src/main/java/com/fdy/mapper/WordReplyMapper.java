package com.fdy.mapper;

import com.fdy.entity.WordReply;
import com.fdy.entity.WordReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WordReplyMapper {
    long countByExample(WordReplyExample example);

    int deleteByExample(WordReplyExample example);

    int insert(WordReply record);

    int insertSelective(WordReply record);

    List<WordReply> selectByExample(WordReplyExample example);

    int updateByExampleSelective(@Param("record") WordReply record, @Param("example") WordReplyExample example);

    int updateByExample(@Param("record") WordReply record, @Param("example") WordReplyExample example);
}
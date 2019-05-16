package com.jtr.phoneshop.Mapper;

import com.jtr.phoneshop.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface indexMapper {
    @Select("select * from good where goodstatus=0")
    public List<Good> findGoodStatus();
}

package com.jtr.phoneshop.Service;

import com.jtr.phoneshop.Mapper.indexMapper;
import com.jtr.phoneshop.pojo.Good;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IndexService {
    @Autowired
    private indexMapper indexMapper;
    public List<Good>findGoodStatus(){
        List<Good> goodStatus = indexMapper.findGoodStatus();
        return goodStatus;
    }
}

package com.example.demo.mapper;

import com.example.demo.entity.Inner;
import com.example.demo.entity.Outer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DBMapper {
    //获取所有依赖条目
    List<Inner> findAll();
    
    //查询模块的外部依赖（带参出了bug）
   List<Outer> queryByModule();
}

package com.example.demo.service;

import com.example.demo.entity.Inner;
import com.example.demo.entity.Outer;
import com.example.demo.mapper.DBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//声明Service容器
@Service
public class DBService {
    //SQL操作映射
    @Autowired
    private DBMapper DBMapper;
    
    //查询全部信息
    public List<Inner> findAll(){
        return DBMapper.findAll();
    }
    
    public List<Outer> queryByModule(){
        return DBMapper.queryByModule();
    }
}

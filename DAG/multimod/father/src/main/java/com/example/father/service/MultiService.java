package com.example.father.service;

import com.example.moda.ModA;
import com.example.modb.ModB;
import com.example.modc.ModC;
import com.example.modd.ModD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiService {
    public int service(){
        return ModA.moda()+ ModB.modb()+
                ModC.modc()+ ModD.modd();
    }
}

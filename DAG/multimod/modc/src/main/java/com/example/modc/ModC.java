package com.example.modc;

import com.example.modb.ModB;
import com.example.mode.ModE;

public class ModC {
    public static int modc(){
        return ModB.modb()+ ModE.mode();
    }
}

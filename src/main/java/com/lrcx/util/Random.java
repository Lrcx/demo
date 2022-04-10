package com.lrcx.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Random {
    public String random(){
        char letter[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String result="";
        for(int i=0;i<6;i++){
            result+=letter[(int)Math.round(Math.random()*letter.length)] ;
        }
        return result;
    }
}

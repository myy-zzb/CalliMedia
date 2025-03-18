package com.example.callimedia;

import java.util.List;

public class chat_item {
    List<chat_text>  text;
     int  touxiang_id;
    chat_item(List<chat_text> x,int y){
        touxiang_id=y;
        text=x;
    }
}

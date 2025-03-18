package com.example.callimedia;

import android.graphics.Bitmap;
import android.provider.ContactsContract;

import java.util.HashMap;

public class biaoji_re_item {
    Bitmap bitmap;
    String name;
   HashMap<String, Integer> x;
   public biaoji_re_item(Bitmap bitmap, String name, HashMap<String, Integer> x) {
       this.bitmap = bitmap;
        this.name = name;
        this.x = x;
   }
}

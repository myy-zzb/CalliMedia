package com.example.callimedia;

import android.graphics.Bitmap;
import android.media.Image;

public class biaoji_grid_item {
    Bitmap image;
    String name;
    String id;
    biaoji_grid_item(Bitmap image, String name,String id)
    {
        this.image = image;
        this.name = name;
        this.id = id;
    }
}

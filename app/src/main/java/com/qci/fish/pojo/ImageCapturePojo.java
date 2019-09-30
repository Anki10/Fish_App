package com.qci.fish.pojo;

import androidx.room.ColumnInfo;

public class ImageCapturePojo {

    private String fish_name;
    private String local_image_path1;
    private String local_image_path2;
    private String local_image_path3;

    public String getFish_name() {
        return fish_name;
    }

    public void setFish_name(String fish_name) {
        this.fish_name = fish_name;
    }

    public String getLocal_image_path1() {
        return local_image_path1;
    }

    public void setLocal_image_path1(String local_image_path1) {
        this.local_image_path1 = local_image_path1;
    }

    public String getLocal_image_path2() {
        return local_image_path2;
    }

    public void setLocal_image_path2(String local_image_path2) {
        this.local_image_path2 = local_image_path2;
    }

    public String getLocal_image_path3() {
        return local_image_path3;
    }

    public void setLocal_image_path3(String local_image_path3) {
        this.local_image_path3 = local_image_path3;
    }


}

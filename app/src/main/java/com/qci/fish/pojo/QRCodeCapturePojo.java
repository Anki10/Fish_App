package com.qci.fish.pojo;

public class QRCodeCapturePojo {

    private String fish_name;

    public String getFish_name() {
        return fish_name;
    }

    public void setFish_name(String fish_name) {
        this.fish_name = fish_name;
    }

    public String getScanned_qrcode() {
        return scanned_qrcode;
    }

    public void setScanned_qrcode(String scanned_qrcode) {
        this.scanned_qrcode = scanned_qrcode;
    }

    private String scanned_qrcode;
}

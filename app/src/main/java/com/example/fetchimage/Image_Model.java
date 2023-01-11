package com.example.fetchimage;

public class Image_Model {

    private String IMAGE_NAME;
    private String IMAGE_URL;

    public Image_Model() {
    }

    public Image_Model(String IMAGE_NAME, String IMAGE_URL) {
        this.IMAGE_NAME = IMAGE_NAME;
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getIMAGE_NAME() {
        return IMAGE_NAME;
    }

    public void setIMAGE_NAME(String IMAGE_NAME) {
        this.IMAGE_NAME = IMAGE_NAME;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }
}

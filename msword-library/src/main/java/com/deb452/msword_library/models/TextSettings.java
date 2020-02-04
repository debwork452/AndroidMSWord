package com.deb452.msword_library.models;
public class TextSettings {
    private String textColor;


    public String getTextColor() {
        return textColor;
    }
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public TextSettings(String textColor){
        this.textColor = textColor;
    }

}

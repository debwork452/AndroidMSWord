package com.deb452.msword_library.models;


import org.jsoup.nodes.Element;

public class HtmlElement {
    public String TagName;
    public Element _Element;

    public  HtmlElement(String _TagName, Element _Element){
        this.TagName= _TagName;
        this._Element= _Element;
    }
}


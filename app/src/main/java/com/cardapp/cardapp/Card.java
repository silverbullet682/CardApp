package com.cardapp.cardapp;

public class Card {
    int imgSrc ;
    String meaning;

    public Card() {
    }

    public Card(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}

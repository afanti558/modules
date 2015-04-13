package com.raipeng.pattern.adapter;

public class Adepter implements Job{
    Person person;

    public Adepter(Person person) {
        this.person = person;
    }

    public void speakEnglish() {
        person.speakEnglish();
    }

    public void speakJapanese() {
        person.speakJapanese();
    }

    //new add
    public void speakFrench() {
        System.out.println("I can speak Frence!");
    }
}

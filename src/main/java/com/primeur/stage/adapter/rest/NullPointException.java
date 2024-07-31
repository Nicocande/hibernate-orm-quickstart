package com.primeur.stage.adapter.rest;

public class NullPointException extends RuntimeException{

    public NullPointException(String message){
       super(message);
    }


    public String message1= ("Should not create Book when title is null");

    public  String message2= ("Should not create Book when author is null");

    public  String message3= ("Should not create Book when description is null");


}

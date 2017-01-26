package com.samarthgupta.chatapp;

/**
 * Created by samarthgupta on 24/01/17.
 */

public class FirebaseClass {
    private String message;
    private String sender;
    private String reciever;

    FirebaseClass(){

    }

    public FirebaseClass(String sender, String reciever, String message){
        this.sender=sender;
        this.reciever=reciever;
        this.message=message;
    }

    public String getReciever() {
        return reciever;
    }


    public String getSender() {
        return sender;
    }


    public String getMessage() {
        return message;
    }

}

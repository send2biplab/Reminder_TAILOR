package com.ubk.casdis_tailor;

public class Contact2 {
    int _id;
    String _name;
    String _phone_number;
    String key_id;
    String key_imgpath;
    public Contact2(){   }
    public Contact2(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contact2(String name, String _phone_number, String key_id,String key_imgpath){
        this._name = name;
        this._phone_number = _phone_number;
        this.key_id=key_id;
        this.key_imgpath=key_imgpath;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getKey_id(){
        return this.key_id;
    }

    public void setKey_id(String key_id){
        this.key_id = key_id;
    }

    public String getPhoneNumber(){
        return this._phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }


    public String getkey_imgpath(){
        return this.key_imgpath;
    }

    public void setkey_imgpath(String key_imgpath){
        this.key_imgpath = key_imgpath;
    }
}
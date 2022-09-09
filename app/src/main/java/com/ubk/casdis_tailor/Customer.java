package com.ubk.casdis_tailor;

public class Customer {
    String name = null;
    String number = null;
    String id = null;

    public Customer(String Sname, String Snumber,String id) {

        super();

        this.name = Sname;
        this.id = id;
        this.number = Snumber;
    }

    public String getName() {
        return name;
    }
        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String Name2) {

        this.name = Name2;

    }
    public String getNumber() {

        return number;

    }
    public void setNumber(String number2) {

        this.number = number2;

    }

    @Override
    public String toString() {

        return  name + " " + number ;

    }
}

package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Iorlov on 13.11.2015.
 */
@XmlType( propOrder = {"name","phonenum","adress"})
@XmlRootElement(name = "Customer")
public class Customer extends ModelItem {
    private String name;
    private String phonenum;
    private String adress;

    public Customer(){
        this.name = "";
        this.phonenum = "";
        this.adress ="";
    }

    public Customer(int id,String name, String phonenum, String adress) {
        super(id);
        this.name = name;
        this.phonenum = phonenum;
        this.adress = adress;
    }

    @XmlElement(name = "Name")
    public void setName(String name){
        this.name=name;
    }

    public String nameProperty(){
        return name;
    }
    @XmlElement(name = "Phone_number")
    public void setPhonenum(String phonenum){
        this.phonenum=phonenum;
    }

    public String phonenumProperty(){
        return phonenum;
    }
    @XmlElement(name = "Adress")
    public void setAdress(String adress){
        this.adress=adress;
    }

    public String adressProperty(){
        return adress;
    }
    public String getName(){
        return  name;
    }

    public String getPhonenum(){
        return phonenum;
    }

    public String getAdress(){
        return adress;
    }

//    @Override
//    public int hashCode() {
//        return 0;
//    }

    @Override
    public boolean equals(Object a) {
        if (a == null){return false;}
        if (a == this){return true;}
        if (getClass()!=a.getClass()) {return  false;}
        Customer o = (Customer)a;
        return (this.getNumber() == o.getNumber() && this.getName().equals(o.getName()) && this.getPhonenum().equals(o.getPhonenum()) && this.getAdress().equals(o.getAdress()));
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        return strbuild.append("ID ")
                .append(this.getNumber())
                .append("; Name ")
                .append(this.getName())
                .append("; Phone number ")
                .append(this.getPhonenum())
                .append("; Adress ")
                .append(this.getAdress())
                .toString();
    }
}
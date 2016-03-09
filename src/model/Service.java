package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by ������ on 16.11.2015.
 */

@XmlType( propOrder = {"status","tariffID","customerID","actualOrderID"})
@XmlRootElement(name = "Service")
public class Service extends ModelItem{
    private int customerID;
    private int tariffID;
    private int actualOrderID;
    private int status;

    public Service(){
        tariffID = 0;
        customerID = 0;
        status = 0;
        actualOrderID = 0;
    }

    public Service(int id, int tariffID, int customerID, int status, int actualOrderID) {
        super(id);
        this.tariffID = tariffID;
        this.customerID = customerID;
        this.status = status;
        this.actualOrderID = actualOrderID;
    }

    @XmlElement(name = "status")
    public void setStatus(int Status){
        this.status =Status;
    }
    public int StatusPror(){

        return status;
    }

    @XmlElement(name = "tariffID")
    public void setTariffID(int TariffID){
        this.tariffID =TariffID;
    }
    public int TariffIDprop(){return tariffID;}

    @XmlElement(name = "customerID")
    public void setCustomerID(int CustomerID){
        this.customerID =CustomerID;
    }
    public int CustomerIDprop(){return customerID;}

    @XmlElement(name = "actualOrderID")
    public void setActualOrderID(int ActualOrderID){
        this.actualOrderID =ActualOrderID;
    }
    public int ActualOrderIDprop(){return actualOrderID;}

    public int getTariffID(){
        return tariffID;
    }

    public int getCustomerID(){
        return customerID;
    }

    public int getStatus(){
        return status;
    }

    public int getActualOrderID(){
        return actualOrderID;
    }

    public String getStringStatus(){
        switch (status){
            case 0: return "Active";
            case 1: return "Suspended";
            case 2: return "Disconnected";
            default: return "";
        }
    }

    public boolean equals(Object a){

        if (a == null){return false;}
        if (a == this){return true;}
        if (getClass()!=a.getClass()) {return  false;}
        Service o = (Service)a;
        return (this.getNumber() == o.getNumber()
                && this.getCustomerID() == o.getCustomerID()
                && this.getTariffID() == o.getTariffID()
                && this.getStatus() == o.getStatus());
    }

    public String toString(){
        StringBuilder strbuild = new StringBuilder();
        return strbuild.append("ID ")
                .append(this.getNumber())
                .append("; Tariff ID ")
                .append(this.getTariffID())
                .append("; Customer ID ")
                .append(this.getCustomerID())
                .append(";Status ")
                .append(this.getStringStatus())
                .toString();
    }
}

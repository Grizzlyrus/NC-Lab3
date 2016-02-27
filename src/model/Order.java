package model;

import util.LocalDateAdapter;
import javafx.beans.property.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Iorlov on 13.11.2015.
 */
@XmlType( propOrder = {"customernum","tariffnum","date","sum", "serviceId", "prevOrderId", "stat"})
@XmlRootElement(name = "Order")
public class Order extends ModelItem {

    private int customernum;
    private int tariffnum;
    private int serviceId;
    private int prevOrderId;

    private LocalDate date;
    private double sum;
    private int stat;

    public Order(){
        this.customernum = 0;
        this.tariffnum = 0;
        this.date = LocalDate.now();
        this.sum = 0;
    }

    public Order(int id, int customernum, int tariffnum, double sum) {
        super(id);
        this.customernum = customernum;
        this.tariffnum = tariffnum;
        this.date = LocalDate.now();
        this.sum = sum;
    }

    public Order(int id, int customernum, int tariffnum, int prevOrderId, LocalDate date, double sum, int stat) {
        super(id);
        this.customernum = customernum;
        this.tariffnum = tariffnum;
        this.prevOrderId = prevOrderId;
        this.date = date;
        this.sum = sum;
        this.stat = stat;
    }

    @XmlElement(name = "Service_ID")
    public void setServiceID(int serviceID){this.serviceId=serviceID;}
    public int serviceidProperty(){return serviceId;}

    @XmlElement(name = "Previous_order_ID")
    public void setPrevOrderID(int prevOrderID){this.prevOrderId=prevOrderID;}
    public int prevorderidProperty(){return prevOrderId;}


    public int statProperty(){
        return stat;
    }

     @XmlElement(name = "Customer_number")
    public void setCustomernum(int customernum){
        this.customernum=customernum;
    }
    public int customernumProperty(){
        return customernum;
    }
    @XmlElement(name = "Tariff_number")
    public void setTariffnum(int tariffnum){
        this.tariffnum=tariffnum;
    }

    public int tariffnumProperty(){
        return tariffnum;
    }
    @XmlElement(name = "Order_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setDate(LocalDate date){
        this.date=date;
    }
    public LocalDate dateProperty(){
        return date;
    }
    @XmlElement(name = "Order_sum")
    public void setSum(double sum){
        this.sum=sum;
    }

    public double sumProperty(){
        return sum;
    }
    public int getCustomernum(){
        return customernum;
    }

    public int getTariffnum(){
        return tariffnum;
    }

    public LocalDate getDate(){
        return date;
    }

    public double getSum(){
        return sum;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setPrevOrderId(int prevOrderId) {
        this.prevOrderId = prevOrderId;
    }

    @XmlElement(name = "Status")
    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getPrevOrderId() {
        return prevOrderId;
    }

    public int getStat() {
        return stat;
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
        Order o = (Order)a;
        return (this.getNumber() == o.getNumber()
                && this.getCustomernum() == o.getCustomernum()
                && this.getTariffnum() == o.getTariffnum()
                && this.getDate() == o.getDate()
                && this.getSum() == o.getSum());
    }

    @Override
    public String toString() {
        StringBuilder strbuild = new StringBuilder();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return strbuild.append("ID ")
                .append(this.getNumber())
                .append("; Customer ID ")
                .append(this.getCustomernum())
                .append("; Tariff ID ")
                .append(this.getTariffnum())
                .append("; Date ")
                .append(df.format(this.getDate()))
                .append("; Sum. of order ")
                .append(this.getSum())
                .toString();
    }
    public String getStringStat(){
        switch (stat){
            case 0: return "New";
            case 1: return "Modify";
            case 2: return "Suspend";
            case 3: return "Resume";
            case 4: return "Disconnect";
            default: return "";
        }
    }
}

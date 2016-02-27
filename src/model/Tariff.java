package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Iorlov on 13.11.2015.
 */
@XmlType( propOrder = {"name", "speed", "cost"})
@XmlRootElement(name = "Tariff")
public class Tariff extends ModelItem {
    private String name;
    private double speed;
    private double cost;

    public Tariff(){
        this.name = "";
        this.speed = 0;
        this.cost = 0;
    }

    public Tariff(int id, String name, double speed, double cost) {
        super(id);
        this.name =name;
        this.speed = speed;
        this.cost = cost;
    }

    @XmlElement(name = "Name")
    public void setName(String name){
        this.name=name;
    }
    public String nameProperty(){
        return name;
    }

    @XmlElement(name = "Speed")
    public void setSpeed(double speed){
        this.speed=speed;
    }

    public Double speedProperty(){
        return speed;
    }
    @XmlElement(name = "Cost")
    public void setCost(double cost){
        this.cost=cost;
    }

    public double costProperty(){
        return cost;
    }

    public String getName(){
        return  name;
    }

    public double getSpeed(){
        return speed;
    }

    public double getCost(){
        return cost;
    }

//    @Override
//    public int hashCode() {
//        return 0;
//    }

    @Override
    public boolean equals(Object a){
        if (a == null){return false;}
        if (a == this){return true;}
        if (getClass()!=a.getClass()) {return  false;}
        Tariff o = (Tariff)a;
        return (this.getNumber() == o.getNumber() && this.getName().equals(o.getName()) && this.getSpeed() == o.getSpeed() && this.getSpeed() == o.getSpeed());
    }

    @Override
    public String toString(){
        StringBuilder strbuild = new StringBuilder();
        return strbuild.append("ID ")
                .append(this.getNumber())
                .append("; Name ")
                .append(this.getName())
                .append("; Speed ")
                .append(this.getSpeed())
                .append("; Cost ")
                .append(this.getCost())
                .toString();
    }

}

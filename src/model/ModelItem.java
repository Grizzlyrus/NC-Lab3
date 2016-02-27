package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

@XmlType( propOrder = {"number"})
public abstract class ModelItem {
    private int number;

    public ModelItem() {
        number=0;
    }

    public ModelItem( int id){
        number = id;
    }


    public int numberProperty(){
        return number;
    }
    @XmlElement(name = "Number")
    public void setNumber(int number){
        this.number=number;
    }

    public int getNumber(){
        return number;
    }

//    @Override
//    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object a);

    @Override
    public abstract String toString();

}

package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ������ on 01.11.2015.
 */

@XmlRootElement(name = "Collection")
@XmlSeeAlso({Tariff.class, Customer.class, Order.class,Service.class})
public class ModelItemCollection<T extends ModelItem> implements Serializable{

    private Map<Integer, T> ModIt;

    public ModelItemCollection(){}

    @XmlElement(name = "Model")
    public Map<Integer, T> getModIt(){
        return ModIt;
    }

    public void setModIt(Map<Integer, T> ModIt){
        this.ModIt = ModIt;
    }
}

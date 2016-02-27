package util;

import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ������ on 04.11.2015.
 */
public final class ModelFacade {
    private ObservableMap<Integer,ModelItem> orders = FXCollections.observableHashMap();
    private ObservableMap<Integer,ModelItem> customers = FXCollections.observableHashMap();
    private ObservableMap<Integer,ModelItem> tariffs = FXCollections.observableHashMap();
    private int currentOrderId;
    private int currentCustomerId;
    private int currentTariffId;

    private static ModelFacade instance = null;

    private ModelFacade(){}

    public static synchronized ModelFacade getInstance(){
        if(instance == null){
            instance = new ModelFacade();
            //TODO check this code

            /*instance.getTariffs().put(1234, new Tariff(1234, "Super Fast", 100, 350));
            instance.getTariffs().put(9876, new Tariff(9876, "TariffName", 50, 400));
            instance.getTariffs().put(4567, new Tariff(4567, "RandomTariffName", 777, 500));
            instance.getTariffs().put(5678, new Tariff(5678, "VerySlow", 10, 1));

            instance.getCustomers().put(1234, new Customer(1234, "qwerty", "8-800-555-35-35", "Samara"));
            instance.getCustomers().put(1379, new Customer(1379, "Name13", "9368746317", "Address"));
            instance.getCustomers().put(9876, new Customer(9876, "RandomName", "9875632147", "Address"));
            instance.getCustomers().put(4567, new Customer(4567, "Customer�13", "8313131313", "SomewhereHere"));
            instance.getCustomers().put(5678, new Customer(5678, "MegaQwerty", "88462245699", "Samara"));

            instance.getOrd ers().put(1234, new Order(1234, 1234, 1234, 350));
            instance.getOrders().put(4567, new Order(4567, 4567, 4567, 500));
            instance.getOrders().put(5678,new Order(5678,5678,5678,10));*/
            instance.setOrders(instance.readObjects("src/InformationSystem/res/Orders.xml"));
            instance.setCustomers(instance.readObjects("src/InformationSystem/res/Customers.xml"));
            instance.setTariffs(instance.readObjects("src/InformationSystem/res/Tariffs.xml"));
            instance.initCurrentIds();
        }
        return instance;
    }

    private void initCurrentIds() {
        int maxId=0;
        for (Map.Entry<Integer, ModelItem> entry : getOrders().entrySet())
        {

            if (maxId < entry.getKey())
            {
                maxId = entry.getKey();
            }

        }
        setCurrentOrderId(maxId);

        maxId=0;
        for (Map.Entry<Integer, ModelItem> entry : getCustomers().entrySet())
        {

            if (maxId < entry.getKey())
            {
                maxId = entry.getKey();
            }

        }
        setCurrentCustomerId(maxId);

        maxId=0;
        for (Map.Entry<Integer, ModelItem> entry : getTariffs().entrySet())
        {

            if (maxId < entry.getKey())
            {
                maxId = entry.getKey();
            }

        }
        setCurrentTariffId(maxId);
    }


    public void setCustomers(ModelItemCollection<Customer> Customers){
        this.customers.putAll(Customers.getModIt());
    }

    public void setOrders(ModelItemCollection<Order> Orders){
        this.orders.putAll(Orders.getModIt());
    }

    public void setTariffs(ModelItemCollection<Tariff> Tariffs){
        this.tariffs.putAll(Tariffs.getModIt());
    }

    public ObservableMap<Integer,ModelItem> getCustomers(){
        return customers;
    }

    public ObservableMap<Integer,ModelItem> getOrders(){
        return orders;
    }

    public ObservableMap<Integer,ModelItem> getTariffs(){
        return tariffs;
    }

    public Order getOrderById(int id){
        return (Order)orders.get(id);
    }

    public Customer getCustomerById(int id){
        return (Customer)customers.get(id);
    }

    public Tariff getTariffById(int id){
        return (Tariff)tariffs.get(id);
    }

    public void setCurrentOrderId(int currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public void setCurrentCustomerId(int currentCustomerId) {
        this.currentCustomerId = currentCustomerId;
    }

    public void setCurrentTariffId(int currentTariffId) {
        this.currentTariffId = currentTariffId;
    }

    public synchronized void addOrder(Order order){
        if (order.getNumber() <= 0) {
            order.setNumber(getNewOrderId());
        }
        orders.put(order.getNumber(), order);
        ModelItemCollection wrapper = new ModelItemCollection();
        wrapper.setModIt(getOrders());
        writeObjects("src/InformationSystem/res/Orders.xml", wrapper);
        //writeObjects("src/res/Orders.xml",Orders);
    }

    public synchronized void addCustomer(Customer customer){
        if (customer.getNumber() <= 0) {
            customer.setNumber(getNewCustomerId());
        }
        customers.put(customer.getNumber(), customer);
        ModelItemCollection wrapper = new ModelItemCollection();
        wrapper.setModIt(getCustomers());
        writeObjects("src/InformationSystem/res/Customers.xml",wrapper);
        //writeObjects("src/res/Customers.xml",Customers);
    }

    public synchronized void addTariff(Tariff tariff){
        if (tariff.getNumber() <= 0) {
            tariff.setNumber(getNewTariffId());
        }
        tariffs.put(tariff.getNumber(), tariff);
        ModelItemCollection wrapper = new ModelItemCollection();
        wrapper.setModIt(getTariffs());
        writeObjects("src/InformationSystem/res/Tariffs.xml",wrapper);
        //writeObjects("src/res/Tariffs.xml",Tariffs);
    }
    public synchronized void removeOrder(int id){
        orders.remove(id);
        ModelItemCollection wrapper = new ModelItemCollection();
        wrapper.setModIt(getOrders());
        writeObjects("src/InformationSystem/res/Orders.xml", wrapper);
    }
    public synchronized void removeCustomer(int id){
        customers.remove(id);
        ModelItemCollection wrapper = new ModelItemCollection();
        wrapper.setModIt(getCustomers());
        writeObjects("src/InformationSystem/res/Customers.xml",wrapper);
    }
    public synchronized void removeTariff(int id){
        tariffs.remove(id);
        ModelItemCollection wrapper = new ModelItemCollection();
        wrapper.setModIt(getTariffs());
        writeObjects("src/InformationSystem/res/Tariffs.xml",wrapper);
    }

    public synchronized int getNewOrderId() {
        return ++currentOrderId;
    }

    public synchronized int getNewCustomerId() {
        return ++currentCustomerId;
    }

    public synchronized int getNewTariffId() {
        return ++currentTariffId;
    }

    public void writeObjects(String filename,ModelItemCollection models){
        try {
            File file = new File(filename);

            JAXBContext jaxbContext = JAXBContext.newInstance(ModelItemCollection.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(models, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public ModelItemCollection readObjects(String filename){
        ModelItemCollection modcoll = new ModelItemCollection();
        try {

            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(ModelItemCollection.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            modcoll = (ModelItemCollection) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return modcoll;
    }
}

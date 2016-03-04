package util;

import model.Customer;
import model.Order;
import model.Service;
import model.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Iorlov on 02.02.2016.
 */
public class DBWorker {
    private DAO dao = new DAO();
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;
    private ArrayList<Tariff> tariffs;
    private ArrayList<Service> services;

    public ArrayList<Customer> getCustomers() {
        if (customers == null) {
//            customers=new ArrayList<Customer>();
//            customers.add(new Customer(1234, "qwerty", "8-800-555-35-35", "Samara"));
//            customers.add(new Customer(1379, "Name13", "9368746317", "Address"));
//            customers.add(new Customer(9876, "RandomName", "9875632147", "Address"));
//            customers.add(new Customer(4567, "Customer№13", "8313131313", "SomewhereHere"));
//            customers.add(new Customer(5678, "MegaQwerty", "88462245699", "Samara"));
//            //customers= (ArrayList<Customer>) dao.getAllCustomers();
        customers = new ArrayList<Customer>(dao.getAllCustomers());
        }
        return customers;
    }
    public ArrayList<Customer> findCustomerByID(int id){
        return getCustomers().parallelStream().filter(customer -> customer.getNumber()==id)
                .collect(Collectors.toCollection(ArrayList<Customer>::new));
    }
    public ArrayList<Customer> findCustomerByName(String name){
        return getCustomers().parallelStream().filter(customer -> customer.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList<Customer>::new));
    }
    public ArrayList<Order> getOrders() {
        if (orders == null) {
//            orders=new ArrayList<Order>();
//            orders.add(new Order(1234, 1234, 1234, 350));
//            orders.get(0).setServiceId(1234);
//            orders.get(0).setStat(0);
//            orders.get(0).setPrevOrderId(0);
//            orders.add(new Order(4567, 4567, 4567, 500));
//            orders.get(1).setServiceId(4567);
//            orders.get(1).setStat(0);
//            orders.get(1).setPrevOrderId(0);
//            orders.add(new Order(5678, 5678, 5678, 10));
//            orders.get(2).setServiceId(5678);
//            orders.get(2).setStat(0);
//            orders.get(2).setPrevOrderId(0);
//            //orders= (ArrayList<Order>) dao.getAllOrders();
        orders = new ArrayList<Order>(dao.getAllOrders());
        }
        return orders;
    }

    public ArrayList<Order> findOrderByID(int id){
        return getOrders().parallelStream().filter(order -> order.getNumber()==id)
                .collect(Collectors.toCollection(ArrayList<Order>::new));
    }

    public ArrayList<Tariff> getTariffs() {
        if (tariffs == null) {
//            tariffs=new ArrayList<Tariff>();
//            tariffs.add(new Tariff(1234, "Super Fast", 100, 350));
//            tariffs.add(new Tariff(9876, "TariffName", 50, 400));
//            tariffs.add( new Tariff(4567, "RandomTariffName", 777, 500));
//            tariffs.add(new Tariff(5678, "VerySlow", 10, 1));
//            //tariffs= (ArrayList<Tariff>) dao.getAllTariffs();
        tariffs = new ArrayList<Tariff>(dao.getAllTariffs());
        }
        return tariffs;
    }
    public ArrayList<Tariff> findTariffByID(int id){
        return getTariffs().parallelStream().filter(tariff -> tariff.getNumber()==id)
                .collect(Collectors.toCollection(ArrayList<Tariff>::new));
    }
    public ArrayList<Tariff> findTariffByName(String name){
        return getTariffs().parallelStream().filter(tariff -> tariff.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList<Tariff>::new));
    }


    public ArrayList<Service> getServices() {
        if (services == null) {
//            services=new ArrayList<Service>();
//            services.add(new Service(1234,1234,1234,0,1234));
//            services.add(new Service(4567,4567,4567,0,4567));
//            services.add(new Service(5678,5678,5678,0,5678));
//
        services = new ArrayList<Service>(dao.getAllServices());
        }
        return services;
    }
    public ArrayList<Service> findServiceByID(int id){
        return getServices().parallelStream().filter(service -> service.getNumber()==id)
                .collect(Collectors.toCollection(ArrayList<Service>::new));
    }
    public void refreshLists(){
        customers= (ArrayList<Customer>) dao.getAllCustomers();
        orders= (ArrayList<Order>) dao.getAllOrders();
        tariffs= (ArrayList<Tariff>) dao.getAllTariffs();
        services= (ArrayList<Service>) dao.getAllServices();
    }

    public void addCustomer(Customer customer){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?)");
            statement.setInt(1,customer.getNumber());
            statement.setString(2,customer.getName());
            statement.setString(3,customer.getPhonenum());
            statement.setString(4,customer.getAdress());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTariff(Tariff tariff){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tariffs VALUES (?,?,?,?)");
            statement.setInt(1,tariff.getNumber());
            statement.setString(2,tariff.getName());
            statement.setDouble(3, tariff.getSpeed());
            statement.setDouble(4, tariff.getCost());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addOrder(Order order){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?,?,?,?)");
            statement.setInt(1,order.getNumber());
            statement.setInt(2, order.getCustomernum());
            statement.setInt(3, order.getTariffnum());
            statement.setInt(4, order.getServiceId());
            statement.setInt(5,order.getPrevOrderId());
            statement.setDate(6, Date.valueOf(order.getDate()));
            statement.setDouble(7, order.getSum());
            statement.setInt(8,order.getStat());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addService(Service service){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO services VALUES (?,?,?,?,?,?,?,?)");
            statement.setInt(1,service.getNumber());
            statement.setInt(2, service.getCustomerID());
            statement.setInt(3, service.getTariffID());
            statement.setInt(4, service.getActualOrderID());
            statement.setInt(5,service.getStatus());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyCustomer(Customer customer){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE customers SET name=? , phone=?, address=? WHERE id=?");
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getPhonenum());
            statement.setString(3, customer.getAdress());
            statement.setInt(4, customer.getNumber());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyTariff(Tariff tariff){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE tariffs SET name=?, speed=?, cost=? WHERE id=?");
            statement.setString(1, tariff.getName());
            statement.setDouble(2, tariff.getSpeed());
            statement.setDouble(3, tariff.getCost());
            statement.setInt(4, tariff.getNumber());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyOrder(Order order){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE orders SET customerid=?,tariffid=?,serviceid=?,prevorderid=?,orderdate=?,summ=?,stat=? WHERE id=?");
            statement.setInt(1, order.getCustomernum());
            statement.setInt(2, order.getTariffnum());
            statement.setInt(3, order.getServiceId());
            statement.setInt(4,order.getPrevOrderId());
            statement.setDate(5, Date.valueOf(order.getDate()));
            statement.setDouble(6, order.getSum());
            statement.setInt(7,order.getStat());
            statement.setInt(8,order.getNumber());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modifyService(Service service){
        Connection connection = null;

        try {
            connection = dao.getDs().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE services SET customerid=?, tariffid=?, actualorderid=?, status=? WHERE id=?");
            statement.setInt(1, service.getCustomerID());
            statement.setInt(2, service.getTariffID());
            statement.setInt(3, service.getActualOrderID());
            statement.setInt(4,service.getStatus());
            statement.setInt(5,service.getNumber());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

package util;

import model.Customer;
import model.Order;
import model.Service;
import model.Tariff;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iorlov on 02.02.2016.
 */
public class DAO{
    private static DataSource ds;

    static {
        try {
            Context ctx = new InitialContext();
            //TODO PostgresPool
            ds = (DataSource) ctx.lookup("jdbc/PostgresPool");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getCustomers(int idCustomers){
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection=null;
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customerId=?");
            statement.setInt(1,idCustomers);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Customer customer=new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                customers.add(customer);
            }
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
        return customers;
    }
    public List<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                customers.add(customer);
            }
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
        return customers;
    }
    public List<Customer> getCustomerByName(String name){
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE name=?");
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                customers.add(customer);
            }
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
        return customers;
    }
    public List<Order> getOrders(int idOrders){
        Connection connection=null;
        ArrayList<Order> orders=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderId=?");
            statement.setInt(1,idOrders);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Order order=new Order(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(5),resultSet.getDate(6).toLocalDate(),
                        resultSet.getDouble(7),resultSet.getInt(8));
                order.setServiceID(resultSet.getInt(4));
                orders.add(order);
            }
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
        return orders;
    }
    public List<Order> getAllOrders(){
        Connection connection=null;
        ArrayList<Order> orders=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Order order=new Order(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(5),resultSet.getDate(6).toLocalDate(),
                        resultSet.getDouble(7),resultSet.getInt(8));
                order.setServiceID(resultSet.getInt(4));
                orders.add(order);
            }
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
        return orders;
    }

    public List<Tariff> getTariffs(int idTariffs){
        Connection connection=null;
        ArrayList<Tariff> tariffs=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tariffs WHERE tariffId=?");
            statement.setInt(1,idTariffs);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Tariff tariff=new Tariff(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4));
                tariffs.add(tariff);
            }
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
        return tariffs;
    }
    public List<Tariff> getAllTariffs(){
        Connection connection=null;
        ArrayList<Tariff> tariffs=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tariffs");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Tariff tariff=new Tariff(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4));
                tariffs.add(tariff);
            }
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
        return tariffs;
    }
    public List<Tariff> getTariffByName(String name){
        Connection connection=null;
        ArrayList<Tariff> tariffs=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tariffs WHERE name=?");
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Tariff tariff=new Tariff(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4));
                tariffs.add(tariff);
            }
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
        return tariffs;
    }
    public List<Service> getServices(int idService){
        Connection connection=null;
        ArrayList<Service> services=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM services WHERE serviceId=?");
            statement.setInt(1,idService);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Service service=new Service(resultSet.getInt(1),resultSet.getInt(3),resultSet.getInt(2),resultSet.getInt(5),resultSet.getInt(4));
                services.add(service);
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return services;
    }
    public List<Service> getAllServices(){
        Connection connection=null;
        ArrayList<Service> services=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM services");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Service service=new Service(resultSet.getInt(1),resultSet.getInt(3),resultSet.getInt(2),resultSet.getInt(5),resultSet.getInt(4));
                services.add(service);
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return services;
    }
}

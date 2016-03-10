package util;

import model.Customer;
import model.Order;
import model.Service;
import model.Tariff;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private DataSource ds;

    public DAO(){
        try {
            Context ctx = new InitialContext();
            //TODO PostgresPool
            ds = (DataSource) ctx.lookup("jdbc/PostgresPool");
            System.out.println(1);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getCustomer(int idCustomer){
        ArrayList<Customer> customers=new ArrayList<>();
        Connection connection=null;
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customerId=?");
            statement.setInt(1,idCustomer);
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
//            e.printStackTrace();
            File a= new File("error.txt");
            try(PrintWriter printWriter=new PrintWriter(new File("error.txt"))){
                printWriter.write(e.getMessage());
                printWriter.close();
            }
            catch (IOException err){}

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
    public List<Order> getOrder(int idOrder){
        Connection connection=null;
        ArrayList<Order> orders=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderId=?");
            statement.setInt(1,idOrder);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Order order=new Order(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(5),resultSet.getDate(6).toLocalDate(),
                        resultSet.getDouble(7),resultSet.getInt(8));
                order.setServiceId(resultSet.getInt(4));
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
                order.setServiceId(resultSet.getInt(4));
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

    public List<Tariff> getTariff(int idTariff){
        Connection connection=null;
        ArrayList<Tariff> tariffs=new ArrayList<>();
        try {
            connection=ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tariffs WHERE tariffId=?");
            statement.setInt(1,idTariff);
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
    public List<Service> getService(int idService){
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

    public DataSource getDs(){
        return ds;
    }
}

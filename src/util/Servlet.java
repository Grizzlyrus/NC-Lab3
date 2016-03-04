package util;

import model.Customer;
import model.Order;
import model.Service;
import model.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Iorlov on 10.02.2016.
 */
@WebServlet(urlPatterns = {"/newcustomer","/neworder","/newtariff","/newservice","/getsum","/getCustomer","/mCustomer","/mTariff","/mOrder"})
public class Servlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBWorker dbWorker= (DBWorker) req.getSession().getAttribute("DBworker");
        switch (req.getServletPath()){
            case "/newcustomer":
                Customer c=new Customer((int) (Math.random()*5000),req.getParameter("name"),req.getParameter("phone"),req.getParameter("address"));
                dbWorker.addCustomer(c);
                dbWorker.getCustomers()
                        .add(c);
                resp.sendRedirect(req.getContextPath()+"/index.jsp?button=Customers");
                return;
            case "/neworder":
                Tariff t1=dbWorker.findTariffByID(Integer.parseInt(req.getParameter("tariff"))).get(0);
                int customerId=Integer.parseInt(req.getParameter("customer"));
                Service s1= new Service();
                s1.setNumber((int) (Math.random()*5000));
                s1.setCustomerID(customerId);
                s1.setTariffID(t1.numberProperty());
                Order o=new Order((int) (Math.random()*5000),customerId,t1.numberProperty(),t1.getCost());
                s1.setActualOrderID(o.numberProperty());
                o.setServiceID(s1.numberProperty());
                o.setPrevOrderId(0);
                o.setStat(0);
                dbWorker.addService(s1);
                dbWorker.addOrder(o);
                dbWorker.getServices().add(s1);
                dbWorker.getOrders().add(o);
                resp.sendRedirect(req.getContextPath()+"/index.jsp?button=Orders");

                return;
            case "/newtariff":
                Tariff t=new Tariff((int) (Math.random()*5000),req.getParameter("name"),Double.parseDouble(req.getParameter("speed")),Double.parseDouble(req.getParameter("cost")));
                dbWorker.addTariff(t);
                dbWorker.getTariffs()
                        .add(t);
                resp.sendRedirect(req.getContextPath()+"/index.jsp?button=Tariffs");
                return;
            case "/newservice":

                break;
            case "/getsum":
                System.out.println(req.getParameter("tariff"));
                resp.getWriter().print(((DBWorker)req.getSession()
                        .getAttribute("DBworker")).findTariffByID(Integer.parseInt(req.getParameter("tariff"))).get(0).costProperty());
                return;
            case "/getCustomer":
                Customer cust=dbWorker.findCustomerByID(Integer.parseInt(req.getParameter("customerId"))).get(0);
                resp.setContentType("application/json");
                String s ="{\"name\":"+"\""+cust.nameProperty()+"\", "+"\"phone\":"+
                        "\""+cust.phonenumProperty()+"\", "+"\"address\":"+"\""+cust.adressProperty()+"\""+"}";
                System.out.println(s);
                resp.getWriter().print(s);
                return;
            case "/mCustomer":
                Customer c1=dbWorker.findCustomerByID(Integer.parseInt(req.getParameter("id"))).get(0);
                c1.setPhonenum(req.getParameter("phone"));
                c1.setAdress(req.getParameter("address"));
                c1.setName(req.getParameter("name"));
                dbWorker.modifyCustomer(c1);
                resp.sendRedirect(req.getContextPath()+"/index.jsp?button=Customers");
                return;
            case "/mTariff":
                Tariff tariff=dbWorker.findTariffByID(Integer.parseInt(req.getParameter("id"))).get(0);
                tariff.setName(req.getParameter("name"));
                tariff.setSpeed(Double.parseDouble(req.getParameter("speed")));
                tariff.setCost(Double.parseDouble(req.getParameter("cost")));
                dbWorker.modifyTariff(tariff);
                resp.sendRedirect(req.getContextPath()+"/index.jsp?button=Tariffs");
                return;
            case "/mOrder":
                Order order=dbWorker.findOrderByID(Integer.parseInt(req.getParameter("id"))).get(0);
                Tariff tariff1=dbWorker.findTariffByID(Integer.parseInt(req.getParameter("tariff"))).get(0);
                int cId=Integer.parseInt(req.getParameter("customer"));
                Service service=dbWorker.findServiceByID(order.getServiceId()).get(0);
                Order newOrder =new Order((int) (Math.random()*5000),cId,tariff1.getNumber(),tariff1.getCost());
                newOrder.setServiceId(service.getNumber());
                newOrder.setPrevOrderId(order.getNumber());
                newOrder.setStat(1);
                dbWorker.addOrder(newOrder);
                dbWorker.getOrders().add(newOrder);
                service.setActualOrderID(newOrder.getNumber());
                service.setCustomerID(cId);
                service.setTariffID(tariff1.getNumber());
                dbWorker.modifyService(service);
                resp.sendRedirect(req.getContextPath()+"/index.jsp?button=Orders");
                return;
        }
        resp.sendRedirect(req.getHeader("referer"));

    }
}

<%--
  Created by IntelliJ IDEA.
  User: Iorlov
  Date: 02.02.2016
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Information System</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="scripts/stupidtable.js"></script>
    <script src="scripts/script.js"></script>
</head>
<body>
<div id="container">
    <ul>
        <form>
            <li><input type="submit" name="button" value="Customers"/></li>
            <li><input type="submit" name="button" value="Orders"/></li>
            <li><input type="submit" name="button" value="Tariffs"/></li>
            <li><input type="submit" name="button" value="Services"/></li>
        </form>
    </ul>
    <ul id="bar">
        <li><div class="dropdown">
            <button class="dropbtn">New</button>
            <div class="dropdown-content">
                <a id="orderLink" href="modifyItem.jsp?create=order">Order</a>
                <a id="customerLink" href="modifyItem.jsp?create=customer">Customer</a>
                <a id="tariffLink" href="modifyItem.jsp?create=tariff">Tariff</a>
            </div>
        </div></li>
        <li><a href="#news">Something</a></li>
    </ul>
    <div id="content">
        <jsp:useBean id="DBworker" class="util.DBWorker" scope="session"/>
        <c:choose>
            <c:when test="${(!empty param.button) && (param.button=='Customers')}">
                <table>
                    <thead>
                    <tr>
                        <th>&nbsp</th>
                        <th data-sort="int">Customer ID</th>
                        <th data-sort="string">Customer Name</th>
                        <th>&nbsp</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="customer" items="${DBworker.customers}">
                        <tr>
                            <td><input type="checkbox" name="checkbox" value="${customer.numberProperty()}"/> </td>
                            <td>${customer.numberProperty()}</td>
                            <td>${customer.nameProperty()}</td>
                            <td><a href="index.jsp?object=${customer.numberProperty()}&table=customer">More</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>

            <c:when test="${(!empty param.table) && (param.table=='customer')}">
                <c:set var="customer" value="${DBworker.findCustomerByID(param.object).get(0)}"/>
                <form action="modifyItem.jsp">
                    <table>
                        <tr>
                            <td>Customer Id</td>
                            <td>${customer.numberProperty()}</td>
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td>${customer.nameProperty()}</td>
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td>${customer.phonenumProperty()}</td>
                        </tr>
                        <tr>
                            <td>Address</td>
                            <td>${customer.adressProperty()}</td>
                        </tr>
                        <tr>
                            <td>&nbsp</td>
                            <td class="modify"><button  type="submit" name="mCustomer" value="${customer.numberProperty()}">Modify</button> </td>
                        </tr>
                    </table>
                </form>
            </c:when>

            <c:when test="${(!empty param.button) && (param.button=='Orders')}">
                <table>
                    <tr>
                        <th>&nbsp</th>
                        <th>Order ID</th>
                        <th>Customer ID</th>
                        <th>Tariff ID</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Service Id</th>
                        <th>&nbsp</th>
                    </tr>
                    <c:forEach var="order" items="${DBworker.orders}">
                        <tr>
                            <td><input type="checkbox" name="checkbox" value="${order.numberProperty()}"/></td>
                            <td>${order.numberProperty()}</td>
                            <td><a href="index.jsp?object=${order.customernum}&table=customer" class="links">${order.customernum}</a></td>
                            <td><a href="index.jsp?object=${order.tariffnum}&table=tariff" class="links">${order.tariffnum}</a></td>
                            <td>${order.dateProperty()}</td>
                            <td>${order.stringStat}</td>
                            <td><a href="index.jsp?object=${order.serviceId}&table=service" class="links">${order.serviceId}</a> </td>
                            <td><a href="index.jsp?object=${order.numberProperty()}&table=order">More</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:when test="${(!empty param.table) && (param.table=='order')}">
                <c:set var="order" value="${DBworker.findOrderByID(param.object).get(0)}"/>
                <c:set var="customer" value="${DBworker.findCustomerByID(order.customernumProperty()).get(0)}"/>
                <c:set var="tariff" value="${DBworker.findTariffByID(order.tariffnumProperty()).get(0)}"/>
                <form action="modifyItem.jsp">
                    <table>
                        <tr>
                            <td>Order id</td>
                            <td>${order.numberProperty()}</td>
                        </tr>
                        <tr>
                            <td>Customer</td>
                            <td><a href="index.jsp?object=${order.customernum}&table=customer" class="links">${customer}</a></td>
                        </tr>
                        <tr>
                            <td>Tariff</td>
                            <td><a href="index.jsp?object=${order.tariffnum}&table=tariff" class="links">${tariff}</a></td>
                        </tr>
                        <tr>
                            <td>Date</td>
                            <td>${order.dateProperty()}</td>
                        </tr>
                        <tr>
                            <td>Sum</td>
                            <td>${order.sumProperty()}</td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td>${order.stringStat}</td>
                        </tr>
                        <tr>
                            <td>Service</td>
                            <td><a href="index.jsp?object=${order.serviceId}&table=service" class="links">${order.serviceId}</a></td>
                        </tr>
                        <c:if test="${order.prevOrderId!=0}" >
                            <tr>
                                <td>Prev Order id</td>
                                <td><a href="index.jsp?object=${order.prevOrderId}&table=order" class="links">${order.prevOrderId}</a></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>&nbsp</td>
                            <td class="modify"><button  type="submit" name="mOrder" value="${order.numberProperty()}">Modify</button> </td>
                        </tr>
                    </table>
                </form>
            </c:when>

            <c:when test="${(!empty param.button) && (param.button=='Tariffs')}">
                <table>
                    <tr>
                        <th>&nbsp</th>
                        <th>Tariff ID</th>
                        <th>Tariff Name</th>
                        <th>&nbsp</th>
                    </tr>
                    <c:forEach var="tariff" items="${DBworker.tariffs}">
                        <tr>
                            <td><input type="checkbox" name="checkbox" value="${tariff.numberProperty()}"/> </td>
                            <td>${tariff.numberProperty()}</td>
                            <td>${tariff.nameProperty()}</td>
                            <td><a href="index.jsp?object=${tariff.numberProperty()}&table=tariff">More</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:when test="${(!empty param.table) && (param.table=='tariff')}">
                <c:set var="tariff" value="${DBworker.findTariffByID(param.object).get(0)}"/>
                <form action="modifyItem.jsp">
                    <table>
                        <tr>
                            <td>Tariff Id</td>
                            <td>${tariff.numberProperty()}</td>
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td>${tariff.nameProperty()}</td>
                        </tr>
                        <tr>
                            <td>Speed</td>
                            <td>${tariff.speedProperty()}</td>
                        </tr>
                        <tr>
                            <td>Cost</td>
                            <td>${tariff.costProperty()}</td>
                        </tr>
                        <tr>
                            <td>&nbsp</td>
                            <td class="modify"><button  type="submit" name="mTariff" value="${tariff.numberProperty()}">Modify</button> </td>
                        </tr>
                    </table>
                </form>
            </c:when>

            <c:when test="${(!empty param.button) && (param.button=='Services')}">
                <table>
                    <tr>
                        <th>&nbsp</th>
                        <th>Service Id</th>
                        <th>Tariff ID</th>
                        <th>Customer Id</th>
                        <th>Status</th>
                        <th>Actual Order Id</th>
                        <th>&nbsp</th>
                    </tr>
                    <c:forEach var="service" items="${DBworker.services}">
                        <tr>
                            <td><input type="checkbox" name="checkbox" value="${service.numberProperty()}"/> </td>
                            <td>${service.numberProperty()}</td>
                            <td><a href="index.jsp?object=${service.tariffID}&table=tariff" class="links">${service.tariffID}</a></td>
                            <td><a href="index.jsp?object=${service.customerID}&table=customer" class="links">${service.customerID}</a></td>
                            <td>${service.stringStatus}</td>
                            <td><a href="index.jsp?object=${service.actualOrderID}&table=order" class="links">${service.actualOrderID}</a></td>
                            <td><a href="index.jsp?object=${service.numberProperty()}&table=service">More</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:when test="${(!empty param.table) && (param.table=='service')}">
                <c:set var="service" value="${DBworker.findServiceByID(param.object).get(0)}"/>
                <table>
                    <tr>
                        <td>Service Id</td>
                        <td>${service.numberProperty()}</td>
                    </tr>
                    <tr>
                        <td>Tariff Id</td>
                        <td><a href="index.jsp?object=${service.tariffID}&table=tariff" class="links">${service.tariffID}</a></td>
                    </tr>
                    <tr>
                        <td>Customer Id</td>
                        <td><a href="index.jsp?object=${service.customerID}&table=customer" class="links">${service.customerID}</a></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>${service.stringStatus}</td>
                    </tr>
                    <tr>
                        <td>Actual Order Id</td>
                        <td><a href="index.jsp?object=${service.actualOrderID}&table=order" class="links">${service.actualOrderID}</a></td>
                    </tr>
                </table>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>

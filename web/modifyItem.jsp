<%--
  Created by IntelliJ IDEA.
  User: Iorlov
  Date: 22.02.2016
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/formStyle.css"/>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="scripts/formScript.js"></script>

    <c:choose>
        <c:when test="${(!empty param.create) && (param.create=='customer')}">
            <title>Create Customer</title>
        </c:when>
        <c:when test="${(!empty param.create) && (param.create=='order')}">
            <title>Create Order</title>
        </c:when>
        <c:when test="${(!empty param.create) && (param.create=='tariff')}">
            <title>Create Tariff</title>
        </c:when>
        <c:when test="${(!empty param.mCustomer)}">
            <title>Modify Customer: ${param.mCustomer}</title>
        </c:when>
        <c:when test="${(!empty param.mTariff)}">
            <title>Modify Tariff: ${param.mTariff}</title>
        </c:when>
        <c:when test="${(!empty param.mOrder)}">
            <title>Modify Order: ${param.mOrder}</title>
        </c:when>
    </c:choose>
    <jsp:useBean id="DBworker" class="util.DBWorker" scope="session"/>
</head>
<body>
<c:choose>
    <c:when test="${(!empty param.create) && (param.create=='customer')}">
        <div class="form" id="customerForm">
            <form action="newcustomer" method="post">
                <label for="customerName">Name</label>
                <input type="text" name="name" id="customerName">
                <label for="phone">Phone</label>
                <input type="text" name="phone" id="phone">
                <label for="address">Address</label>
                <input type="text" name="address" id="address">
                <input type="submit" value="Create customer">
            </form>
        </div>
    </c:when>
    <c:when test="${(!empty param.mCustomer)}">
        <c:set var="customer" value="${DBworker.findCustomerByID(param.mCustomer).get(0)}"/>
        <div class="form" id="customerForm">
            <form action="mCustomer" method="post">
                <label for="mcustomerName">Name</label>
                <input type="text" name="name" id="mcustomerName" value="${customer.nameProperty()}">
                <label for="mphone">Phone</label>
                <input type="text" name="phone" id="mphone" value="${customer.phonenumProperty()}">
                <label for="maddress">Address</label>
                <input type="text" name="address" id="maddress" value="${customer.adressProperty()}">
                <input type="submit" value="Modify customer">
                <input type="hidden" name="id" value="${customer.numberProperty()}">
            </form>
        </div>
    </c:when>
    <c:when test="${(!empty param.create) && (param.create=='tariff')}">
        <div class="form" id="tariffForm">
            <form action="newtariff" method="post">
                <label for="name">Name</label>
                <input type="text" name="name" id="name">
                <label for="speed">Speed</label>
                <input type="text" name="speed" id="speed">
                <label for="cost">Cost</label>
                <input type="text" name="cost" id="cost">
                <input type="submit" value="Create tariff">
            </form>
        </div>
    </c:when>
    <c:when test="${(!empty param.mTariff)}">
        <c:set var="tariff" value="${DBworker.findTariffByID(param.mTariff).get(0)}"/>
        <div class="form" id="tariffForm">
            <form action="mTariff" method="post">
                <label for="mname">Name</label>
                <input type="text" name="name" id="mname" value="${tariff.nameProperty()}">
                <label for="mspeed">Speed</label>
                <input type="text" name="speed" id="mspeed" value="${tariff.speedProperty()}">
                <label for="mcost">Cost</label>
                <input type="text" name="cost" id="mcost" value="${tariff.costProperty()}">
                <input type="submit" value="Modify tariff">
                <input type="hidden" name="id" value="${tariff.numberProperty()}">
            </form>
        </div>
    </c:when>
    <c:when test="${(!empty param.create) && (param.create=='order')}">
        <div class="form" id="orderForm">
            <form action="neworder" method="post">
                <label for="customer">Customer</label>
                <select type="text" name="customer" id="customer">
                    <c:forEach var="customer" items="${DBworker.customers}">
                        <option value=${customer.numberProperty()}>id: ${customer.numberProperty()} name:${customer.nameProperty()}</option>
                    </c:forEach>
                </select>
                <label for="tariff">Tariff</label>
                <select type="text" name="tariff" id="tariff">
                    <c:forEach var="tariff" items="${DBworker.tariffs}">
                        <option value=${tariff.numberProperty()}>id: ${tariff.numberProperty()} name:${tariff.nameProperty()}</option>
                    </c:forEach>
                </select>
                <label for="sum">Sum</label>
                <label id="sum"></label>
                <input type="submit" value="Create order">
            </form>
        </div>
    </c:when>
    <c:when test="${(!empty param.mOrder)}">
        <c:set var="order" value="${DBworker.findOrderByID(param.mOrder).get(0)}"/>
        <div class="form" id="orderForm">
            <form action="mOrder" method="post">
                <label for="customer">Customer</label>
                <select type="text" name="customer" id="customer">
                    <c:forEach var="customer" items="${DBworker.customers}">
                        <option value=${customer.numberProperty()}>id: ${customer.numberProperty()} name:${customer.nameProperty()}</option>
                    </c:forEach>
                </select>
                <label for="tariff">Tariff</label>
                <select type="text" name="tariff" id="tariff">
                    <c:forEach var="tariff" items="${DBworker.tariffs}">
                        <option value=${tariff.numberProperty()}>id: ${tariff.numberProperty()} name:${tariff.nameProperty()}</option>
                    </c:forEach>
                </select>
                <label for="sum">Sum</label>
                <label id="sum"></label>
                <input type="submit" value="Modify order">
                <input type="hidden" name="id" value="${order.numberProperty()}">
            </form>
        </div>
    </c:when>
</c:choose>
</body>
</html>

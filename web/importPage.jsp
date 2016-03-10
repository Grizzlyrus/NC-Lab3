<%--
  Created by IntelliJ IDEA.
  User: iorlov
  Date: 10.03.2016
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Import</title>
</head>
<body>
<form method="post" action="Import">

    <table style="width: 100%;height: 70%;">
        <tr style="height: 70%">
            <td>
                <textarea rows="5" cols="64" name="xmlText" style="width: 100%;height: 100%;"></textarea>
            </td>
        </tr>
        <tr style="text-align: right;">
            <td>
                <input type="submit" value="import"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

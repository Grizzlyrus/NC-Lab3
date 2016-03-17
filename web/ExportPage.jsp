<%--
  Created by IntelliJ IDEA.
  User: iorlov
  Date: 14.03.2016
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Export Page</title>
</head>
<body>
    <pre>${sessionScope.data}</pre>
<a href="ExportHTML?table=${param.table}">Show in HTML</a>
</body>
</html>

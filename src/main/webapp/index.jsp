<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="/generate">Generate Ticket</a>

<p/>

<uL>
    <c:forEach items="${tList}" var="aTicket">
        <li> <c:out escapeXml="false" value="${aTicket}"/>  </li>
    </c:forEach>
</uL>

<img src="img/login_form.jpg"/>
</body>
</html>

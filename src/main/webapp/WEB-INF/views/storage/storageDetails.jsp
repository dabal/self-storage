<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>

<table>
    <tr>
        <td><spring:message code="storagedetails.itemlist.name"/></td>
        <td><spring:message code="storagedetails.itemlist.quantity"/></td>
        <td><spring:message code="storagedetails.itemlist.metric"/></td>
        <td><spring:message code="storagedetails.itemlist.category"/></td>
    </tr>
    <c:forEach items="${items}" var="item">
        <tr>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.quantity}"/></td>
            <td><c:out value="${item.metric.name}"/></td>
            <td><c:out value="${item.category.name}"/></td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../footer.jsp"/>


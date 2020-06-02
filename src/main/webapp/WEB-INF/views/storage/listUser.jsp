<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>

<table>
    <c:forEach items="${storages}" var="storage">
        <tr>
            <td><c:out value="${storage.name}"/></td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../footer.jsp"/>


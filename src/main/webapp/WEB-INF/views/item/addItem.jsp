<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>


<form:form modelAttribute="itemDto" method="POST">
    <form:hidden path="id"/>
    <spring:message code="addItemForm.name"/> <form:input path="name"/><form:errors path="name" cssClass="error"/><br/>
    <spring:message code="addItemForm.quantity"/><form:input path="quantity"/><form:errors path="quantity"
                                                                                           cssClass="error"/><br/>
    <spring:message code="addItemForm.category"/><form:select items="${categories}" path="category" itemValue="id"
                                                              itemLabel="name"/><form:errors path="category"
                                                                                             cssClass="error"/><br/>
    <spring:message code="addItemForm.metric"/><form:select items="${metrics}" path="metric" itemValue="id"
                                                            itemLabel="name"/><form:errors path="metric"
                                                                                           cssClass="error"/><br/>
    <spring:message code="addItemForm.storage"/> <form:select items="${storageList}" path="storage" itemValue="id"
                                                              itemLabel="name"/>
    <form:errors path="storage" cssClass="error"/><br/>
    <input type="submit" value="<spring:message code="addItemForm.submit"/>"></input>
</form:form>


<jsp:include page="../footer.jsp"/>


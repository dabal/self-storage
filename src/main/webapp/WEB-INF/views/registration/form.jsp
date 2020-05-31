<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>

<form:form modelAttribute="userDto" method="POST">
    <div><label> <spring:message code="registrationForm.username"/>: <form:input path="username"/><form:errors
            path="username" cssClass="error"/>
    </label></div>
    <div><label> <spring:message code="registrationForm.password"/>:<form:password path="password"/><form:errors
            path="password" cssClass="error"/> </label>
    </div>
    <div><label> <spring:message code="registrationForm.retypePassword"/>: <form:password path="password1"/><form:errors
            path="password1" cssClass="error"/>
    </label></div>
    <div><input type="submit" value="<spring:message code="registrationForm.submit"/>:"/></div>

</form:form>

<jsp:include page="../footer.jsp"/>


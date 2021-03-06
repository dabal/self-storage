<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"/>
<form method="post">
    <div class="form-group">
        <div><label> <spring:message code="loginForm.username"/>: <input type="text" name="username"
                                                                         class="form-control"/>
        </label></div>
        <div><label> <spring:message code="loginForm.password"/><input type="password" name="password"
                                                                       class="form-control"/> </label></div>
        <div><input type="submit" value="<spring:message code="loginForm.submit"/>" class="btn btn-primary"/></div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
</form>
<spring:message code="loginForm.dontHaveAnAccount"/>
<a href="<c:url value="/registration"/>">
    <spring:message code="loginForm.registerLabel"/></a>
<jsp:include page="footer.jsp"/>


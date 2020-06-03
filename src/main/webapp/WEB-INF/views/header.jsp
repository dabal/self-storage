<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>App titles</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="bootstrap/dist/css/bootstrap.css"/>
</head>
<body>
<h1><spring:message code="app.title"/></h1>
<a href="/about">about</a><br>
<sec:authorize access="isAuthenticated()">
<form action="<c:url value="/logout"/>" method="post">
    <input type="submit" value="Wyloguj"><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><br>
</form>

    <sec:authentication property="principal.username"></sec:authentication>
</sec:authorize>



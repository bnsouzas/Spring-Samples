<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tags:pageTemplate titulo="titulo.login.form" backWidth="3">
	<c:if test="${param.error != null}">
        <div class="alert alert-danger">
            <p><spring:message code="login.invalid" /></p>
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">
            <p><spring:message code="login.logout" /></p>
        </div>
    </c:if>
	<form:form servletRelativeAction="/login" method="post">
        <spring:message code="login.username" var="messageUsername" />
        <spring:message code="login.senha" var="messageSenha" />
        <div class="input-group form-group">
            <label for="username" class="input-group-addon"><i class="glyphicon glyphicon-user"></i></label>
            <input type="text" name="username" class="form-control" placeholder="${messageUsername}" />
        </div>
        <div class="input-group form-group">
            <label for="password" class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></label>
            <input type="password" name="password" class="form-control" placeholder="${messageSenha}" />
        </div>
        <div class="form-group">
        	<button type="submit" class="btn btn-primary btn-block"><spring:message code="botao.logar" /></button>
        </div>
    </form:form>
</tags:pageTemplate>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="titulo.usuario.signin" backWidth="6">
	<form:form action="${spring:mvcUrl('UC#register').build()}" method="post"
		commandName="usuario" cssClass="form-horizontal">
		<div class="form-group">
			<form:label path="email" cssClass="control-label col-sm-2"><spring:message code="usuario.email"/></form:label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="nome" cssClass="control-label col-sm-2"><spring:message code="usuario.nome"/></form:label>
			<div class="col-sm-10">
				<form:input path="nome" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="username" cssClass="control-label col-sm-2"><spring:message code="usuario.username"/></form:label>
			<div class="col-sm-10">
				<form:input path="username" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="senha" cssClass="control-label col-sm-2"><spring:message code="usuario.senha"/></form:label>
			<div class="col-sm-10">
				<form:password path="senha" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
				<form:button class="btn btn-success"><spring:message code="botao.cadastrar" /></form:button>
			</div>
		</div>
			<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.usuario'].allErrors}">
				<p class="alert alert-danger">
					<form:errors path="email" />
					<form:errors path="nome" />
					<form:errors path="username" />
					<form:errors path="senha" />
				</p>
			</c:if>
	</form:form>
</tags:pageTemplate>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate titulo="${artigo.titulo}" backWidth="6">
	<form:form action="${spring:mvcUrl('AC#save').build()}" method="post" commandName="artigo" cssClass="form-horizontal">
		<div class="form-group">
			<form:label path="titulo" class="control-label col-sm-2"><spring:message code="artigo.titulo" /></form:label>
			<div class="col-sm-10">
				<form:input path="titulo" cssClass="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<form:label path="conteudo" class="control-label col-sm-2"><spring:message code="artigo.conteudo" /></form:label>
			<div class="col-sm-10">
				<form:textarea path="conteudo" cssClass="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<form:label path="resumo" class="control-label col-sm-2"><spring:message code="artigo.resumo" /></form:label>
			<div class="col-sm-10">
				<form:textarea path="resumo" cssClass="form-control"/>
			</div>
		</div>
		<form:hidden path="id"/>
		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
				<form:button class="btn btn-success"><spring:message code="botao.salvar" /></form:button>
			</div>
		</div>
		
		<c:if test="${not empty requestScope['org.springframework.validation.BindingResult.artigo'].allErrors}">
			<p class="alert alert-danger">
				<form:errors path="id" />
				<form:errors path="usuario" />
				<form:errors path="titulo" />
				<form:errors path="resumo" />
				<form:errors path="conteudo" />
			</p>
		</c:if>
	</form:form>
</tags:pageTemplate>
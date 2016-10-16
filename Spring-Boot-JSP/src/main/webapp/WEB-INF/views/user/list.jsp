<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="titulo.usuario.list" backWidth="9">
	<div class="card-panel">
		<div class="card-panel-header">
		</div>
		<div class="card-panel-body">
			<c:forEach items="${users}" var="u" varStatus="sts">
				<div class="card card-small">
					<div class="card-header">
						<h4>${u.username}</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<label class="col-sm-4 col-xs-4"><spring:message code="usuario.nome"/></label>
							<span class="col-sm-8">${u.nome}</span>
						</div>
						<div class="row">
							<label class="col-sm-4 col-xs-4"><spring:message code="usuario.email"/></label>
							<span class="col-sm-8 col-xs-8">${u.email}</span>
						</div>
						<div class="row">
							<label class="col-sm-4 col-xs-4"><spring:message code="usuario.roles"/></label>
							<span class="col-sm-8 col-xs-8">
								<c:forEach items="${u.roles}" var="r" varStatus="loop">
					 				${r.nome}<c:if test="${!loop.last}">, </c:if>
					 			</c:forEach>
					 		</span>
						</div>
					</div>
					<div class="card-footer">
						<div class="pull-right">
							<form:form action="${spring:mvcUrl('UC#edit').arg(0,u.username).build()}" method="GET">
					 			<button type="submit" class="btn btn-info"><i class="glyphicon glyphicon-edit"></i></button>
					 		</form:form>
					 		<spring:message code="palavra.usuario" var="palavraUsuario" />
					 		<spring:message code="confirm.delete" arguments="${palavraUsuario}" var="confirmDelete" />
					 		<form:form action="${spring:mvcUrl('UC#delete').arg(0, u.username).build()}" method="POST" onsubmit="return confirmSubmit(this,'${confirmDelete}');" >
					 			<button type="submit" class="btn btn-danger" id="#toggle"><i class="glyphicon glyphicon-remove"></i></button>
					 		</form:form>
				 		</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="card-panel-footer">
		</div>
	</div>
	<c:if test="${sucesso != null}">
		<p class="alert alert-success">${sucesso}</p>
	</c:if>
</tags:pageTemplate>
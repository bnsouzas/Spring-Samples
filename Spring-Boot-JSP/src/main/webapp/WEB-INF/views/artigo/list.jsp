<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<tags:pageTemplate titulo="titulo.artigo.list" backWidth="9">
	<spring:message code="palavra.artigo" var="palavraArtigo" />
	<div class="table-responsive">
		<table class="table table-condensed table-striped table-hover">
			<thead>
				<tr>
					<th><spring:message code="artigo.titulo" /></th>
					<th><spring:message code="artigo.resumo" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!artigos.isEmpty()}">
					<c:forEach items="${artigos}" var="a">
						<tr>
							<td>${a.titulo}</td>
							<td>${a.resumo}</td>
							<td>
								<form:form action="${spring:mvcUrl('AC#edit').arg(0,a.id).build()}" method="get">
									<button type="submit"class="btn btn-info"><spring:message code="botao.edit" /></button>
								</form:form>
								<form:form action="${spring:mvcUrl('AC#delete').arg(0,a.id).build()}" method="post">
									<button type="submit"class="btn btn-danger"><spring:message code="botao.delete" /></button>
								</form:form>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${artigos.isEmpty()}">
					<tr class="warning">
						<td colspan="3"><spring:message code="tabela.vazia" arguments="${palavraArtigo}" /></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<form:form action="${spring:mvcUrl('AC#add').build()}" method="post" >
		<button type="submit"class="btn btn-success"><spring:message code="botao.incluir" arguments="${palavraArtigo}" /></button>
	</form:form>
</tags:pageTemplate>
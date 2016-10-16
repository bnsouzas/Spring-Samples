<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate titulo="titulo.home.index" backWidth="9">
	<c:if test="${sucesso != null}">
		<p class="alert alert-success">${sucesso}</p>
	</c:if>
</tags:pageTemplate>
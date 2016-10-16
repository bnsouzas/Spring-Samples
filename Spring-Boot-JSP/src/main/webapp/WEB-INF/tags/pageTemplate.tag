<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true" description="tamanho do painel de 1 a 10" %>
<%@ attribute name="backWidth" required="true" %>
<%@ attribute name="cssClass" %>
<%@ attribute name="stylesheet" fragment="true" %>
<%@ attribute name="scriptHeader" fragment="true" %>
<%@ attribute name="scriptFooter" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="theme-color" content="RoyalBlue">
	<spring:message code="${titulo}" text="${titulo}" var="tituloPagina" />
	<title><spring:message code="projeto.titulo" /> - ${tituloPagina}</title>
	<spring:url value="/resources/theme/css" var="cssUrl" />
	<spring:url value="/resources/theme/js" var="jsUrl" />
	<spring:url value="/resources/jquery/jquery-3.1.1.min.js" var="jqueryUrl" />
	<spring:url value="/resources/bootstrap/css" var="bootstrapCSS" />
	<spring:url value="/resources/bootstrap/js" var="bootstrapJS" />
	<spring:url value="/resources/bootstrap/fonts" var="bootstrapFonts" />
	
	<link href="${bootstrapCSS}/bootstrap-theme.min.css" rel="stylesheet" />
	<link href="${bootstrapCSS}/bootstrap.min.css" rel="stylesheet" />
	<jsp:invoke fragment="stylesheet" />
	<link href="${cssUrl}/main.css" rel="stylesheet" />
	<link href="${cssUrl}/card.css" rel="stylesheet" />
	
	<script src="${jqueryUrl}" type="text/javascript"></script>
	<jsp:invoke fragment="scriptHeader" />
</head>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/views/header.jsp" %>
		<main class="back-width-${backWidth} ${cssClass}">
			<h3 style="margin: 5px auto;">${tituloPagina}</h3><hr style="margin: 10px auto;"/>
			<jsp:doBody />
		</main>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp" %>
	<jsp:invoke fragment="scriptFooter" />
	<script src="${jsUrl}/main.js" type="text/javascript"></script>
	<script src="${bootstrapJS}/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
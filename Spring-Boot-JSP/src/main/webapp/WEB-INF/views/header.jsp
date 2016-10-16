<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<header>
	<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapse-header-navbar" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
		        </button>
				<a class="navbar-brand" href="${spring:mvcUrl('HC#index').build()}"><spring:message code="projeto.titulo" /></a>
			</div>
			<div class="collapse navbar-collapse" id="collapse-header-navbar">
				<ul class="nav navbar-nav">
					<security:authorize access="hasRole('ROLE_USER')">
						<li><a href="${spring:mvcUrl('AC#list').build()}"><spring:message code="menu.header.artigo" /></a></li>
					</security:authorize>
					
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="${spring:mvcUrl('UC#list').build()}"><spring:message code="menu.header.usuario" /></a></li>
					</security:authorize>
				</ul>
				<ul class="nav navbar-nav pull-right hidden-xs">
					<security:authorize access="!isAuthenticated()">
						<spring:url value="/login" var="loginUrl" htmlEscape="true" />
						<li><a href="${loginUrl}" ><spring:message code="menu.header.login" /></a></li>
						<li><a href="${spring:mvcUrl('UC#signIn').build()}"><spring:message code="menu.header.cadastrese" /></a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<spring:url value="/logout" var="logoutUrl" htmlEscape="true" />
						<li>
							<a href="javascript:void(0)" onclick="document.getElementById('logoutForm').submit()" >
								<spring:message code="menu.header.logout" />
							</a>
							<form:form action="${logoutUrl}" method="post" id="logoutForm" cssStyle="display: inline;" >
							</form:form>
						</li>
					</security:authorize>
				</ul>
				<ul class="nav navbar-nav visible-xs">
					<security:authorize access="!isAuthenticated()">
						<spring:url value="/login" var="loginUrl" htmlEscape="true" />
						<li><a href="${loginUrl}" ><spring:message code="menu.header.login" /></a></li>
						<li><a href="${spring:mvcUrl('UC#signIn').build()}"><spring:message code="menu.header.cadastrese" /></a></li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">
						<spring:url value="/logout" var="logoutUrl" htmlEscape="true" />
						<li>
							<a href="javascript:void(0)" onclick="document.getElementById('logoutForm').submit()" >
								<spring:message code="menu.header.logout" />
							</a>
							<form:form action="${logoutUrl}" method="post" id="logoutForm" cssStyle="display: inline;" >
							</form:form>
						</li>
					</security:authorize>
				</ul>
			</div>
		</div>
	</nav>
</header>
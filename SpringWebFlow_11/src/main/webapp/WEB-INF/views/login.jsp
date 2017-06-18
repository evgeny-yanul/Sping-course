<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" href="<c:url value="/resources/css/home.css" />"
		rel="stylesheet">			
	<link type="text/css"
		href="<c:url value="/resources/dijit/themes/tundra/tundra.css" />"
		rel="stylesheet">	
	
	<script type="text/javascript"
		src="<c:url value="/resources/dojo/dojo.js" />">		
	</script>	
	<script type="text/javascript"
		src="<c:url value="/resources/spring/Spring.js" />">		
	</script>	
	<script type="text/javascript"
		src="<c:url value="/resources/spring/Spring-Dojo.js" />">		
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body class="tundra">
	<form:form method="POST" modelAttribute="user" class="box login">
		<fieldset class="boxBody">
			<span style="float: right"> 
				<a href="?lang=en"><spring:message code="en" /></a> 
				<a href="?lang=ru"><spring:message code="ru" /></a>
			</span>
			<form:label path="username">
				<spring:message code="username" />
			</form:label>			
			<form:input path="username" />
			
			<script type="text/javascript">
				Spring.addDecoration(new Spring.ElementDecoration({
									elementId : "username",
									widgetType : "dijit.form.ValidationTextBox",
									
									widgetAttrs : {
										promptMessage : "<spring:message code="enter_username" />"
									}
								}));
			</script>
			
			<form:label path="password">
				<spring:message code="password" />
			</form:label>				
			<form:password path="password"/>
			
			<script type="text/javascript">
				Spring.addDecoration(new Spring.ElementDecoration({
									elementId : "password",
									widgetType : "dijit.form.ValidationTextBox",
									
									widgetAttrs : {
										promptMessage : "<spring:message code="enter_password" />"
									}
								}));
			</script>
					
			<c:if
				test="${not empty flowRequestContext.messageContext.allMessages}">
				<ul class="red_messages">
					<c:forEach items="${flowRequestContext.messageContext.allMessages}"
						var="message">
						<li>${message.text}</li>
					</c:forEach>
				</ul>
			</c:if>						
		</fieldset>
		<footer>
		<a href="${flowExecutionUrl}&_eventId=createUser"><spring:message code="create-user" /></a>
		<input type="submit" class="btnLogin" value="<spring:message code="login"/>" name="_eventId_submit">				
		</footer>
	</form:form>
</body>
</html>
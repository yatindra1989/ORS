<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>

<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Merit List</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">

	<sf:form action="getMeritList" commandName="form" method="post">
		<div class="text-center container">
			<h1 class="text-primary">
				<s:message code="label.meritList"></s:message>
			</h1>

			<c:if test="${error }">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-alert"></span> ${error }
				</div>
			</c:if>
		</div>
		<br>
		<div class="table-responsive">
			<table class="table table-bordered tabled-condensed table-hover">
				<thead>
					<tr id="trcolor">
						<th id="thcolor"><s:message code="label.sNo"></s:message></th>
						<th id="thcolor"><s:message code="label.rollNo"></s:message></th>
						<th id="thcolor"><s:message code="label.name"></s:message></th>
						<th id="thcolor"><s:message code="label.physics"></s:message></th>
						<th id="thcolor"><s:message code="label.chemistry"></s:message></th>
						<th id="thcolor"><s:message code="label.math"></s:message></th>
						<th id="thcolor"><s:message code="label.total"></s:message></th>

					</tr>
				</thead>
				<c:set var="index" value="${((form.pageNo-1)*form.pageSize)+1 }"></c:set>
				<c:forEach items="${meritList}" var="merit">
					<c:if test="${merit.physics>=35 }">
						<tbody>
							<tr>
								<td>${index}</td>
								<td>${merit.rollNo }</td>
								<td>${merit.name }</td>
								<td>${merit.physics }</td>
								<td>${merit.chemistry }</td>
								<td>${merit.maths }</td>
								<td>${merit.maths + merit.chemistry + merit.physics}</td>
							</tr>
						</tbody>
						<c:set var="index" value="${index+1 }"></c:set>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<br>
		<div class="text-center">
			<sf:button class="btn btn-primary" type="submit" name="operation">
				<span class="glyphicon glyphicon-arrow-left"></span>
				<s:message code="label.back"></s:message>

			</sf:button>
		</div>
		<sf:hidden path="pageNo" />
		<sf:hidden path="pageSize" />
	</sf:form>
</body>
</html>

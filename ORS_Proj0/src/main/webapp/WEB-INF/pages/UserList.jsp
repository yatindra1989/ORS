<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>

<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>User List</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">

	<sf:form action="search" method="post" commandName="form">
		<div class="text-center container">
			<h1 class="text-primary">
				<s:message code="label.userList"></s:message>
			</h1>

			<c:if test="${not empty success }">
				<div class="alert alert-success" role="alert">
					<span class="glyphicon glyphicon-saved"></span> ${success }
				</div>
			</c:if>
			<c:if test="${not empty error }">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-alert"></span> ${error }
				</div>
			</c:if>
		</div>
		<br>


		<div class="container">
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<sf:input path="firstName" cssClass="form-control"
						placeholder="First Name" />
				</div>
				<div class="col-md-2 col-md-offset-1">
					<sf:input path="login" cssClass="form-control"
						placeholder="Login ID" />
				</div>
				<div class="col-md-2 col-md-offset-1">
					<sf:select path="roleId" cssClass="form-control" size="0">
						<sf:option value="0">Role</sf:option>
						<sf:options items="${roleList}" itemValue="id"
							itemLabel="roleName"></sf:options>
					</sf:select>
				</div>
				<div class="col-md-1">
					<button class="form-control btn btn-primary" type="submit"
						name="operation" value="Search">
						<s:message code="label.search"></s:message>
					</button>
				</div>
				<div class="col-md-1">
					<a class="form-control btn btn-primary"
						href="<c:url value="/ctl/User/search"></c:url>"><s:message
							code="label.reset"></s:message></a>
				</div>
			</div>
		</div>
		<br>

		<c:choose>
			<c:when test="${not empty list }">
				<div class="table-responsive">
					<table class="table table-bordered table-condensed table-hover">
						<thead>
							<tr id="trcolor">
								<th id="thcolor" align="left" width="8%"><input
									type="checkbox" id="selectall" onclick="checkAll(this)">
									<s:message code="label.selectAll"></s:message></th>
								<th id="thcolor"><s:message code="label.sNo"></s:message></th>
								<th id="thcolor"><s:message code="label.firstName"></s:message></th>
								<th id="thcolor"><s:message code="label.lastName"></s:message></th>
								<th id="thcolor"><s:message code="label.login"></s:message></th>
								<th id="thcolor"><s:message code="label.gender"></s:message></th>
								<th id="thcolor"><s:message code="label.dob"></s:message></th>
								<th id="thcolor"><s:message code="label.mobileNo"></s:message></th>
								<th id="thcolor"><s:message code="label.role"></s:message></th>
								<th id="thcolor"><s:message code="label.edit"></s:message></th>
							</tr>
						</thead>

						<c:set var="index" value="${((form.pageNo-1)*form.pageSize)+1 }"></c:set>

						<c:forEach items="${list }" var="user">
							<tbody>
								<tr>
									<td><input type="checkbox" class="mycheckbox" name="ids"
										value="${user.id }"
										${(sessionScope.user.id==user.id)?"disabled":"" }></td>
									<td>${index}</td>
									<td>${user.firstName }</td>
									<td>${user.lastName }</td>
									<td>${user.login }</td>
									<td>${user.gender }</td>
									<td><fmt:formatDate value="${user.dob }" /></td>
									<td>${user.mobileNo }</td>
									<td><c:choose>
											<c:when test="${user.roleId==1 }">Admin</c:when>
											<c:when test="${user.roleId==2 }">Student</c:when>
											<c:when test="${user.roleId==3 }">College</c:when>
											<c:when test="${user.roleId==4 }">Kiosk</c:when>
											<c:when test="${user.roleId==5 }">Faculty</c:when>
										</c:choose></td>

									<td><c:url value="/ctl/User" var="edit">
											<c:param name="id" value="${user.id }"></c:param>
										</c:url><a href='<c:out value="${edit }"></c:out>'><span
											class="glyphicon glyphicon-edit"></span></a></td>
								</tr>
							</tbody>
							<c:set var="index" value="${index+1}"></c:set>
						</c:forEach>
					</table>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-1">
							<a class="btn btn-primary"
								href="<c:url value="/ctl/User"></c:url>"><s:message
									code="label.new"></s:message> <span
								class="glyphicon glyphicon-plus"></span> </a>

						</div>
						<div class="col-md-10">
							<div class="col-md-4 text-right">
								<c:choose>
									<c:when test="${form.pageNo==1 }">
										<button class="btn btn-primary" type="submit" name="operation"
											disabled="disabled" value="Previous">
											<s:message code="label.previous"></s:message>
											<span class="glyphicon glyphicon-circle-arrow-left"></span>
										</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-primary" type="submit" name="operation"
											value="Previous">
											<s:message code="label.previous"></s:message>
											<span class="glyphicon glyphicon-circle-arrow-left"></span>
										</button>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-4 text-center">
								<c:if test="${buttonNumber>0 }">
									<c:forEach var="button" begin="1" end="${buttonNumber }"
										step="1">
										<button class="btn btn-primary" type="submit" name="pageNo"
											${(form.pageNo==button) ? 'disabled' : ''} value="${button }">${button}</button>
									</c:forEach>
								</c:if>
							</div>
							<div class="col-md-4">
								<c:choose>
									<c:when test="${buttonNumber==form.pageNo }">
										<button class="btn  btn-primary" type="submit"
											name="operation" value="Next" disabled="disabled">
											<s:message code="label.next"></s:message>
											<span class="glyphicon glyphicon-circle-arrow-right"></span>
										</button>
									</c:when>
									<c:otherwise>
										<button class="btn  btn-primary" type="submit"
											name="operation" value="Next">
											<s:message code="label.next"></s:message>
											<span class="glyphicon glyphicon-circle-arrow-right"></span>
										</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-md-1">
							<button class="btn  btn-primary" type="submit" name="operation"
								value="Delete">
								<s:message code="label.delete"></s:message>
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</div>
					</div>
				</div>
				<br>
				<br>
				<sf:hidden path="pageNo" />
				<sf:hidden path="pageSize" />
			</c:when>
			<c:otherwise>
				<br>
				<br>
				<table class="table table-bordered table-striped">
					<thead>
						<tr id="trcolor">
							<th id="thcolor" align="left" width="8%"><input
								type="checkbox" id="selectall" onclick="checkAll(this)">
								<s:message code="label.selectAll"></s:message></th>
							<th id="thcolor"><s:message code="label.sNo"></s:message></th>
							<th id="thcolor"><s:message code="label.firstName"></s:message></th>
							<th id="thcolor"><s:message code="label.lastName"></s:message></th>
							<th id="thcolor"><s:message code="label.login"></s:message></th>
							<th id="thcolor"><s:message code="label.gender"></s:message></th>
							<th id="thcolor"><s:message code="label.dob"></s:message></th>
							<th id="thcolor"><s:message code="label.mobileNo"></s:message></th>
							<th id="thcolor"><s:message code="label.edit"></s:message></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
				<c:if test="${empty error }">
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<h2 class="text-center text-danger">
								<s:message code="message.noRecordFound"></s:message>
							</h2>
						</div>
					</div>
				</c:if>
				<br>
				<br>
			</c:otherwise>
		</c:choose>

	</sf:form>
	<%@include file="Footer.jsp"%>
</body>
</html>

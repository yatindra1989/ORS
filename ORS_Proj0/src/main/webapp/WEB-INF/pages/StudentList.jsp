<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>
<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Student List</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<sf:form action="search" method="post" commandName="form">
		<div class="text-center container">

			<h1 class="text-primary">
				<s:message code="label.studentList"></s:message>
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
					<sf:input path="lastName" cssClass="form-control"
						placeholder="Last Name" />
				</div>
				<div class="col-md-2 col-md-offset-1">
					<sf:input path="emailId" cssClass="form-control"
						placeholder="Email ID" />
				</div>
				<div class="col-md-1">
					<button class="form-control btn btn-primary" type="submit"
						name="operation" value="Search">
						<s:message code="label.search"></s:message>
					</button>
				</div>
				<div class="col-md-1">
					<a class="form-control btn btn-primary"
						href="<c:url value="/ctl/Student/search"></c:url>"><s:message
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
								<c:if test="${sessionScope.user.roleId==1 }">
									<th id="thcolor" align="left" width="8%"><input
										type="checkbox" id="selectall" onclick="checkAll(this)">Select
										All</th>
								</c:if>
								<th id="thcolor"><s:message code="label.sNo"></s:message></th>
								<th id="thcolor"><s:message code="label.firstName"></s:message></th>
								<th id="thcolor"><s:message code="label.lastName"></s:message></th>
								<th id="thcolor"><s:message code="label.collegeName"></s:message></th>
								<th id="thcolor"><s:message code="label.dob"></s:message></th>
								<th id="thcolor"><s:message code="label.mobileNo"></s:message></th>
								<th id="thcolor"><s:message code="label.email"></s:message></th>
								<c:if test="${sessionScope.user.roleId==1 }">
									<th id="thcolor"><s:message code="label.edit"></s:message></th>
								</c:if>
							</tr>
						</thead>
						<c:set var="index" value="${((form.pageNo-1)*form.pageSize)+1 }"></c:set>
						<c:forEach items="${list }" var="student">
							<tbody>
								<tr>
									<c:if test="${sessionScope.user.roleId==1 }">
										<td><input type="checkbox" class="mycheckbox" name="ids"
											value="${student.id }"></td>
									</c:if>
									<td>${index}</td>
									<td>${student.firstName}</td>
									<td>${student.lastName }</td>
									<td>${student.collegeName }</td>
									<td><fmt:formatDate value="${student.dob }" /></td>
									<td>${student.mobileNo }</td>
									<td>${student.emailId }</td>
									<c:if test="${sessionScope.user.roleId==1 }">
										<td><c:url value="/ctl/Student" var="edit">
												<c:param name="id" value="${student.id}"></c:param>
											</c:url> <a href='<c:out value="${edit }"></c:out>'><span
												class="glyphicon glyphicon-edit"></span></a></td>
									</c:if>

								</tr>
							</tbody>
							<c:set var="index" value="${index+1 }"></c:set>
						</c:forEach>
					</table>
				</div>

				<div class="container-fluid">
					<div class="row">
						<div class="col-md-1">
							<c:if test="${sessionScope.user.roleId==1 }">
								<a class="btn btn-primary"
									href="<c:url value="/ctl/Student"></c:url>"><s:message
										code="label.new"></s:message> <span
									class="glyphicon glyphicon-plus"></span> </a>
							</c:if>
						</div>
						<div class="col-md-10">
							<div class="col-md-4 text-right">
								<c:choose>
									<c:when test="${form.pageNo==1 }">
										<sf:button class="btn btn-primary" type="submit" name="operation"
											disabled="true" value="Previous">
											<span class="glyphicon glyphicon-circle-arrow-left"></span>
											<s:message code="label.previous"></s:message>
										</sf:button>
									</c:when>
									<c:otherwise>
										<sf:button class="btn btn-primary" type="submit" name="operation"
											value="Previous">
											<span class="glyphicon glyphicon-circle-arrow-left"></span>
											<s:message code="label.previous"></s:message>
										</sf:button>
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
										<sf:button class="btn btn-primary" type="submit" name="operation"
											value="Next" disabled="true">
											<s:message code="label.next"></s:message>
											<span class="glyphicon glyphicon-circle-arrow-right"></span>
										</sf:button>
									</c:when>
									<c:otherwise>
										<sf:button class="btn btn-primary" type="submit" name="operation"
											value="Next">
											<s:message code="label.next"></s:message>
											<span class="glyphicon glyphicon-circle-arrow-right"></span>
										</sf:button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<c:if test="${sessionScope.user.roleId==1 }">
							<div class="col-md-1">
								<sf:button class="btn btn-primary" type="submit" name="operation"
									value="Delete">
									<s:message code="label.delete"></s:message>
									<span class="glyphicon glyphicon-trash"></span>
								</sf:button>
							</div>
						</c:if>
					</div>
				</div>

				<sf:hidden path="pageNo" />
				<sf:hidden path="pageSize" />
			</c:when>
			<c:otherwise>
				<br>
				<br>
				<table class="table table-bordered table-striped">
					<thead>
						<tr id="trcolor">
							<c:if test="${sessionScope.user.roleId==1 }">
								<th id="thcolor" align="left" width="8%"><input
									type="checkbox" id="selectall" onclick="checkAll(this)">Select
									All</th>
							</c:if>
							<th id="thcolor"><s:message code="label.sNo"></s:message></th>
							<th id="thcolor"><s:message code="label.firstName"></s:message></th>
							<th id="thcolor"><s:message code="label.lastName"></s:message></th>
							<th id="thcolor"><s:message code="label.college"></s:message></th>
							<th id="thcolor"><s:message code="label.dob"></s:message></th>
							<th id="thcolor"><s:message code="label.mobileNo"></s:message></th>
							<th id="thcolor"><s:message code="label.email"></s:message></th>
							<c:if test="${sessionScope.user.roleId==1 }">
								<th id="thcolor"><s:message code="label.edit"></s:message></th>
							</c:if>
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
</body>
</html>

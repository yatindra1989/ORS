<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>

<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">

<title>Get Marksheet</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<div class="container">
		<sf:form action="getMarksheet" method="post" commandName="form"
			cssClass="well">

			<h1 class="text-primary text-center">Get Marksheet</h1>
			<br>
			<c:if test="${not empty success}">
				<div class="alert alert-success text-center" role="alert">
					<span class="glyphicon glyphicon-saved"></span> ${success}
				</div>
			</c:if>

			<c:if test="${not empty error}">
				<div class="alert alert-danger text-center" role="alert">
					<span class="glyphicon glyphicon-alert"></span> ${error}
				</div>
			</c:if>

			<div class="form-group row text-center">
				<div class="col-md-2 col-md-offset-3">
					<sf:label path="rollNo">
						<s:message code="label.rollNo">
						</s:message>
						<font color="red">*</font>
					</sf:label>
				</div>
				<div class="col-md-3" style="padding-right: 95px;">
					<sf:input path="rollNo" class="form-control" />
					<div class="text-left">
						<label class="control-label text-danger" id="font5"><sf:errors
								path="rollNo"></sf:errors></label>
					</div>
				</div>
				<div class="col-md-1">
					<button type="submit" class="btn btn-primary " name="operation"
						value="GO">GO</button>
				</div>
			</div>

			<c:if test="${(error == null) && (not empty form.rollNo) }">
				<c:set var="total"
					value="${form.physics + form.chemistry + form.maths}"></c:set>

				<c:set var="percent" value="${total/3 }"></c:set>

				<c:choose>
					<c:when
						test="${form.physics <35 || form.chemistry <35 || form.maths < 35 }">
						<c:set var="result" value="fail"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="result" value="pass"></c:set>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${form.physics <35}">
						<c:set var="p" value="${form.physics}*"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="p" value="${form.physics}"></c:set>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${form.chemistry <35}">
						<c:set var="c" value="${form.chemistry}*"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="c" value="${form.chemistry}"></c:set>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${form.maths <35}">
						<c:set var="m" value="${form.maths}*"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="m" value="${form.maths}"></c:set>
					</c:otherwise>
				</c:choose>
				<c:if test="${percent >= 60 }">
					<c:set var="division" value="I"></c:set>
				</c:if>
				<c:if test="${percent<60 && percent >=50 }">
					<c:set var="division" value="II"></c:set>
				</c:if>
				<c:if test="${percent<50 && percent >=35 }">
					<c:set var="division" value="III"></c:set>
				</c:if>
				<c:if test="${percent < 35 || result=='fail'}">
					<c:set var="division" value="-"></c:set>
				</c:if>
				<br>
				<br>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<table class="table table-condensed table-bordered ">
							<tr>
								<th><s:message code="label.rollNo" /></th>
								<td><c:out value="${form.rollNo }" /></td>
							</tr>

							<tr>
								<th><s:message code="label.student" /></th>
								<td><c:out value="${form.getName() }" /></td>
							</tr>

							<tr>
								<td colspan="2" align="center"><b><s:message
											code="label.detailOfMarks" /></b></td>
							</tr>

							<tr>
								<th><B><s:message code="label.subject" /></B></th>
								<td><B><s:message code="label.marks" />/100</B></td>
							</tr>

							<tr>
								<th><s:message code="label.physics" /></th>
								<td><c:out value="${p}" /></td>
							</tr>

							<tr>
								<th><s:message code="label.chemistry" /></th>
								<td><c:out value="${c}" /></td>
							</tr>

							<tr>
								<th><s:message code="label.math" /></th>
								<td><c:out value="${m}" /></td>
							</tr>

							<tr>
								<th><s:message code="label.total" /></th>
								<td><c:out value="${total}/300" /></td>
							</tr>

							<tr>
								<%-- <c:choose>
		                  <c:when test="${result==pass}">
		                   class = " success"
		                  </c:when>
		                    class ="danger"
		                </c:choose> --%>

								<th><s:message code="label.result" /></th>
								<td><c:out value="${result}" /></td>
							</tr>
							<tr>
								<th><s:message code="label.division" /></th>
								<td><c:out value="${division}" /></td>
							</tr>
							<c:if test="${result==fail}">
								<tr>
									<td>*Fail in this Subject</td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 col-md-offset-3">
						<c:if test="${not empty form.rollNo}">
							<div class="text-center">
								<c:url value="/ctl/Marksheet/getMarksheetOnMail"
									var="rollNumber">
									<c:param name="rollNo" value="${form.rollNo}"></c:param>
								</c:url>
								<a href='<c:out value="${rollNumber }"></c:out>'
									class="btn btn-primary">Send via Email</a>
							</div>
						</c:if>
					</div>
					<div class="col-md-3">
						<c:if test="${not empty form.rollNo}">
							<div class="text-center">
								<c:url value="/ctl/Marksheet/getMarksheetAsPdf" var="rollNumber">
									<c:param name="rollNo" value="${form.rollNo}"></c:param>
								</c:url>
								<a href='<c:out value="${rollNumber }"></c:out>'
									class="btn btn-primary">Download</a>
							</div>
						</c:if>
					</div>
				</div>
			</c:if>
			<br>
		</sf:form>
	</div>
</body>
</html>

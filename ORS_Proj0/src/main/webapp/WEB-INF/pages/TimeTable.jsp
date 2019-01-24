<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>
<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Time Table</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<div class="container">
		<sf:form action="TimeTable" method="post" commandName="form"
			class="well">

			<div class="text-center">
				<c:choose>
					<c:when test="${form.id>0 }">
						<h1 class="text-primary">
							<s:message code="label.updateTimeTable"></s:message>
						</h1>
					</c:when>
					<c:otherwise>
						<h1 class="text-primary">
							<s:message code="label.addTimeTable"></s:message>
						</h1>
					</c:otherwise>
				</c:choose>

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

			<sf:hidden path="id" />
			<sf:hidden path="createdBy" />
			<sf:hidden path="modifiedBy" />
			<sf:hidden path="createdDatetime" />
			<sf:hidden path="modifiedDatetime" />


			<div class="container">
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.course"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-education"></i></span>
							<sf:select path="courseId" cssClass="form-control">
								<sf:option value="0">Select</sf:option>
								<sf:options items="${courseList }" itemValue="id"
									itemLabel="courseName"></sf:options>
							</sf:select>
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="courseId"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.subject"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-book"></i></span>
							<sf:input path="subject" cssClass="form-control"
								placeholder="Subject" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="subject"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.examDate"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
							<sf:input path="examDate" readonly="readonly" id="datepicker"
								cssClass="form-control" placeholder="mm/dd/yyyy" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="examDate"></sf:errors></label>
					</div>
				</div>



				<div class="col-md-7 col-md-offset-4" style="padding-left: 55px;">
					<sf:button type="submit" name="operation" value="Save"
						class="btn btn-primary col-md-2">
						<s:message code="label.save"></s:message>
					</sf:button>
					<sf:button type="submit" name="operation" value="Cancel"
						class="btn btn-danger col-md-2 col-md-offset-1">
						<s:message code="label.cancel"></s:message>
					</sf:button>
				</div>
			</div>
			<br>
			<br>
		</sf:form>
	</div>
</body>
</html>
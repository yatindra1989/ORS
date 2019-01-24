<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>
<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Course View</title>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<div class="container">
		<sf:form action="Course" method="post" commandName="form"
			cssClass="well">

			<div class="text-center">
				<c:choose>
					<c:when test="${form.id>0 }">
						<h1 class="text-primary">
							<s:message code="label.updateCourse"></s:message>
						</h1>
					</c:when>
					<c:otherwise>
						<h1 class="text-primary">
							<s:message code="label.addCourse"></s:message>
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
							code="label.courseName"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-education"></i></span>
							<sf:input path="courseName" cssClass="form-control"
								placeholder="Course Name" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="courseName"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.courseDescription"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-pencil"></i></span>
							<sf:textarea path="description" cssClass="form-control"
								placeholder="Course Description" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="description"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.duration"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-time"></i></span>
							<sf:select path="duration" cssClass="form-control">
								<sf:option value="">Select</sf:option>
								<sf:option value="1 Year">1 Year</sf:option>
								<sf:option value="2 Years">2 Years</sf:option>
								<sf:option value="3 Years">3 Years</sf:option>
								<sf:option value="4 Years">4 Years</sf:option>
								<sf:option value="5 Years">5 Years</sf:option>
							</sf:select>
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="duration"></sf:errors></label>
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
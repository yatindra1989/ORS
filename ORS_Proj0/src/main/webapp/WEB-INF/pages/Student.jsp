<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>

<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Add Student</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<div class="container">
		<sf:form action="Student" method="post" commandName="form"
			cssClass="well">

			<div class="text-center">
				<c:if test="${form.id>0 }">
					<h1 class="text-primary">
						<s:message code="label.updateStudent"></s:message>
					</h1>
				</c:if>

				<c:if test="${form.id==0 }">
					<h1 class="text-primary">
						<s:message code="label.addStudent"></s:message>
					</h1>
				</c:if>
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
							code="label.firstName"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<sf:input path="firstName" cssClass="form-control"
								placeholder="First Name" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="firstName"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.lastName"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<sf:input path="lastName" cssClass="form-control"
								placeholder="Last Name" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="lastName"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.dob"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
							<sf:input path="dob" cssClass="form-control" id="datepicker"
								placeholder="mm/dd/yyyy" readonly="readonly" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="dob"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.mobileNo"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-phone"></i></span>
							<sf:input path="mobileNo" cssClass="form-control" maxlength="10"
								placeholder="Mobile Number" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="mobileNo"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.email"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-envelope"></i></span>
							<sf:input path="emailId" cssClass="form-control"
								placholder="Email ID" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="emailId"></sf:errors></label>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.collegeName"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-education"></i></span>
							<sf:select path="collegeId" cssClass="form-control" size="0">
								<sf:option value="0">Select</sf:option>
								<sf:options items="${collegeList }" itemValue="id"
									itemLabel="name"></sf:options>
							</sf:select>
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="collegeId"></sf:errors></label>
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

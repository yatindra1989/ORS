<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>
<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Add Marksheet</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<div class="container">
		<sf:form action="Marksheet" method="post" commandName="form"
			cssClass="well">

			<div class="text-center">
				<c:if test="${form.id>0 }">
					<h1 class="text-primary">
						<s:message code="label.updateMarksheet"></s:message>
					</h1>
				</c:if>
				<c:if test="${form.id==0 }">
					<h1 class="text-primary">
						<s:message code="label.addMarksheet"></s:message>
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
							code="label.rollNo"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-education"></i></span>
							<sf:input path="rollNo" cssClass="form-control"
								placeholder="ex: 123xyz123" readonly="${form.id>0?'true':'' }"></sf:input>
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="rollNo"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.name"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<sf:select path="studentId" cssClass="form-control">
								<sf:option value="0">Select</sf:option>
								<sf:options items="${studentList }" itemValue="id"
									itemLabel="firstName"></sf:options>
							</sf:select>
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="studentId"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.physics"></s:message><font color="red">*</font> </label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list-alt"></i></span>
							<sf:input path="physics" cssClass="form-control" type="text"
								placeholder="Physics Marks" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="physics"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.chemistry"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list-alt"></i></span>
							<sf:input path="chemistry" cssClass="form-control" type="text"
								placeholder="Chemistry Marks" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="chemistry"></sf:errors></label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.math"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-list-alt"></i></span>
							<sf:input path="maths" cssClass="form-control" type="text"
								placeholder="Math Marks" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="maths"></sf:errors></label>
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

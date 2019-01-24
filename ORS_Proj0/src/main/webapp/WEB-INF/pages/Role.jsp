<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>
<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Add Role</title>
</head>
<body
	style="background-image: url('http://localhost:8080/ORS_Proj0/resources/img/contact-form-step1-background.jpg');">
	<div class="container">
		<sf:form action="Role" method="post" commandName="form"
			class="well form-horizontal">

			<div class="text-center">
				<c:if test="${form.id>0 }">
					<h1 class="text-primary">
						<s:message code="label.updateRole"></s:message>
					</h1>
				</c:if>

				<c:if test="${form.id==0 }">
					<h1 class="text-primary">
						<s:message code="label.addRole"></s:message>
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

				<div class="form-group">
					<label class="col-md-4 control-label"><s:message
							code="label.roleName"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
							<sf:input path="roleName" cssClass="form-control"
								placeholder="Role Name" />
						</div>
						<label class="control-label text-danger" id="font5"><sf:errors
								path="roleName"></sf:errors></label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"><s:message
							code="label.roleDescription"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-pencil"></i></span>
							<sf:textarea path="roleDescription" cssClass="form-control"
								placeholder="Role Description" />
						</div>
						<label id="font5" class="control-label text-danger"><sf:errors
								path="roleDescription"></sf:errors></label>

					</div>
				</div>
				<div class="col-md-7 col-md-offset-4" style="padding-left: 65px;">
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

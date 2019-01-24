<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>


<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Reset Password</title>
</head>
<body>
	<div class="container">

		<sf:form commandName="form" method="post" class="well">
			<div class="text-center">

				<h1 class="text-primary">
					<s:message code="label.resetPassword"></s:message>
				</h1>
				<br>
				<c:if test="${not empty success }">
					<div class="alert alert-success col-md-10 col-md-offset-1"
						role="alert">
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
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.newPassword"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<sf:input path="newPassword" cssClass="form-control"
								type="password" placeholder="New Password" />
						</div>
						<label class="control-label text-danger" id="font5"> <sf:errors
								path="newPassword"></sf:errors>
						</label>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-4 text-right control-label"><s:message
							code="label.confirmPassword"></s:message><font color="red">*</font></label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>
							<sf:input path="confirmPassword" cssClass="form-control"
								type="password" placeholder="Confirm Password" />
						</div>
						<label class="control-label text-danger" id="font5"> <sf:errors
								path="confirmPassword"></sf:errors>
						</label>
					</div>
				</div>
				<div class="col-md-7 col-md-offset-4" style="padding-left: 55px;">
					<button class="btn btn-primary col-md-2" type="submit"
						name="operation" value="Save">
						<s:message code="label.save"></s:message>
					</button>

				</div>

			</div>
		</sf:form>
	</div>
</body>
</html>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>


<html>
<head>
<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/resources/img/Title_Icon.png">
<title>Forget Password</title>

</head>
<body>
	<div class="container">

		<sf:form commandName="form" method="post" class="well">
			<div class="text-center">

				<h1 class="text-primary">
					<s:message code="label.forgetPassword"></s:message>
					<br> <br>
					<c:if test="${( empty success) && (empty otp)&&(empty otpError)}">
						<h4>
							<s:message code="label.sendPasswordMessage"></s:message>
						</h4>
					</c:if>
					<c:if
						test="${(not empty success) || (not empty otp) ||(not empty otpError)}">
						<h4>
							<s:message code="label.enterOTP"></s:message>
						</h4>
					</c:if>
				</h1>
				<br>

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

				<c:if test="${( empty success) && (empty otp) &&(empty otpError)}">
					<div class="form-group row">
						<label class="col-md-4 text-right control-label"><s:message
								code="label.login"></s:message><font color="red">*</font></label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<sf:input path="login" cssClass="form-control"
									placeholder="Login ID" />
							</div>
						</div>
						<div>
							<button class="btn btn-primary" type="submit" name="operation"
								value="Go">
								<s:message code="label.go"></s:message>
							</button>
						</div>
					</div>

					<div class="col-md-offset-4 text-left">
						<label id="font5" class="control-label text-danger">&nbsp;&nbsp;<sf:errors
								path="login"></sf:errors>
						</label>
					</div>
				</c:if>

				<c:if
					test="${(not empty success) || (not empty otp) ||(not empty otpError)}">
					<div class="form-group row">
						<label class="col-md-4 text-right control-label"><s:message
								code="label.otp"></s:message><font color="red">*</font></label>
						<div class="col-md-3 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span>
								<sf:input path="otp" cssClass="form-control" placeholder="OTP" />
							</div>
						</div>
						<!-- <br>
				   <br>
				   <br> -->
						<div>
							<button class="btn btn-primary" type="submit" name="operation"
								value="Submit">
								<s:message code="label.submit"></s:message>
							</button>
						</div>
					</div>
					<div class="col-md-offset-4 text-left">
						<label id="font5" class="control-label text-danger">&nbsp;&nbsp;
							${otpError } </label>
					</div>
				</c:if>

			</div>
		</sf:form>
	</div>
</body>
</html>

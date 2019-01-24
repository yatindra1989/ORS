<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@page isELIgnored="false"%>

<html>
<head>

<link rel="icon"
	href="http://localhost:8080/ORS_Proj0/img/Title_Icon.png">
<title>Login</title>

<style type='text/css'>
.login-div {
	width: 400px;
	height: auto;
	background: linear-gradient(#23486A, #23486A);
	padding: 40px 50px;
	border-radius: 5px;
	border: 1px solid #555;
	font-family: arial;
	box-shadow: 0 0 10px #333;
	position: absolute;
	left: 0;
	right: 0;
	margin: auto;
}

.login-div-header {
	margin: 5px 0px;
	font-size: 24px;
	text-align: center;
	color: #E6E6E6;
}

input[type=text], select {
	width: 100%;
	padding: 10px 10px;
	margin: 1px 0;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 15px;
}

input[type=submit] {
	width: 100%;
	background-color: #2d719D;
	color: white;
	padding: 14px 20px;
	margin: 10px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 18px;
}

input[type=submit]:hover {
	background-color: #2d7189;
}
</style>
</head>
</head>
<body>
	<sf:form method="post" commandName="form">
		<div class='login-div'>
			<p class='login-div-header'>
				<s:message code="label.loginForm"></s:message>
			</p>
			<br>
			<c:if test="${not empty message }">
				<div class="alert alert-danger" style="text-align: center;">
					<span class="glyphicon glyphicon-alert"></span> ${message }
				</div>
			</c:if>
			<c:if test="${!empty success }">
				<div class="alert alert-success" role="alert">
					<span class="glyphicon glyphicon-saved"></span> ${success }
				</div>
			</c:if>
			<c:if test="${!empty error }">
				<div class="alert alert-danger" role="alert">
					<span class="glyphicon glyphicon-alert"></span> &nbsp; ${error}
				</div>
			</c:if>
			<sf:input path="login" placeholder="Login ID" class="form-control" />
			<label class="control-label text-danger"> <sf:errors
					path="login"></sf:errors>
			</label> <br> <br>
			<sf:password path="password" placeholder="Password"
				class="form-control" />
			<label class="control-label text-danger"> <sf:errors
					path="password"></sf:errors>
			</label> <br> <br>
			<sf:button class="btn btn-primary  btn-block col-md-2" type="submit"
				name="operation" value="SignIn">
				<s:message code="label.signIn"></s:message>
			</sf:button>
			<br> <br> <br> <br>
			<div class="text-right">
				<a id="dcolor" href="<c:url value="forgetpassword"/>" class='pwd-fg'>
					<s:message code="label.forgetPassword"></s:message>
				</a>
			</div>
		</div>
	</sf:form>
</body>
</html>

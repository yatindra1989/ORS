<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://localhost:8080/ORS_Proj0/resources/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="http://localhost:8080/ORS_Proj0/resources/js/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://localhost:8080/ORS_Proj0/resources/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="http://localhost:8080/ORS_Proj0/resources/css/jquery-ui.css">

<script
	src="http://localhost:8080/ORS_Proj0/resources/js/jquery-1.12.4.js"></script>
<script src="http://localhost:8080/ORS_Proj0/resources/js/jquery-ui.js"></script>
<script src="http://localhost:8080/ORS_Proj0/resources/js/cal.js"></script>

<link rel="stylesheet"
	href="http://localhost:8080/ORS_Proj0/resources/css/navbar-static-top.css">


<!-- <link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
 -->

<style>
.error {
	color: red;
	font-weight: bold;
}

.success {
	color: green;
	font-weight: bold;
}

#font5 {
	font-size: small;
}

#thcolor {
	color: white;
}

#trcolor {
	background-color: #23486A;
}
</style>
</head>

<body>
	<table align="center" width="100%">
		<tr class="text-right">
			<td id="font5"><tiles:insertAttribute name="menu"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td colspan="2"><tiles:insertAttribute name="header"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td colspan="1" height="400px" width="80%" valign="top"><tiles:insertAttribute
					name="body"></tiles:insertAttribute></td>
		</tr>
		<tr>
			<td colspan="2" align="center" valign="bottom" height="30px"><tiles:insertAttribute
					name="footer"></tiles:insertAttribute></td>

		</tr>
	</table>
</body>
</html>
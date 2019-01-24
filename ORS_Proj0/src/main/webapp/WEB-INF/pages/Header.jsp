<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript">
	function checkAll(bx) {
		var form = bx.form;
		var isChecked = bx.checked;
		for (var i = 0; i < form.length; i++) {
			if (form[i].type == 'checkbox') {
				form[i].checked = isChecked;
			}
		}
	}
</script>

<style type="text/css">
.navbar {
	background: #23486A;
}

#dcolor {
	color: white;
}

#userImage {
	display: inline-block;
	border: 1px solid #ddd;
	border-radius: 4px;
	padding: 5px;
}

@media screen and (min-width: 768px) {
	.dropdown:hover .dropdown-menu, .btn-group:hover .dropdown-menu {
		display: block;
	}
	.dropdown-menu {
		margin-top: 0;
	}
	.dropdown-toggle {
		margin-bottom: 2px;
	}
	.navbar .dropdown-toggle, .nav-tabs .dropdown-toggle {
		margin-bottom: 0;
	}
}

.dropdown, .btn-group : hover {
	background-color: red;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(".dropdown, .btn-group").hover(function() {
			var dropdownMenu = $(this).children(".dropdown-menu");
			if (dropdownMenu.is(":visible")) {
				dropdownMenu.parent().toggleClass("open");
			}
		});
	});
</script>
</head>
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-static-top">
		<img align="left"
			src="http://localhost:8080/ORS_Proj0/resources/img/rayswhite.png"
			width="150" height="50">

		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/Welcome" />">&emsp;&emsp;<span
					class="glyphicon glyphicon-home"></span> <s:message
						code="label.home"></s:message>
				</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">

				<c:if test="${not empty sessionScope.user}">

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> Hi ! <c:out
									value="${sessionScope.user.firstName }"></c:out> (<c:out
									value="${sessionScope.role}"></c:out>) <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/ctl/User/myProfile" />"><span
										class="glyphicon glyphicon-user"></span> <s:message
											code="label.myProfile"></s:message></a></li>

								<li><a href="<c:url value="/ctl/User/changePassword" />"><span
										class="glyphicon glyphicon-cog"></span> <s:message
											code="label.changePassword"></s:message></a></li>

								<c:if test="${sessionScope.user.roleId == 1}">
									<li><a href="<c:url value="/resources/doc/index.html" />"
										target="_blank"><span class="glyphicon glyphicon-book"></span>
											<s:message code="label.javaDoc"></s:message></a></li>
								</c:if>

								<li><a href="<c:url value="/Login" />"><span
										class="glyphicon glyphicon-log-out"></span> <s:message
											code="label.logout"></s:message></a></li>

							</ul></li>
					</ul>
				</c:if>
				<c:if test="${empty sessionScope.user }">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/register" />"><span
								class="glyphicon glyphicon-user"></span> <s:message
									code="label.signUp"></s:message></a></li>
						<li><a href="<c:url value="/Login" />"><span
								class="glyphicon glyphicon-log-in"></span> <s:message
									code="label.signIn"></s:message></a></li>

					</ul>

				</c:if>


				<ul class="nav navbar-nav">
					<!--<li class="active"><a href="#">Home</a></li> -->
					<%-- <li><a href="<%=ORSView.WELCOME_CTL%>">Welcome</a></li> --%>
					<!-- <li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
					 -->


					<c:if test="${not empty sessionScope.user}">



						<!-- --------------------------------------
						Admin
						------------------------------------------- -->
						<c:if test="${sessionScope.user.roleId == 1}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.user"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/User" />"><span
											class="glyphicon glyphicon-user"></span> <s:message
												code="label.addUser"></s:message></a></li>
									<li><a href="<c:url value="/ctl/User/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.userList"></s:message></a></li>
									<!-- <li role="separator" class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li> -->
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.marksheet"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Marksheet" />"><span
											class="glyphicon glyphicon-education"></span> <s:message
												code="label.addMarksheet"></s:message></a></li>
									<li><a href="<c:url value="/ctl/Marksheet/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.marksheetList"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMarksheet" />"><span
											class="glyphicon glyphicon-search"></span> <s:message
												code="label.getMarksheet"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMeritList" />"><span
											class="glyphicon glyphicon-sort-by-attributes-alt"></span> <s:message
												code="label.meritList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.college"></s:message>
									<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/College" />"><span
											class="glyphicon glyphicon-education"></span> <s:message
												code="label.addCollege"></s:message></a></li>
									<li><a href="<c:url value="/ctl/College/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.collegeList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.student"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Student" />"><span
											class="glyphicon glyphicon-education"></span> <s:message
												code="label.addStudent"></s:message></a></li>
									<li><a href="<c:url value="/ctl/Student/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.studentList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.role"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Role" />"><span
											class="glyphicon glyphicon-plus-sign"></span> <s:message
												code="label.addRole"></s:message></a></li>
									<li><a href="<c:url value="/ctl/Role/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.roleList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.faculty"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Faculty" />"><span
											class="glyphicon glyphicon-education"></span> <s:message
												code="label.addFaculty"></s:message></a></li>
									<li><a href="<c:url value="/ctl/Faculty/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.facultyList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.course"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Course" />"><span
											class="glyphicon glyphicon-education"></span> <s:message
												code="label.addCourse"></s:message></a></li>
									<li><a href="<c:url value="/ctl/Course/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.courseList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><s:message code="label.timeTable"></s:message><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/TimeTable" />"><span
											class="glyphicon glyphicon-plus-sign"></span> <s:message
												code="label.addTimeTable"></s:message></a></li>
									<li><a href="<c:url value="/ctl/TimeTable/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.timeTableList"></s:message></a></li>
								</ul></li>
						</c:if>



						<!-- --------------------------------------
						Student
						------------------------------------------- -->
						<c:if test="${sessionScope.user.roleId == 2}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Marksheet <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Marksheet/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.marksheetList"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMarksheet" />"><span
											class="glyphicon glyphicon-search"></span> <s:message
												code="label.getMarksheet"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMeritList" />"><span
											class="glyphicon glyphicon-sort-by-attributes-alt"></span> <s:message
												code="label.meritList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">College <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/College/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.collegeList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Course<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Course/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.courseList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Time Table<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/TimeTable/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.timeTableList"></s:message></a></li>
								</ul></li>
						</c:if>



						<!-- --------------------------------------
						College
						------------------------------------------- -->
						<c:if test="${sessionScope.user.roleId == 3}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Marksheet <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Marksheet/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.marksheetList"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMarksheet" />"><span
											class="glyphicon glyphicon-search"></span> <s:message
												code="label.getMarksheet"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMeritList" />"><span
											class="glyphicon glyphicon-sort-by-attributes-alt"></span> <s:message
												code="label.meritList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">College <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/College/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.collegeList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Faculty<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Faculty/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.facultyList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Course<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Course/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.courseList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Time Table<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/TimeTable/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.timeTableList"></s:message></a></li>
								</ul></li>
						</c:if>




						<!-- --------------------------------------
						Kiosk
						------------------------------------------- -->
						<c:if test="${sessionScope.user.roleId == 4}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Marksheet <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Marksheet/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.marksheetList"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMarksheet" />"><span
											class="glyphicon glyphicon-search"></span> <s:message
												code="label.getMarksheet"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMeritList" />"><span
											class="glyphicon glyphicon-sort-by-attributes-alt"></span> <s:message
												code="label.meritList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">College <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/College/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.collegeList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Course<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Course/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.courseList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Time Table<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/TimeTable/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.timeTableList"></s:message></a></li>
								</ul></li>
						</c:if>


						<!-- --------------------------------------
						Faculty
						------------------------------------------- -->
						<c:if test="${sessionScope.user.roleId == 5}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Marksheet <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Marksheet/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.marksheetList"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMarksheet" />"><span
											class="glyphicon glyphicon-search"></span> <s:message
												code="label.getMarksheet"></s:message></a></li>
									<li><a
										href="<c:url value="/ctl/Marksheet/getMeritList" />"><span
											class="glyphicon glyphicon-sort-by-attributes-alt"></span> <s:message
												code="label.meritList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">College <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/College/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.collegeList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Faculty<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Faculty/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.facultyList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Course<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/Course/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.courseList"></s:message></a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Time Table<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/ctl/TimeTable/search" />"><span
											class="glyphicon glyphicon-list-alt"></span> <s:message
												code="label.timeTableList"></s:message></a></li>
								</ul></li>
						</c:if>

					</c:if>
				</ul>

			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<!-- container -->
	<!-- 	<footer class="footer">
		<div>
			<h4 style="text-align: center;">© Copyrights SUNRAYS
				Technologies</h4>
		</div>
	</footer>

 -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- 	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"><\/script>')
	</script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></script>
 -->
</body>
</html>

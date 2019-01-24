<html>
<head>
<style>
.my-footer {
	border-radius: 0px;
	margin: 0px; /* pesky margin below .navbar */
	position: absolute;
	width: 100%;
}
</style>
</head>

<body>
	<!-- 	<div class="container">
		<div class="row">
			Content of any length asdfasdfasdfasdfs <br /> asdfasdfasdfasdfs <br />
			asdfasdfasdfasdfs <br />
		</div>

	</div> -->

	<div class="navbar navbar-default my-footer">
		<div class="col-md-5 col-md-offset-4">
			<p class="navbar-text" id="hcolor">Copyright &copy;
				RaysTechnologies/All rights reserved.</p>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			var $docH = $(document).height();
			// The document height will grow as the content on the page grows.
			$('.my-footer').css({
				/*
				The default height of .navbar is 50px with a 1px border,
				change this 52 if you change the height of your footer.
				 */
				top : ($docH - 52) + 'px'
			});
		});
	</script>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Admin Page</title>
<!-- plugins:css -->
<link rel="stylesheet" href="Admin/template/vendors/feather/feather.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="../Admin/template/vendors/mdi/css/materialdesignicons.min.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="../Admin/template/vendors/ti-icons/css/themify-icons.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="../Admin/template/vendors/typicons/typicons.css" type="text/css"
	media="screen">
<link rel="stylesheet"
	href="../Admin/template/vendors/simple-line-icons/css/simple-line-icons.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="../Admin/template/vendors/css/vendor.bundle.base.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="../Admin/template/vendors/datatables.net-bs4/dataTables.bootstrap4.css"
	type="text/css" media="screen">
<link rel="stylesheet"
	href="../Admin/template/js/select.dataTables.min.css" type="text/css"
	media="screen">
<link rel="stylesheet"
	href="../Admin/template/css/vertical-layout-light/style.css"
	type="text/css" media="screen">
<link rel="stylesheet" href="../Admin/template/css/main.css"
	type="text/css" media="screen">
<link rel="shortcut icon" href="Admin/template/images/favicon.png" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div class="container-scroller">
		<nav
			class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex align-items-top flex-row">
			<%@include file="../component/logoInfo.jsp"%>
			<%@include file="../component/navbarMenu.jsp"%>
		</nav>
		<div class="container-fluid page-body-wrapper">
			<%@include file='../component/themeSetting.jsp'%>
			<%@include file="../component/leftbar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<h1 class="text-center">List User</h1>
					<table
						class="table table-striped table-bordered table-hover text-center"
						style="font-family:" Times NewRoman", Times, serif; font-weight:300">
						<tr class="table-primary">
							<th></th>
							<th>Id</th>
							<th>Username</th>
							<th>Email</th>
							<th>Fullname</th>
							<th>Is Admin</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<php:forEach items="${users}" var="user">
							<tr>
								<th> <img style="width:30px; height:30px" class="img-md rounded-circle" alt="" src="${user.getAvatar() }"></th>
								<th>${user.getId() }</th>
								<th>${user.getUsername() }</th>
								<th>${user.getEmail() }</th>
								<th>${user.getFullname() }</th>
								<th><input type="checkbox"
									<php:if test="${user.getRole() == 1 }">checked</php:if>>
								</th>
								<th><a href="editUser?id=${user.getId() }" class="btn btn-warning">Edit</a></th>
								<th><a href="deleteUser?id=${user.getId() }" class="btn btn-danger">Delete</a></th>
							</tr>

						</php:forEach>
					</table>
					<nav aria-label="Page navigation example"
						class="d-flex justify-content-center">
						<ul class="pagination">
							<li class="page-item"><a style="pointer-events: none"
								class="page-link" href="user?page">Previous</a></li>
							<php:forEach var="page" begin="1" end="${numberOfPage }">
								<li class="page-item"><a class="page-link"
									href="user?page=${page}">${page }</a></li>
							</php:forEach>
							<li class="page-item"><a style="pointer-events: none"
								class="page-link" href="user?page">Next</a></li>
						</ul>
					</nav>
				</div>
				<%@include file="../component/footer.jsp"%>
			</div>
		</div>
	</div>
	<script src="../Admin/template/vendors/js/vendor.bundle.base.js"></script>
	<script src="../Admin/template/vendors/chart.js/Chart.min.js"></script>
	<script
		src="../Admin/template/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script
		src="../Admin/template/vendors/progressbar.js/progressbar.min.js"></script>

	<script src="../Admin/template/js/off-canvas.js"></script>
	<script src="../Admin/template/js/hoverable-collapse.js"></script>
	<script src="../Admin/template/js/template.js"></script>
	<script src="../Admin/template/js/settings.js"></script>
	<script src="../Admin/template/js/todolist.js"></script>
	<script src="../Admin/template/js/dashboard.js"></script>
	<script src="../Admin/template/js/Chart.roundedBarCharts.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>



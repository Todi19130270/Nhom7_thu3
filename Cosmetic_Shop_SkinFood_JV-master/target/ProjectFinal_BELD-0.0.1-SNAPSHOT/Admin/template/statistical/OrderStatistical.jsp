<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<div class="content-wrapper"
					style="padding: 20px; margin: 10px; width: 100%">
					<h1 class="text-center">Order Management</h1>
					<table
						class="table table-hover table-bordered table-striped text-center">
						<tr>
							<th>ID Order</th>
							<th>Username</th>
							<th>Phone</th>
							<th>Fullname</th>
							<th>Total</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
						<php:forEach items="${orderTotals}" var="orderTotal">
							<tr>
								<td>${orderTotal.getId() }</td>
								<td>${orderTotal.getUserModel().getId() }</td>
								<td>${orderTotal.getPhone() }</td>
								<td>${orderTotal.getOrderName() }</td>
								<td>${orderTotal.getFormatTotalAfterDiscount() } đ</td>
								<php:if test="${orderTotal.getStatus() == 0 }">
									<td>Đang chờ xác nhận</td>
									<td>
										<a href="confirm?id=${orderTotal.getId() }" class="btn btn-success">Xác nhận</a>
									</td>
								</php:if>
								<php:if test="${orderTotal.getStatus() == 1 }">
									<td>Đang vận chuyển</td>
									<td>
										<a href="dagiao?id=${orderTotal.getId() }" class="btn btn-danger">Xác nhận đã tới</a>
									</td>
								</php:if>
								<php:if test="${orderTotal.getStatus() == 2 }">
									<td>Đang chờ người nhận xác nhận</td>
									<td>
										<a href="" class="btn btn-secondary">Waiting...</a>
									</td>
								</php:if>
								<php:if test="${orderTotal.getStatus() == -1 }">
									<td>Đã giao</td>
									<td>
										<a href="#" class="btn btn-primary">Đã giao cho người nhận</a>
									</td>
								</php:if>
							</tr>
						</php:forEach>

					</table>
				</div>
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



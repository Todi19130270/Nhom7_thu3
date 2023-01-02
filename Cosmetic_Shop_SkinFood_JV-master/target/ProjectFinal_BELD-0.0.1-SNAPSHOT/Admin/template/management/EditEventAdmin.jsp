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
					<a href="event" class="btn btn-primary">Return to event page</a>
					<h1 class="text-center" style="padding-top: -50px">List Event</h1>
					<div
						class="d-flex text-center justify-content-center align-items-center">
						<img alt="" src="${event.getIcon() }" class="col-md-4"
							style="width: 60px">
						<h3 class="" style="color: red; margin: 20px;">${event.getName() }</h3>
					</div>
					<form action="eventEdit" method="post">
						<input type="hidden" name="eventId" value="${event.getId() }">
						<table class="table table-hover table-striped table-bordered text-center">
							<tr>
								<td>Title</td>
								<td>Discount Amount</td>
								<td>Delete</td>
							</tr>
							<php:forEach items="${EPs }" var="EP">
								<php:forEach items="${products }" var="product">
									<php:if test="${EP.getProduct() == product.getId() }">
										<tr>
											<th>${product.getTitle() }</th>
											<th>${product.getDiscount() }</th>

											<th><a class="btn btn-danger"
												href="eventDelete?event=${event.getId() }&product=${product.getId()}">Delete</a></th>
										</tr>
									</php:if>
								</php:forEach>

							</php:forEach>

						</table>
						<div class="form-group">
							<label for="">Choose product to add to event</label> <select
								name="productId" class="form-control text-center" 
								style="background-color: white; padding: 0px 10px; color: red;">


								
								
								
								<php:forEach items="${productNotInEvents }" var="product">
										<option value="${product.getId() }">${product.getId() } -->
											${product.getTitle() }</option>
								</php:forEach>




							</select>
						</div>
						<input type="submit" class="btn btn-primary" style="width: 100%" value="Add to event">
					</form>

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



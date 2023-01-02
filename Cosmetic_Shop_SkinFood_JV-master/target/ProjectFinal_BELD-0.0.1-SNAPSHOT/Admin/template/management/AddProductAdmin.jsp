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
					<a href="product" class="btn btn-primary" >Go to list product</a>
					<h1 class="text-center" style="padding-top: -50px">Add product</h1>
					<form action="addProduct" method="post" 
						style="width: 50%; margin: auto">
						<div class="form-group">
							<label for="" style="font-size: 18px">Enter title</label> <input
								name="title" type="text" class="form-control">
						</div>
						<div class="form-group">
							<label for="" style="font-size: 18px">Enter description</label>
							<textarea rows="9" cols="70" name="description" class="form-control" style="height: 70px"></textarea>
						</div>
						<div class="form-group">
							<label for="" style="font-size: 18px">Enter rating</label> <input
								type="text" class="form-control" value="5" disabled>
						</div>
						<div class="form-group">
							<label for="" style="font-size: 18px">Sold</label> <input
								disabled type="text" class="form-control" value="0">
						</div>
						<div class="form-group">
							<label for="" style="font-size: 18px">Enter Price Value</label> <input
								name="price" type="text" class="form-control">
						</div>
						<div class="form-group">
							<label for="" style="font-size: 18px">Enter Discount Value</label> <input
								name="discount" type="text" class="form-control">
						</div>
						<div class="form-group">
							<label for="" style="font-size: 18px">Select category</label> <select
								name="category" id="select_projects" class="form-control"
								style="padding: 3px; background-color: white; color: red">
								<php:forEach items="${collections }" var="collection">
									<optgroup label="${collection.getName() }">
										<php:forEach items="${categories }" var="category">
											<php:if
												test="${category.getCollection() == collection.getId() }">
												<option class="option-choosen form-control"
													value=${category.getId() }>${category.getName() }</option>
											</php:if>
										</php:forEach>
									</optgroup>
								</php:forEach>
							</select>
						</div>
						<div class="mb-4">
							<label for="" style="font-size: 18px">Select Brand</label> <select
								class="form-control" style="background-color: white; color: red; padding: 4px"
								id="" name="brand">
								<php:forEach items="${brands }" var="brand">
									<option value=${brand.getId() }>${brand.getName() }</option>
								</php:forEach>
							</select>
						</div>
						<php:if test="${mess.length() > 0 }">
							<p style="font-size: 24px !important;font-weight: 700; color: red" >${mess }</p>
						</php:if>
						<div class="justify-content-center">
							<button type="submit" class="btn btn-primary margin-center"
								style="width: 100%">Submit</button>
						</div>
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



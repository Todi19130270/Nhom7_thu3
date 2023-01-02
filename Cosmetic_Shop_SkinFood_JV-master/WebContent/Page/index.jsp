<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://unpkg.com/flickity@2/dist/flickity.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
	integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="Page/css/web.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="Page/css/cartMainCss.css" type="text/css"
	media="screen">
<link rel="stylesheet"
	href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<title>Dangu Shop</title>
<style type="text/css">
.flickity-slider {
	
}
</style>
</head>
<body>
	<%@include file="web/header.jsp"%>
	<%@include file="component/header/headerCarousel.jsp"%>
	<!-- PRODUCT -->
	<div class="product">
		<div class="container">
			<%@include file="component/main/flashDeal.jsp"%>
			<%@include file="component/main/allEvent.jsp"%>
			<%@include file="component/main/deal1k.jsp"%>
			<div class="d-flex justify-content-around mt-5 mb-5">
				<php:forEach items="${categories }" var="cate" begin="1" end="10">
					<a class="btn" style="background-color: #990000; color: white"
						href="category?id=${cate.getId() } ">${cate.getName() }</a>
				</php:forEach>
			</div>
			<%@include file="component/main/saleProduct.jsp"%>
			<div class="text-center d-flex justify-content-center" id="seemorebtn">
				<button class="btn btn-dark">Xem thêm</button>
			</div>
			<%@include file="component/main/banner1.jsp"%>
			<%@include file="component/main/normMakeup.jsp"%>
			<%@include file="component/main/banner2.jsp"%>
			<%@include file="component/main/normSkincare.jsp"%>
		</div>
	</div>
	<!-- FOOTER -->
	<%@include file="web/footer.jsp"%>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>

<script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.min.js"></script>
<script src="Page/js/web.js"></script>
<script type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			console.log("alo");
		    function seemore() {
		        var start = document.getElementsByClassName("product_count_start").length; 
		        console.log(start);
		        $.ajax({
					url : "seemore",
					type : "post",
					data : {
						start : start
					},
					success : function(response) {
						console.log("1");
						var seemore_space = document.getElementById("seemore_space");
						seemore_space.innerHTML += response;
					},
					error : function(xhr) {
						console.log(textStatus, errorThrown);
						console.log("alo2")
					}
				});
		     
		    }
		    $("#seemorebtn").click(seemore);
		});
</script>
</html>
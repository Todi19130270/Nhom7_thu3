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

<title>Document</title>
</head>
<body>
	<!-- Header -->
	<%@include file="web/header.jsp"%>

	<div class="view-pro">
		<div class="container">
			<div class="crumb">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
						<li class="breadcrumb-item active" aria-current="page">${productDetail.getTitle() }</li>
					</ol>
				</nav>
			</div>

			<div class="row">
				<div class="col-lg-5 justify-content-center pl-5 ml-5">
					<div class="view-img justify-content-center pl-5 ml-5" >
						<div class="view-img__pro-main justify-content-center">
							<img src="${productDetail.getFirstImage() }" alt=""
								class="img-main ml-5">
							<div class="img-cover"></div>
						</div>
						<div class="view-img__list">
							<php:forEach items="${listImages }" var="list">
								<div class="view-img__list-item act">
									<a href="#" class="view-img__link" onclick="return false">
										<img id="img-desc1" src="${list.getImage() }" alt="">
									</a>
								</div>
							</php:forEach>
						</div>

					</div>
				</div>
				<div class="col-lg-5">
					<div class="view-info">
						<h4 class="view-info__title">
							${productDetail.getTitle() }
							</h2>
							<div class="view-rate">
								<div class="rating-stars">
									<i class="star--gold fas fa-star"></i> <i
										class="star--gold fas fa-star"></i> <i
										class="star--gold fas fa-star"></i> <i
										class="star--gold fas fa-star"></i> <i
										class="star--gold  fas fa-star"></i>
								</div>
								<div class="text-ratings">
									<p>
										<span>${productDetail.getRating() }</span> sao - <span>62</span>
										Đánh giá
									</p>
								</div>
							</div>
							<div class="view-price">
								<div class="view-price__sale">
									<span class="curr-price">${productDetail.getFormatPriceStandard() }đ</span>
									<span class="old-price">${productDetail.getFormatPriceDefault() }đ</span>
									<span class="percent-sale">-
										${productDetail.getDiscount() }%</span>
								</div>
								<div class="view-price__note">
									<p>
										Giá thị trường:
										${productDetail.getFormatPrice(productDetail.getPriceDefault()*1.1 )}
										- Tiết
										kiệm:${productDetail.getFormatPrice(productDetail.getPriceDefault() * 0.1 ) }
										(<span>- 10%</span>)
									</p>
								</div>
							</div>
							<div class="view-info__note">
								<p class="view-info__text">${productDetail.getDescription()	 }</p>
							</div>
					</div>
					<div class="option-view">
						<!--  
						<div class="choose-color">
							<span>Chá»n mÃ u : </span>
							<div class="option-color">
								<button class="option-color__pro option-color-selected">MÃ u
									há»ng</button>
								<button class="option-color__pro">MÃ u xÃ¡m</button>
							</div>
						</div>
						-->
						<form action="addProductToCart" class="text-cener" method="post">
							<input type="hidden" name="productId"
								value="${productDetail.getId() }">
							<div class="choose-quantity">
								<span>Số lượng : </span>
								<div class="option-quantity">
									<div id="" class="btn btn-primary" onclick="sub(${productDetail.getId() },0)">-</div>
									<input type="text" class="input_number text-center"
										id="${productDetail.getId() }" name="quantity" min="1"
										max="20" value="1">
									<div id="" class="btn btn-primary" onclick="add(${productDetail.getId() },0)">+</div>
								</div>
							</div>
							<button class="option-add__cart box shadow-lg btn btn-danger mt-4" style="height: 40px">
								Thêm vào giỏ hàng</span>
							</button>
						</form>

					</div>
				</div>
				<div class="col-lg-2"></div>
			</div>
		</div>
		<div>
			
		</div>
	</div>
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
<script src="Page/js/web.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</html>
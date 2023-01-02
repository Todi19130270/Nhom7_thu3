<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="header-second">
	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group header-search">
					<input type="text" class="form-control" id="search-product"
						placeholder="Tìm kiếm..."> <select class="form-select"
						aria-label="Default select example">
						<option selected>Sản phẩm</option>
						<option value="1">Tin tức</option>
					</select>
					<button class="btn btn-outline-secondary" type="button">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="header-logo">
					<a href="home"> <img width="320px" height="80px"
						src="Page/img/logo.png" alt="">
					</a>
				</div>
			</div>
			<div class="col-lg-4">
				<div class="header-cart">
					<ul class="heaeder-cart__list">
						<li class="header-cart__item"><a href="#"
							class="header-cart__liked" title="Sản phẩm yêu thích"> <i
								class="far fa-heart"></i> Yêu thích <span class="number-liked">
									....</span>
						</a></li>
						<li class="header-cart__item cart-header-right"
							style="position: relative"><a href="cart"
							class="header-cart__added text-danger" title="Sản phẩm giỏ hàng"> <i
								class="fas fa-shopping-cart"></i> Giỏ hàng <span
								class="number-added">${sessionScope.cart != null ? sessionScope.cart.getCartItems().size() : 0} </span>
						</a>
							<%@include file="headerCartDiv.jsp"%></li>



					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
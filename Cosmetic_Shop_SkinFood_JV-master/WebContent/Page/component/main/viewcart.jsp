<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="cart">
	<div class="container">
		<div class="crumb">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item btn btn-warning "><a class="text-danger"
						href="home">Trang chủ</a></li>
					<li class="breadcrumb-item active" aria-current="page">Giỏ
						hàng</li>
				</ol>
			</nav>
		</div>

		<div class="cart-menu">
			<div class="row .cart-plus__item text-center">
				<div class="col-lg-4">
					<div class="menu-product">Sản phẩm</div>
				</div>
				<div class="col-lg-2">
					<div class="menu-product__quan">Category</div>
				</div>
				<div class="col-lg-2">
					<div class="menu-product__price">Đơn giá</div>
				</div>
				<div class="col-lg-2">
					<div class="menu-product__quan">Số lượng</div>
				</div>
				<div class="col-lg-1">
					<div class="menu-product__allprice">Tổng tiền </div>
				</div>
				<div class="col-lg-1">
					<div class="menu-product_remove">Thao tác</div>
				</div>
			</div>
		</div>

		<php:set var="cartItems" value="${sessionScope.cart.getCartItems()}" />
		<php:forEach items="${cartItems }" var="cartItem">
			<div class="cart-plus">
				<div class="row cart-plus__item">
					<div class="col-lg-4">
						<div class="cart-desc">
							<div class="form-check">
								<input class="form-check-cart" type="checkbox" checked disabled
									id="flexCheckDefault"
									style="width: 18px; height: 18px; color: #4ca214;">
							</div>
							<a href="product_detail?id=${cartItem.getProduct().getId() }" class="cart-info">
								<div class="cart-info__img">
									<img src="${cartItem.getProduct().getFirstImage() }" alt="">
								</div>
								<div class="cart-info__text d-flex">
									<p class="product-sale__item-name align-items-center"
										style="line-height: 80px">${cartItem.getProduct().getTitle() }</p>
								</div>
							</a>
						</div>
					</div>
					<div class="cart-info__chosen col-lg-2 text-center">${cartItem.getProduct().getCategoryName() }</div>
					<div class="col-lg-2">
						<div class="cart-price__item">
							<span class="product-sale__item-price-curr">${cartItem.getProduct().getFormatPriceStandard() }đ</span>
							<span class="product-sale__item-price-old">${cartItem.getProduct().getFormatPriceDefault() }đ</span>
						</div>
					</div>
					<div class="col-lg-2 text-center justify-content-center">
						<form action=""  id="form${cartItem.getProduct().getId() }">
							<div class="option-quantity justify-content-center">
								<button type="button" class="btn btn-success rounded-0"
									onclick="sub ( ${cartItem.getProduct().getId()} , ${cartItem.getProduct().getPrice() }); subAjax(${cartItem.getProduct().getId()}, ${ cartItem.getNumber()})">-</button>
								<input type="text"
									class="input_number btn btn-danger col-lg-3 rounded-0"
									id="${cartItem.getProduct().getId() }" name="quantity" min="1"
									max="20" value="${cartItem.getNumber() }">
								<button type="button" class="btn btn-success rounded-0"
									onclick="add(${cartItem.getProduct().getId()} , ${cartItem.getProduct().getPrice() }); addAjax(${cartItem.getProduct().getId()}, ${ cartItem.getNumber()})">+</button>
							</div>
						</form>
					</div>
					<div class="col-lg-1">
						<div class="cart-info__total-price text-center">
							<input class="col-lg-12 cart_main_element_title_total" style="border: none"
								id="totalPrice${cartItem.getProduct().getId() }"
								value="${cartItem.getTotalSingleFormat() }"  >
						</div>
					</div>
					<div class="col-lg-1">
						<div class="cart-info__delete">
							<button class="btn btn-danger">Xóa</button>
						</div>
					</div>
				</div>
			</div>
		</php:forEach>

		<div class="cart_main_element row">
			<!--startToConfirmOrder-->
			<form action="preview" method="POST"
				class="form_continue_to_order row justify-content-end align-items-center">
				<h4 class="col-md-2 text-right">Tổng số tiền:</h4>
				<php:forEach items="${cartItems }" var="cartItem">
					<input type="hidden" id="s${cartItem.getProduct().getId() }"
						name="${cartItem.getProduct().getId() }"
						value="${cartItem.getNumber() }" class="form_to_order_element">
				</php:forEach>
				<input type="hidden" id="old_total" name="oldtotal" class="total_price_hidden"
					value="${sessionScope.cart.getTotal()}" /> 
				<input type="hidden" id="total_price_hidden" name="total_price"
					class="total_price_hidden" value="${sessionScope.cart.getTotal()}" />
				<input type="text" class="col-md-2 btn btn-danger mr-4"
					value="${sessionScope.cart.getFormatTotal()}" id="total_price">
				<input type="submit" class="col-md-2 btn btn-success" value="Đặt hàng">
			</form>
		</div>
	</div>
</div>

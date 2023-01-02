<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<php:set var="cartItems" value="${sessionScope.cart.getCartItems()}" />
<div class="cart-items-header" style="right: -30%">
	<php:if test="${cartItems != null }">
		<div class="wrapper-cart">
			<h4 class="wrapper-cart-title">Sản phẩm trong giỏ hàng</h4>
			<php:forEach items="${cartItems }" var="cartItem">
				<a href="?viewproduct=2" class="cart-element"> <img
					src="${ cartItem.getProduct().getFirstImage()}" alt=""
					class="cart-element-image">
					<p class="cart-element-title">${ cartItem.getProduct().getTitle()}</p>
					<p class="cart-element-price">
						${cartItem.getProduct().getFormatPriceStandard()}đ <span class="number_order">x${ cartItem.getNumber()}</span>
					</p>
				</a>
			</php:forEach>
			<div class="wrapper_cart_link">
				<a style="poiter: cursor" href="cart" class="cart_link_element">
					Đến Giỏ Hàng
				</a>
			</div>
		</div>
	</php:if>

	<php:if test="${cartItems.size() == 0 || cartItems == null }">
		<div class="cart-items-empty">
			<img
				src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/cart/9bdd8040b334d31946f49e36beaf32db.png"
				alt="#">
			<p>Nothing here</p>
		</div>
	</php:if>
</div>
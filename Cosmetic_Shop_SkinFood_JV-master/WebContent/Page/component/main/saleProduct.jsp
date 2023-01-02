<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="product-sale">
	<div class="product-sale__header">
		<div class="product-sale__title">
			<h4>SẢN PHẨM KHUYẾN MÃI</h4>
		</div>
		<div class="product-sale__countdown">
			<img src="Page/img/icon-deals-of.png" alt=""> KẾT THÚC TRONG
			<div class="countdown">
				<div id="countdown-days"></div>
				<div id="countdown-hours"></div>
				<div id="countdown-minutes"></div>
				<div id="countdown-seconds"></div>
			</div>
		</div>
		<div class="product-sale__direct">
			<button type="button" class="btn btn-outline-success">
				<i class="fas fa-chevron-left"></i>
			</button>
			<button type="button" class="btn btn-outline-success">
				<i class="fas fa-chevron-right"></i>
			</button>
		</div>
	</div>
	<!--
						<div class="product-sale__item-img"
							style="background-image: url(${product.getFirstImage()});">
						</div>
						-->
	<div class="product-sale__body">
		<div class="row" id="seemore_space">
			<php:forEach items="${saleProducts}" var="product">
				<div class="col-lg-2 mt-5 product_count_start">
					<a href="product_detail?id=${product.getId() }"
						class="product-sale__item">
						<div class="product-sale__item-img"
							style="padding-top: 4px; height: 220px">
							<img alt="" src="${product.getFirstImage() }" style="width: 100%" />
						</div>
						<p class="product-sale__item-name">${product.getTitle() }</p>
						<div class="product-sale__item-price">
							<span class="product-sale__item-price-curr">${product.getFormatPriceStandard() }đ</span>
							<span class="product-sale__item-price-old">${product.getFormatPriceDefault() }đ</span>
						</div>
					</a>
				</div>
			</php:forEach>
		</div>
	</div>
</div>
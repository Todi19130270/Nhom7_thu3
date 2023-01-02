<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>

<div class="product-norm">
	<div class="product-norm__header">
		<div class="product-norm__tilte">
			<h4>SẢN PHẨM TRANG ĐIỂM</h4>
		</div>
	</div>
	<ul class="product-norm__nav">

		<php:forEach items="${saleMakeups }" var="step" varStatus="i">
			<php:if test="${i.count == 1 }">
				<li class="product-norm__active col-md-2">
					<p>TRANG ĐIỂM</p>
				</li>
			</php:if>
			<php:if test="${i.count != 1 }">
				<li class="col-md-2">
					<p>${step.getName() }</p>
				</li>
			</php:if>

		</php:forEach>
	</ul>
	<div class="product-norm__wrap">
		<div class="carousel"
			data-flickity='{ "freeScroll": true, "wrapAround": true, "pageDots": false, "autoPlay": true }'>
			<php:forEach items="${MakeupProducts }" var="makeup">
				<div class="col">
					<div class="product-norm__items shadow-lg p-3 mb-5 bg-white rounded" style="height: 410px">
						<div class="product-norm__img">
							<a href="product_detail?id=${makeup.getId() }"> <img
								src="${makeup.getFirstImage() }"
								alt="">
							</a>
						</div>
						<div class="product-norm__detail">
							<h6>
								<a href="product_detail?id=${makeup.getId() }" class="product-norm__link">[NEW] ${makeup.getTitle() }</a>
							</h6>
							<p class="product-norm__brand">
								<a href="brand=${makeup.getBrand() }" class="product-norm__link--brand">${makeup.getBrandName() }</a>
							</p>
							<div class="product-norm__price">
								<span class="product-norm__price--curr"> ${makeup.getFormatPriceStandard() } đ</span>
								<div class="product-norm__quantity">
									<p>${makeup.getFormatPriceDefault() } ₫</p>
									<span class="product-norm__quantity--selled">Đã bán
										${makeup.getSold() }</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</php:forEach>
		</div>
	</div>
</div>
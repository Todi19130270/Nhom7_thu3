<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="product">
	<div class="container">
		<div class="product-sale">
			<div class="product-sale__header">
				<div class="product-sale__title">
					<img
						src="https://app2.jeoway.net/61/hotdeal/images/icon_flashdeal_2.png"
						alt="">
				</div>
				<div class="product-sale__countdown">

					<div class="countdown">
						<div id="counth"></div>
						<span class="haicham">:</span>
						<div id="countm"></div>
						<span class="haicham">:</span>
						<div id="counts"></div>
					</div>
				</div>
				<div class="product-sale__direct">
					<a href="event?id=8" target="__blank" class="text-danger">XEM
						TẤT CẢ DEAL =></a>
				</div>
			</div>

			<div class="product-sale__body">
				<div class="carousel"
					data-flickity='{ "freeScroll": true, "wrapAround": true, "pageDots": false, "autoPlay": true }'>
					<php:forEach items="${flashdeal1s }" var="flashdeal1">
						<div class="col-lg-2 carousel-cell">
							<a href="product_detail?id=${flashdeal1.getId() }"
								class="product-sale__item">
								<div class="product-sale__item-img" style="padding-top: 4px">
									<img alt="" src="${flashdeal1.getFirstImage()}" style="width: 100%; height: 180px">
								</div>
								<p class="product-sale__item-name">${flashdeal1.getTitle() }</p>
								<div class="product-sale__item-price">
									<span class="product-sale__item-price-curr">${flashdeal1.getFormatPriceStandard() }đ</span>
									<span class="product-sale__item-price-old">${flashdeal1.getFormatPriceDefault() }</span>
								</div>
								<div class="home-product-item__sale-off">
									<span class="home-product-item__percent">-${flashdeal1.getDiscount() }%</span>
								</div>
								<div class="khungChungPT">
									<span style="width: 80%;" class="mauChay"></span>
									<div class="titleDangDienRa">ĐANG DIỄN RA</div>
								</div>
							</a>
						</div>
					</php:forEach>
				</div>

			</div>
		</div>
	</div>
</div>
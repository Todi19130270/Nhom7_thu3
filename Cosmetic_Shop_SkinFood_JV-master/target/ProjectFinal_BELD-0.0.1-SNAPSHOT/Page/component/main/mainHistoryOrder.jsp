<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="cart_main">
	<div class="cart_main_header">
		<div class="cart_main_header-left">
			<a href="#"> <img src="Page/img/logo.png"
				style="width: 320px; height: 80px" alt=" alo">
			</a>
			<h3 class="cart_main_header_left_title text-success">Lịch Sử Mua
				Hàng</h3>
		</div>
	</div>
	
	<php:if test="${historyTransactions.isEmpty() == true }">
		<h3 class="text-danger text-center mt-4">Empty</h3>
	</php:if>
	
	<div class="cart_main_wrapper">
		<php:forEach items="${historyTransactions }" var="his">
			<div class="cart_main_wrapper_list_order">
				<h3 class="cart_list_order_title">Mã đơn hàng: ${his.getId() }</h3>
				<h4 class="cart_list_order_title">Ngày đặt hàng: ${his.getTimeOrderString() }</h4>
				<div
					class="cart_list_order_element_ne row text-center d-flex align-items-center">
					<p class="cart_list_order_element_1 col-md-2">Ảnh</p>
					<p class="cart_list_order_element_title col-md-4">Tên sản phẩm</p>
					<p class="cart_list_order_element_single_price col-md-2">Giá</p>
					<p class="cart_list_order_element_number col-md-2">Số lượng</p>
					<p class="cart_list_order_element_total_price col-md-2">Tính</p>
				</div>
				<php:forEach items="${historySingleTransactions}" var="hisSingle">
					<php:if
						test="${hisSingle.getOrderTotal().equals(his.getId()) == true }">
						<div
							class="cart_list_order_element row text-center align-items-center">
							<div class="col-md-2 text-center">
								<img style="width: 50%"
									src="${hisSingle.getProductById().getFirstImage() }" alt="">
							</div>
							<p class="cart_list_order_element_title col-md-4">${hisSingle.getProductById().getTitle() }</p>
							<p class="cart_list_order_element_single_price col-md-2">
								${hisSingle.getProductById().getFormatPriceStandard() }đ</p>
							<p class="cart_list_order_element_number col-md-2">${hisSingle.getNumber() }</p>
							<p class="cart_list_order_element_total_price col-md-2">${hisSingle.getFormatPrice()}
								đ</p>
						</div>
					</php:if>
				</php:forEach>
				<div class="d-flex text-center mt-4">
					<div class="col-md-2"></div>
					<p class="col-md-4 text-right text-danger">Phone number:
						${his.getPhone() }</p>
					<div class="col-md-2">Vận chuyển:</div>
					<div class="col-md-2">${his.getTransportName() }</div>
					<div class="col-md-2 btn btn-primary">${his.getTransportFee() }đ</div>
				</div>

				<php:if test="${his.getDiscount() != 0 }">
					<div class="d-flex text-center">
						<div class="col-md-6"></div>
						<div class="col-md-2"></div>
						<div class="col-md-2">Tổng:</div>
						<div class="col-md-2 btn btn-danger">
							<del>${his.getFormatTotalAfterDiscount() }đ</del>
						</div>
					</div>
				</php:if>
				<div
					class="cart_list_order_status row justify-content-end text-center ">
					<form action="recieveOrder" method="POST"
						class="cart_list_order_status_paying" style="width: 15%">
						<php:if test="${his.getStatus() == 0 }">
							<input disabled
								class="btn btn-warning rounded-0 border border-danger text-danger"
								type="submit" value="Chờ xác nhận">
						</php:if>
						<php:if test="${his.getStatus() == 1 }">
							<input disabled
								class="btn btn-danger rounded-0 border border-primary text-dark"
								type="submit" value="Đang giao cho bạn">
						</php:if>
						<php:if test="${his.getStatus() == 2 }">
							<input type="hidden" name="recieve" value="${his.getId() }">
							<input
								class="btn btn-danger rounded-0 border border-primary text-dark"
								type="submit" value="Đã nhận được hàng">
						</php:if>
						<php:if test="${his.getStatus() == -1 }">
							<div
								class="btn btn-success rounded-0 border border-primary col-md-12">Đã
								nhận được hàng</div>
						</php:if>
					</form>
					<div class="cart_list_order_status_price" style="width: 15%">
						${his.getFormatTotal() }₫</div>
				</div>
			</div>
		</php:forEach>
	</div>
</div>
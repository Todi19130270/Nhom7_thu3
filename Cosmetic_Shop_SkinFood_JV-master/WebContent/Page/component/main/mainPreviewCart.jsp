<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<main class="order container">
	<div class="container-ordermain row justify-content-center">
		<div class="row container-order" style="width: 80%">
			<h3 class="container-order-title col-md-12">Xác nhận đơn mua của
				bạn</h3>
			<div
				class="container-order-confirm-elementfirst row col-md-12 align-items-center text-center">
				<p class="col-md-1 container-order-confirm-element-img">Ảnh</p>
				<p class="col-md-5 container-order-confirm-element-title">Tên
					sản phẩm</p>
				<p class="col-md-2 container-order-confirm-element-number">Số
					lượng</p>
				<p class="col-md-2 container-order-confirm-element-unitprice">Đơn
					giá</p>
				<p class="col-md-2 container-order-confirm-element-total">Tổng</p>
			</div>
			<php:set var="cartItems" value="${sessionScope.cart.getCartItems()}" />
			<php:forEach items="${cartItems }" var="cartItem">
				<div
					class="container-order-confirm-element row col-md-12 align-items-center text-center"
					style="margin: 0px -12px">
					<img src="${cartItem.getProduct().getFirstImage() }" alt=""
						class="col-md-1 container-order-confirm-element-img ">
					<p class="col-md-5 container-order-confirm-element-title">${cartItem.getProduct().getTitle() }</p>
					<p class="col-md-2 container-order-confirm-element-number">${cartItem.getNumber() }</p>
					<p class="col-md-2 container-order-confirm-element-unitprice">${cartItem.getProduct().getFormatPriceStandard() }đ</p>
					<p class="col-md-2 container-order-confirm-element-total">${cartItem.getTotalSingleFormat() }đ</p>
				</div>
			</php:forEach>


		</div>
		<form action="confirmOrder" class="form_to_comfirm_order row align-items-center "
			style="width: 80%" method="POST">
			<div
				class="form_to_comfirm_order_order col-md-8 row flex-column justify-content-center">
				<h3 class="container-order-title col-md-12">Xác nhận thông tin
					của bạn</h3>
				<div class="row">
					<label for="" class="col-md-6">Nhập tên</label> <input type="text"
						style="width: 250px" name="orderName"
						value="${userLogin.getFullname() }">
				</div>
				<div class="row">
					<label for="" class="col-md-6">Nhập địa chỉ</label> <input
						style="width: 250px" type="text" name="orderAddress"
						value="${userLogin.getDefaultAddress() }">
				</div>
				<div class="row">
					<label for="" class="col-md-6">Nhập Số điện thoại</label> <input
						style="width: 250px" type="text" value="${userLogin.getPhone() }" name="orderPhone" id="phoneNumberCheck" onchange="checkPhone()"
						placeholder="Số điện thoại">
					<p id="phoneNumberCheckMess" class="text-danger" style="font-size: 14px"></p>
				</div>
				<div class="row">
					<label for="" class="col-md-6">Email (Edit in your profile)</label> <input
						style="width: 250px" type="text" value="${userLogin.getEmail() }" name="emailOrder" id="email" 
					>
				</div>
				<div class="row">
					<label for="" class="col-md-6">Nhập đơn vị vận chuyển</label> <select
						name="transport" id="transport_select" class="form-control"
						style="width: 250px" onchange="transferRequest(value)">
						<php:forEach items="${transports }" var="transport">
							<option value="${transport.getId() }">${transport.getTransportNameById()}
								- ${transport.getTransportFeeById() }</option>
						</php:forEach>
					</select>
				</div>
				<div class="row">
					<label for="" class="col-md-6">Nhập mã giảm giá: </label> <input
						style="width: 150px" type="text" name="magiamgia" id="magiamgia"
						placeholder="Nhập mã vô đây !!!">
					<div class="btn btn-danger" onclick="discountCoupon()"
						style="width: 100px; height: 35px;">Áp mã!</div>
				</div>
				<div class="row" style="width: 600px">
					<textarea name="orderNote" wrap="off" cols="28" rows="5"
						placeholder="Nhập ghi chú của bạn ..."></textarea>
				</div>
				
				<php:if test="${message.isEmpty() != true }">
					<h5 class="mt-4 text-danger" >${message }</h5>
				</php:if>

			</div>
			<input type="hidden" name="hidden_transport" id="hidden_transport"
				value="${transports.get(0).getId() }"> 
			<input type="hidden"
				name="hidden_total" id="hidden_total" value="${totalOrder}">
			<input type="hidden"
				name="oldtotal" id="oldtotal" value="${totalOrder}">
			<input type="hidden" name="hidden_coupon" id="hidden_coupon" value="" >
			<input type="hidden" name="userLogin" id="user_id" value="${userLoginId }" >
			<div style="min-width: 350px"
				class="row form_to_comfirm_order_submit col-md-4 flex-column justify-content-end align-items-end text-center">
				
				<div
					class="form_to_comfirm_order_submit-price row col-md-12 text-right align-items-center justify-content-end">
					<p>Thành tiền (VND)</p>
					<del id="total_after_del" class="text-danger"></del>

					<input class="form_to_comfirm_order_submit-pricene" disabled
						id="totalFormat_cart" style="min-width: 350px"
						value="${totalFormat }" />
				</div>
				<div
					class="form_to_comfirm_order_submit-btn col-md-12 row justify-content-end">
					<input type="submit" style="min-width: 350px"
						value="Xác nhận đặt hàng">
				</div>
			</div>
		</form>
	</div>
</main>
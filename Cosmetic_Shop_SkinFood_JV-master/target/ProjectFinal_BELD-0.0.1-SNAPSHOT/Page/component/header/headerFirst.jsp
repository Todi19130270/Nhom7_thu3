<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="header-first">
	<div class="container header_container">
		<div class="header-first__contact">
			<ul class="contact-social">
				<li><a href="#" class="icon-social"><i
						class="fab fa-facebook-f"></i></a></li>
				<li><a href="#" class="icon-social"><i
						class="fab fa-instagram"></i></a></li>
				<li><a href="#" class="icon-social"><i
						class="fab fa-twitter"></i></a></li>
				<li><a href="#" class="icon-social"><i
						class="fab fa-google-plus-g"></i></a></li>
			</ul>

			<div class="contact-product">
				<div class="product-viewed">
					<i class="icon-down fas fa-chevron-down"></i> <span>Sản phẩm
						đã xem</span>
				</div>
			</div>
		</div>
		<div class="header-second__contact">
			<ul class="contact-phone">
				<li class="contact-province">
					<div class="contact-province__choose">
						<span>Chọn miền</span> <i
							class="contact-province__icon fas fa-sort-down"></i>
					</div>
				</li>
				<li class="contact-hotline">
					<div class="contact-hotline__phone">
						<span>Hotline: <span class="phone">0911 384 114</span></span>
					</div>
				</li>
				<li class="contact-switchboard">
					<div class="contact-switchboard__phone">
						<span>Tổng đài tư vấn: <span class="phone">1900 7013</span></span>
					</div>
				</li>
			</ul>
			<div class="contact-account">
				<php:if test="${userLogin == null }">
					<div class="contact-account__create">
						<span>Tài khoản</span> <i class="icon-down fas fa-chevron-down"></i>
					</div>
					<div class="contact-account__log" style="z-index: 10">
						<a href="login" class="contact-account__login"> <span>Đăng
								nhập</span>
						</a> <a href="signup" class="contact-account__signup"> <span>Đăng
								ký</span>
						</a>
					</div>
				</php:if>
				<php:if test="${userLogin != null }">
					<li class="nav-item dropdown d-none d-lg-block user-dropdown"><a
						class="nav-link" id="UserDropdown" href="#"
						data-bs-toggle="dropdown" aria-expanded="false"> <img
							class="img-xs rounded-circle"
							style="width: 30px; height: 30px; border: 1px solid red;"
							src="${userLogin.getAvatar() }" alt="Profile image">
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown m-4 p-4"
							aria-labelledby="UserDropdown">
							<div class="dropdown-header text-center">
								<img class="img-md rounded-circle"
									src="${userLogin.getAvatar() }"
									style="width: 60px; height: 60px; border: 1px solid red;"
									alt="Profile image">
								<p class="mb-1 mt-3 font-weight-semibold">${userLogin.getFullname() }</p>
								<p class="fw-light text-muted mb-0">${userLogin.getEmail() }</p>
							</div>
							<php:if test="${userLogin.getRole() == 1 }">
							<a href="admin/home" class="dropdown-item my-2 "><i
								class="fas fa-cogs text-danger me-2"></i>
								Management Page <span class="badge badge-pill badge-danger">1</span></a>
							</php:if>
							<a href="./profile" class="dropdown-item my-2 "><i
								class="far fa-user text-primary me-2"></i>
								My Profile <span class="badge badge-pill badge-danger">1</span></a>
							<a class="dropdown-item my-2 " href="history"><i
								class="far fa-envelope text-primary me-2"></i>
								History</a> 
							<a class="dropdown-item my-2 " href="./change-key"><i
								class="fas fa-calendar-times text-primary me-2"></i>
								Khóa</a>
							<a class="dropdown-item my-2 " ><i
								class="fas fa-question-circle text-primary me-2"></i>
								FAQ</a> 
							<a class="dropdown-item my-2" href="signout"><i
								class="fas fa-sign-out-alt text-warning me-2"></i>Sign
								Out</a>
						</div></li>

				</php:if>

			</div>
		</div>
	</div>
</div>
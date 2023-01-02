<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<div class="header-third">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="heaeder-nav">
					<ul class="header-nav__menu">
						<li class="header-nav__parent"><a href="#"
							class="header-nav__link"> <i class="fas fa-bars"></i>
						</a></li>
						<php:forEach items="${collections }" var="collection">
							<li class="header-nav__child" style="z-index: 2"><a href=""
								class="child-link">${collection.getName() } <i
									class="fas fa-chevron-down"></i>
							</a>
								<div class="menu-makeup shadow-lg p-3 mb-5 bg-white rounded"
									style="z-index: 2">
									<div class="row" style="z-index: 2">
										<php:if test="${collection.getId()==7 }">
											<php:forEach items="${categories }" var="category">
												<php:if
													test="${category.getCollection() == collection.getId() }">
													<div class="col-lg-6" style="z-index: 2">
														<ul class="list-makeup">
															<li><a href="category?id=${category.getId() }">${category.getName() }</a></li>
														</ul>
													</div>
												</php:if>
											</php:forEach>
										</php:if>

										<php:if test="${collection.getId() !=7 }">
											<php:forEach items="${categories }" var="category">
												<php:if
													test="${category.getCollection() == collection.getId() }">
													<div class="col-lg-3" style="z-index: 2">
														<ul class="list-makeup">
															<li><a href="category?id=${category.getId() }">${category.getName() }</a></li>
														</ul>
													</div>
												</php:if>
											</php:forEach>
										</php:if>
									</div>
								</div></li>
						</php:forEach>



						<li class="header-nav__child"><a href="" class="child-link">
								Thương hiệu <i class="fas fa-chevron-down"></i>
						</a>
							<div class="menu-body shadow-lg p-3 mb-5 bg-white rounded"
								style="z-index: 5">
								<div class="row">
									<php:forEach var="brand" items="${brands }">
										<div class="col-lg-3">
											<div>
												<ul class="list-makeup">
													<li><a href="brand?id=${brand.getId() }">${brand.getName() }</a></li>
												</ul>
											</div>
										</div>
									</php:forEach>
								</div>
							</div></li>
						<li class="header-nav__child"><a href="" class="child-link">
								Khuyến mãi <i class="fas fa-chevron-down"></i>
						</a>
							<div class="menu-body shadow-lg p-3 mb-5 bg-white rounded"
								style="z-index: 5">
								<div class="row">
									<php:forEach var="event" items="${events }">
										<div class="col-lg-3">
											<div>
												<ul class="list-makeup">
													<li><a href="event?id=${event.getId() }">${event.getName() }</a></li>
												</ul>
											</div>
										</div>
									</php:forEach>
								</div>
							</div></li>
					</ul>
					<ul class="header-search-and-cart">
						<li class="header-nav__parent"><a href=""
							class="header-search__icon"> <i class="fas fa-search"></i>
						</a></li class="header-nav__parent">
						<li class="cart-header-right" style="position: relative"><a
							href="cart" class="header-cart__icon"> <i
								class="fas fa-shopping-cart"></i>
						</a> <%@include file="headerCartDiv.jsp"%></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
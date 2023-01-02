<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<!DOCTYPE html>
<div class="profile">
	<h1 class="text-primary">Your profile</h1>
	<form class="margin-auto form-box d-flex" action="profile"
		method="post">
		<div class="col-md-8">
			<div class="text-left line-space">
				<h5 class="text-left text-danger mt-4">Manager account</h5>
				<p class="text-left text-success">Manager your account and
					security information</p>
			</div>
			<div class="form-group row form-element">
				<label for="id" class="col-sm-4 col-form-label">Your Id</label>
				<div class="col-sm-8">
					<input type="text" name="id" class="form-control" id="id"
						placeholder="${userLogin.getId() }" disabled> <input
						type="hidden" name="id" value="${ userLogin.getId() }">
				</div>
			</div>
			<div class="form-group row form-element">
				<label for="username" class="col-sm-4 col-form-label">Username</label>
				<div class="col-sm-8">
					<input type="text" name="username" class="form-control"
						id="username" placeholder="${userLogin.getUsername() }" disabled>
				</div>
			</div>
			<div class="form-group row form-element">
				<label for="username" class="col-sm-4 col-form-label">Password</label>
				<div class="col-sm-8">
					<input type="password" name="password" class="form-control"
						id="password" value="password">
				</div>
			</div>
			<div class="form-group row form-element">
				<label for="inputEmail3" class="col-sm-4 col-form-label">Email</label>
				<div class="col-sm-8">
					<input type="email" name="email" class="form-control"
						id="inputEmail3" value="${userLogin.getEmail()}">
				</div>
			</div>
			<div class="form-group row form-element">
				<label for="fullname" class="col-sm-4 col-form-label">Fullname</label>
				<div class="col-sm-8">
					<input type="text" name="fullname" class="form-control"
						id="fullname" value="${userLogin.getFullname() }">
				</div>
			</div>
			<div class="form-group row form-element">
				<label for="fullname" class="col-sm-4 col-form-label">Phone
					number</label>
				<div class="col-sm-8">
					<input type="text" name="phone" class="form-control" id="fullname"
						value="${ userLogin.getPhone()}">
				</div>
			</div>
			<div class="form-group row form-element">
				<label for="avatar" class="col-sm-4 col-form-label">Avatar
					link</label>
				<div class="col-sm-8">
					<input type="text" name="avatar" class="form-control" id="avatar"
						value="${ userLogin.getAvatar()}">
				</div>
			</div>
			<php:if test="${userLogin.getPublicKey() == ' '}">
				<div class="form-group row form-element">
					<div class="col-sm-8">
<%--						<input type="text" name="publicKey" class="form-control" id="publicKey"--%>
<%--							   value="${ userLogin.getPublicKey()}">--%>
						<a href="/profile?fileName=privateKey.txt">Tải khóa</a>
					</div>
				</div>
			</php:if>
			<php:if test="${mess != null}">
				<p class="text-danger">${mess }</p>
			</php:if>
			<input type="submit" class="btn btn-danger mb-4 col-md-9"
				value="Save">
		</div>

		<div class="col-md-4" style="margin-top: 100px">
			<p>Your avatar</p>
			<img alt="" src="${userLogin.getAvatar() }" class="imgAvatar">
		</div>
	</form>

</div>
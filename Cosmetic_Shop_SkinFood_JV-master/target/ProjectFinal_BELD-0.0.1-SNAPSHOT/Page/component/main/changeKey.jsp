<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<!DOCTYPE html>
<div class="profile">
	<h1 class="text-primary">Your profile</h1>
	<form class="margin-auto form-box d-flex was-validated" enctype="multipart/form-data" action="change-key"
		method="post">
		<div class="col-md-8">
			<div class="text-left line-space">
				<h5 class="text-left text-danger mt-4">Manager account</h5>
				<p class="text-left text-success">Manager your account and
					security information</p>
			</div>
			<div class="form-group row form-element">
				<label for="billingform-key">Khóa</label>
				<div class="custom-file">
					<input type="file" class="custom-file-input" name="fileKey" id="billingform-key" required>
					<label class="custom-file-label" for="billingform-key"></label>
				</div>
			</div>
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
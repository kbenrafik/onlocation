<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<layout:layoutNoHeaderNoFooterNoAside bodyCss="login-page">
<div id="authentification" style="position: relative;">
	<video autoplay controls style="width: 100%;">

		<source
			src="${pageContext.request.contextPath}/src/video/video.mp4"
			type="video/mp4">
		Your browser does not support the video tag.
	</video>
	<div class="login-box" style='position: absolute;top: 7%;left: 38%;'>
		<div class="login-logo">
			<!--  	<a href="../../index2.html"><b>Admin</b>LTE</a> -->
			<a href="../../index2.html"><img
				src="${pageContext.request.contextPath}/src/img/logo.png" alt="" /></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION !=null }">
				<p class="alert alert-danger">
					Votre tentative de connexion a échoué , essayez à nouveau.<br />Causé
					par : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				</p>
			</c:if>
			<br>
			<p class="login-box-msg">Connectez-vous pour commencer votre
				session</p>
			<form name='f' action="<c:url value='j_spring_security_check' />"
				method='POST'>
				<div class="form-group has-feedback">
					<input type='text' name='j_username' placeholder='Login'
						class="form-control">
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name='j_password'
						placeholder='password'>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">&nbsp;</div>
					</div>
					<div class="col-xs-4">
						<button type="submit"
							style="width: 100px; position: relative; left: -11px;"
							class="btn btn-primary btn-block btn-flat">Se
							connecter&nbsp;</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
</div>

</layout:layoutNoHeaderNoFooterNoAside>
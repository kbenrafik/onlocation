<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<img
						src="${pageContext.request.contextPath}/src/img/user2-160x160.jpg"
						class="img-circle" alt="User Image" />
				</security:authorize>
				<security:authorize access="hasRole('ROLE_MANAGER')">
					<img
						src="${pageContext.request.contextPath}/src/img/user8-128x128.jpg"
						class="img-circle" alt="User Image" />
				</security:authorize>
			</div>

			<div class="pull-left info">
				<p>Bonjour : ${username}</p>
				<a href="#"><i class="fa fa-circle text-success"></i> En ligne</a>
			</div>


		</div>
		<!-- /.search form -->
		<common:mainMenu />
	</section>
	<!-- /.sidebar -->
</aside>
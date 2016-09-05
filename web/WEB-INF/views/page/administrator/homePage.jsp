<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"
	value="${pageContext.request.contextPath}/dashboard" />
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="colorBody" value="${colorBody}" />

<layout:defaultPage bodyCss="${colorBody}">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Tableau de board</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Info boxes -->

			<div class='row'>
				<div class='col-md-8'>

					<div class="box box-success">
						<div class="box-header with-border">
							<h3 class="box-title">Rapport visiteurs</h3>
							<div class="box-tools pull-right">
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button class="btn btn-box-tool" data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<div class="row">
								<div class="col-md-9 col-sm-8">
									<div class="pad">
										<!-- Map will be created here -->
										<div id="world-map-markers" style="height: 325px;"></div>
									</div>
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-4">
									<div class="pad box-pane-right bg-green"
										style="min-height: 280px">
										<div class="description-block margin-bottom">
											<div class="sparkbar pad" data-color="#fff">90,70,90,70,75,80,70</div>
											<h5 class="description-header">8390</h5>
											<span class="description-text">Visits</span>
										</div>
										<!-- /.description-block -->
										<div class="description-block margin-bottom">
											<div class="sparkbar pad" data-color="#fff">90,50,90,70,61,83,63</div>
											<h5 class="description-header">30%</h5>
											<span class="description-text">Referrals</span>
										</div>
										<!-- /.description-block -->
										<div class="description-block">
											<div class="sparkbar pad" data-color="#fff">90,50,90,70,61,83,63</div>
											<h5 class="description-header">70%</h5>
											<span class="description-text">Organic</span>
										</div>
										<!-- /.description-block -->
									</div>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->

				</div>
				<!-- /.col -->
				<div class='col-md-4'>
					<!-- USERS LIST -->
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">
							<a href="${contextPath}" class="uppercase">Utilisateur récent</a></h3>
							<div class="box-tools pull-right">
								<span class="label label-danger">8 Nouveaux utilisateurs</span>
								<button class="btn btn-box-tool" data-widget="collapse">
									<i class="fa fa-minus"></i>
								</button>
								<button class="btn btn-box-tool" data-widget="remove">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<ul class="users-list clearfix">
								
									<c:forEach items="${listLatestUser}" var="user">
								<li>
									
								<img
									src="${user.pathPhoto}"
									alt="User Image" /> <a class="users-list-name" href="#">${user.lastName}
								</a>
							
									<span class="users-list-date">
								
									<jsp:setProperty name="now" property="time"
												value="${user.createdAt}" />
											<fmt:formatDate value="${now}"
													  type="date" pattern="dd/MM"/>
								</span>
									 </li>
									
								</c:forEach>
							</ul>
							<!-- /.users-list -->
						</div>
						<!-- /.box-body -->
						<div class="box-footer text-center">
							<a href="${contextPath}/ViewAllUsers/" class="uppercase">Voir tous les utilisateurs</a>
						<form:form action="${contextPath}/ViewAllUsers/" commandName="user">
							<div class="box-body no-padding">
						
							<ul class="users-list clearfix">
							<c:forEach items="${listAllUser}" var="user">
								<li>
									
							<img
									src="${user.pathPhoto}"
									alt="User Image" /> <a class="users-list-name" href="#">${user.lastName}
									</a>
								
									<span class="users-list-date">
									<jsp:setProperty name="now" property="time"
												value="${user.createdAt}" />
											<fmt:formatDate value="${now}"
													pattern="dd/MM" />
									
									</span>
									 </li>
									
								</c:forEach>
							</ul>
							</div>
							</form:form>
						</div>
						<!-- /.box-footer -->
					</div>
					<!--/.box -->
				</div>
				<!-- /.col -->

			</div>
			<!-- /.row -->


		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
</layout:defaultPage>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath"
	value="${pageContext.request.contextPath}/dashboard-manager" />

<layout:defaultPage bodyCss="skin-green">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Gestion des commandes</h1>
		</section>
		<jsp:useBean id="now" class="java.util.Date" />
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Liste des commandes en cours</h3>
						</div>
						<div class="box-body">
							<table id="examplde2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>orderId</th>
										<th>quantity</th>
										<th>createdAt</th>
										<th>status</th>
										<th>user</th>
										<th>product</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listOrdersInProgress}" var="order"
										varStatus="loop">

										<c:choose>
											<c:when
												test="${listOrdersInProgress[loop.index-1].orderId == order.orderId}">
												<tr class="odd">
													<td>=></td>
											</c:when>
											<c:otherwise>
												<tr>
													<td>${order.orderId}</td>
											</c:otherwise>
										</c:choose>

										<jsp:setProperty name="now" property="time"
											value="${order.createdAt}" />
										<td>${order.quantity}</td>
										<td><fmt:formatDate value="${now}"
												pattern="MM/dd/yyyy HH:mm" /></td>
										<c:choose>
											<c:when
												test="${listOrdersInProgress[loop.index-1].orderId == order.orderId}">
												<td>&nbsp;</td>
											</c:when>
											<c:otherwise>
												<td><span class="label label-danger">En cours</span></td>
											</c:otherwise>
										</c:choose>

										<td>${order.user.login}</td>
										<td>${order.product.name}</td>
										<c:choose>
											<c:when
												test="${listOrdersInProgress[loop.index-1].orderId == order.orderId}">
												<td>&nbsp;</td>
											</c:when>
											<c:otherwise>
												<td><a class="btn btn-block btn-primary"
													href="${contextPath}/order/delivred/${order.orderId}">changer
														status à livré</a></td>
											</c:otherwise>
										</c:choose>

										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>orderId</th>
										<th>quantity</th>
										<th>createdAt</th>
										<th>status</th>
										<th>user</th>
										<th>product</th>
										<th>Action</th>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Liste des commandes Livré</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>orderId</th>
										<th>quantity</th>
										<th>createdAt</th>
										<th>status</th>
										<th>user</th>
										<th>product</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listOrdersDelivred}" var="order"
										varStatus="loop">
										<c:choose>
											<c:when
												test="${listOrdersDelivred[loop.index-1].orderId == order.orderId}">
												<tr class="odd">
													<td>=></td>
											</c:when>
											<c:otherwise>
												<tr>
													<td>${order.orderId}</td>
											</c:otherwise>
										</c:choose>
										<td>${order.quantity}</td>
										<jsp:setProperty name="now" property="time"
											value="${order.createdAt}" />
										<td><fmt:formatDate value="${now}"
												pattern="MM/dd/yyyy HH:mm" /></td>
										<c:choose>
											<c:when
												test="${listOrdersDelivred[loop.index-1].orderId == order.orderId}">
												<td>&nbsp;</td>
											</c:when>
											<c:otherwise>
												<td><span class="label label-success">Livré</span></td>
											</c:otherwise>
										</c:choose>

										<td>${order.user.login}</td>
										<td>${order.product.name}</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>orderId</th>
										<th>quantity</th>
										<th>createdAt</th>
										<th>status</th>
										<th>user</th>
										<th>product</th>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<!-- /.content -->
	</div>
	</section>
	<!-- /.content-wrapper -->
</layout:defaultPage>

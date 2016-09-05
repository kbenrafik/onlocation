<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"
	value="${pageContext.request.contextPath}/dashboard-manager" />
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="colorBody" value="${colorBody}" />

<layout:defaultPage bodyCss="${colorBody}">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Tableau de board
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Info boxes -->
		
					<div class="row">
				<div class="col-md-8">
					<!-- TABLE: LATEST ORDERS -->
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Dernieres commandes</h3>
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
						<div class="box-body">
							<div class="table-responsive">
							
								<table class="table no-margin">
									<thead>
								
										<tr>
											<th>Item</th>
											<th>Status</th>
											<th>Location</th>
										</tr>
									</thead>
									<c:forEach items="${listOrdersInProgress}" var="order">
									
									<tbody>
										<tr>
											<td>${order.product.name}</td>
											<td><span class="label label-success">${order.status}</span></td>
											<td><div class="sparkbar" data-color="#00a65a"
													data-height="20">${order.location.name}</div></td>
										</tr>
										
									</tbody>
									</c:forEach>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.box-body -->
						<div class="box-footer clearfix">
							 <a href="javascript::;"
								class="btn btn-sm btn-default btn-flat pull-right">Voir toutes les commandes</a>
								
						</div>
						<!-- /.box-footer -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
				<div class="col-md-4">
					<!-- PRODUCT LIST -->
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">
							<a href="${contextPath}" class="uppercase">Produits ajoutés récemment</a></h3>
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
						<div class="box-body">
							<ul class="products-list product-list-in-box">
							<c:forEach items="${listLatestProduct}" var="product">
							
								<li class="item">
									<div class="product-img">
										<img src="${product.photo}"
											alt="Product Image" />
									</div>
									<div class="product-info">
										<a href="javascript::;" class="product-title">${product.name}<span
											class="label label-warning pull-right">${product.price} DHS</span></a> <span
											class="product-description">${product.description}</span>
									</div>
								</li>
										<!-- /.item -->
								</c:forEach>
							</ul>
						</div>
						<!-- /.box-body -->
						<div class="box-body">
							
							<a href="${contextPath}/ViewAllproducts/" class="uppercase">Voir tous les produits</a>
							<form:form action="${contextPath}/ViewAllproducts/" commandName="product">
							<div class="box-body no-padding">
						
						<ul class="products-list product-list-in-box">
						
							<c:forEach items="${listAllProduct}" var="product">
						
								<li class="item">
									<div class="product-img">
							<img
									src="${product.photo}"
									alt="Product Image" />
									</div>
									<div class="product-info">
										<a href="javascript::;" class="product-title">${product.name}<span
											class="label label-warning pull-right">${product.price} DHS</span></a> <span
											class="product-description">${product.description}</span>
									</div>
								 </li>
									
								</c:forEach>
							</ul>
							</div>
							</form:form>
						
						</div>
						<!-- /.box-footer -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
			

			<!-- Main row -->
			<div class="row">
			
				<div class="col-md-4">
					<!-- /.info-box -->
					<div class="info-box bg-green">
						<span class="info-box-icon"><i
							class="ion ion-ios-heart-outline"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">Mention j'aime</span> <span
								class="info-box-number">${NombreOfLike}</span>
						</div>
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
					<div class="info-box bg-aqua">
						<span class="info-box-icon"><i
							class="ion-ios-chatbubble-outline"></i></span>
						<div class="info-box-content">
							<span class="info-box-text">Commentaires</span>
							
							 <span
								class="info-box-number">${NombreOfComment}</span>
						</div>
					
						<!-- /.info-box-content -->
					</div>
					<!-- /.info-box -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
</layout:defaultPage>
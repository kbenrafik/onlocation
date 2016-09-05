<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath"
	value="${pageContext.request.contextPath}/dashboard" />

<layout:defaultPage bodyCss="skin-blue">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<c:if test="${not empty message}">
			<div class="callout callout-info"
				style="margin-bottom: 0 !important;">${message}</div>
		</c:if>
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Gestion des Categories</h1>
		</section>
		<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Liste des Categories</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id Categorie</th>
										<th>Id foursquare</th>
										<th>Nom de la Categorie</th>
									
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listCategoryLocation}" var="categoryLocation">
										<tr>
											<td>${categoryLocation.categoryID}</td>
											<td>${categoryLocation.foursquareID}</td>
											<td>${categoryLocation.name}</td>
											<td><a
												href="${contextPath}/categoryLocation/update/${categoryLocation.categoryID}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/categoryLocation/delete/${categoryLocation.categoryID}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet Categorie?')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id Categorie</th>
										<th>Id foursquare</th>
										<th>Nom de la categorie</th>
										
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

			<!-- /.content -->
			<div class="row">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">
								<c:if
									test="${(categoryLocation.categoryID != null) or (!empty categoryLocation.name)}">Modifier Categorie</c:if>
								<c:if
									test="${(categoryLocation.categoryID == null) or (empty categoryLocation.name)}">Ajouter Categorie</c:if>
							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form:form action="${contextPath}/categoryLocation/add"
							commandName="categoryLocation">
							<div class="box-body">
								<div class="form-group">
									<c:if
										test="${(!empty categoryLocation.categoryID && !empty categoryLocation.name)}">
										<form:label path="categoryID">Categorie Id:</form:label>
										<form:input path="categoryID" cssClass="form-control"
											readonly="true" />
									</c:if>
									<c:if test="${empty categoryLocation.name}">
										<form:hidden path="categoryID" />
									</c:if>

								</div>
								<div class="form-group">
									<form:label path="foursquareID"> foursquare ID: </form:label>
									<form:input path="foursquareID" cssClass="form-control" />
									<form:errors path="foursquareID" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="name">Nom de la categorie:</form:label>
									<form:input path="name" cssClass="form-control" />
									<form:errors path="name" cssClass="error" />
								</div>
															</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${(categoryLocation.categoryID != null) or (!empty categoryLocation.name)}">
									<input type="submit" value="Modifier Categorie"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet Categorie?')" />
								</c:if>
								<c:if
									test="${(categoryLocation.categoryID == null) or (empty categoryLocation.name)}">
									<input type="submit" value="Ajouter Categorie"
										class="btn btn-primary" />
								</c:if>
							</div>
						</form:form>
					</div>
				</div>
			</div>
	</div>
	</section>
	<!-- /.content-wrapper -->
</layout:defaultPage>

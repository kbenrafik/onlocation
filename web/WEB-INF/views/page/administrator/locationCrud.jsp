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
			<h1>Gestion des emplacements</h1>
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
							<h3 class="box-title">Liste des emplacements</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id Emplacement</th>
										<th>Id foursquare</th>
										<th>Nom de l'emplacement</th>
										<th>Tel</th>
										<th>adresse</th>
										<th>url</th>
										<th>latitude</th>
										<th>longitude</th>
										<th>categorie</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listLocation}" var="location">
										<tr>
											<td>${location.locationID}</td>
											<td>${location.foursquareID}</td>
											<td>${location.name}</td>
											<td>${location.phone}</td>
											<td>${location.adresse}</td>
											<td>${location.url}</td>
											<td>${location.latitude}</td>
											<td>${location.longitude}</td>
											<td>${location.categoryLocation.name}</td>
											<td><a
												href="${contextPath}/location/update/${location.locationID}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/location/delete/${location.locationID}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet emplacement?')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id Emplacement</th>
										<th>Id foursquare</th>
										<th>Nom de l'emplacement</th>
										<th>Tel</th>
										<th>adresse</th>
										<th>url</th>
										<th>latitude</th>
										<th>longitude</th>
										<th>categorie</th>
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
									test="${((!empty location.name && !empty location.foursquareID && !empty location.phone && !empty location.adresse && !empty location.url) && (location.locationID != null)) or (location.locationID != null)}">Modifier emplacement</c:if>
								<c:if
									test="${((empty location.name or empty location.foursquareID or empty location.phone or empty location.adresse or empty location.url ) && (location.locationID == null)) or (location.locationID == null)}">Ajouter emplacement</c:if>
							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form:form action="${contextPath}/location/add"
							commandName="location">
							<div class="box-body">
								<div class="form-group">
									<c:if
										test="${((!empty location.name && !empty location.foursquareID && !empty location.phone && !empty location.adresse && !empty location.url) && (location.locationID != null)) or (location.locationID != null)}">
										<form:label path="locationID">Emplacement Id:</form:label>
										<form:input path="locationID" cssClass="form-control"
											readonly="true" />
									</c:if>
									<c:if test="${empty location.name}">
										<form:hidden path="locationID" />
									</c:if>

								</div>
								<div class="form-group">
									<form:label path="foursquareID"> foursquare ID: </form:label>
									<form:input path="foursquareID" cssClass="form-control" />
									<form:errors path="foursquareID" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="name">Nom de l'emplacement:</form:label>
									<form:input path="name" cssClass="form-control" />
									<form:errors path="name" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="phone">Tel:</form:label>
									<form:input path="phone" cssClass="form-control" />
									<form:errors path="phone" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="adresse">Adresse:</form:label>
									<form:input path="adresse" cssClass="form-control" />
									<form:errors path="adresse" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="url">Url:</form:label>
									<form:input path="url" cssClass="form-control" />
									<form:errors path="url" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="latitude">Latitude:</form:label>
									<form:input path="latitude" cssClass="form-control" />
								</div>
								<div class="form-group">
									<form:label path="longitude">Longitude:</form:label>
									<form:input path="longitude" cssClass="form-control" />
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${((!empty location.name && !empty location.foursquareID && !empty location.phone && !empty location.adresse && !empty location.url) && (location.locationID != null)) or (location.locationID != null)}">
									<input type="submit" value="Modifier Emplacement"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet emplacement?')" />
								</c:if>
								<c:if
									test="${((empty location.name or empty location.foursquareID or empty location.phone or empty location.adresse or empty location.url ) && (location.locationID == null)) or (location.locationID == null)}">
									<input type="submit" value="Ajouter Emplacement"
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

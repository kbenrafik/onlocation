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
		<c:if test="${not empty message}">
			<div class="callout callout-info"
				style="margin-bottom: 0 !important;">${message}</div>
		</c:if>
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Gestion des photos</h1>
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
							<h3 class="box-title">Liste des photos</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id</th>
										<th>prefix</th>
										<th>suffix</th>
										<th>createdAt</th>
										<th>isVisible</th>
										<th>Photo</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listPhoto}" var="photo">
										<tr>
											<td>${photo.id}</td>
											<td>${photo.prefix}</td>
											<td>${photo.suffix}</td>
											<td>${photo.createdAt}</td>
											<td>${photo.isVisible}</td>
											<td><img src="${photo.phathImage}" alt="" height="50"
												width="60"></td>
											<td><a href="${contextPath}/photo/update/${photo.id}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/photo/delete/${photo.id}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet photo ?')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>prefix</th>
										<th>suffix</th>
										<th>createdAt</th>
										<th>isVisible</th>
										<th>Photo</th>
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
									test="${((!empty photo.prefix && !empty suffix && !empty photo.createdAt && !empty photo.isVisible  ) && (photo.id != null)) or (photo.id != null)}">Modifier Photo</c:if>
								<c:if
									test="${(( empty photo.prefix or empty suffix or empty photo.createdAt or empty photo.isVisible ) && (photo.id == null)) or (photo.id == null)}">Ajouter Photo</c:if>
							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form:form action="${contextPath}/photo/add" commandName="photo"
							enctype="multipart/form-data">
							<div class="box-body">

								<div class="form-group">
									<c:if
										test="${((!empty photo.prefix && !empty suffix && !empty photo.createdAt && !empty photo.isVisible) && (photo.id != null)) or (photo.id != null)}">
										<form:label path="id">Photo ID</form:label>
										<form:input path="id" cssClass="form-control" readonly="true" />
										<form:label path="createdAt"> Date de création :</form:label>
										<form:input path="createdAt" cssClass="form-control"
											readonly="true" />
									</c:if>
									<c:if test="${empty photo.prefix}">
										<form:hidden path="id" />
									</c:if>

								</div>

								<div class="form-group">
									<form:label path="prefix">Prefix :</form:label>
									<form:input path="prefix" cssClass="form-control" />
									<form:errors path="prefix" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="suffix">suffix :</form:label>
									<form:input path="suffix" cssClass="form-control" />
									<form:errors path="suffix" cssClass="error" />
								</div>

								<div class="form-group">

									<form:label path="isVisible"> Visibilité : </form:label>

									<form:select path="isVisible">
										<form:option value="" label="--Choisir la visibilité--"
											selected="true" />
										<form:options items="${visibilite}" />
									</form:select>
									<form:errors path="isVisible" cssClass="error" />
								</div>
								<div class="form-group">

								Inserer une photo : <input type="file" name="file">

							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${((!empty photo.prefix && !empty suffix && !empty photo.createdAt && !empty photo.isVisible) && (photo.id != null)) or (photo.id != null)}">

									<input type="submit" value="Modifier Photo"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet Photo?')" />
								</c:if>
								<c:if
									test="${((empty photo.prefix or empty suffix or empty photo.createdAt or empty photo.isVisible) && (photo.id == null)) or (photo.id == null)}">
									<input type="submit" value="Ajouter Photo"
										class="btn btn-primary" />
								</c:if>
							</div>
							<!--<img src="${imagetoDisplay}" width="100" height="100"/>  -->
						</form:form>

					</div>
				</div>
			</div>
	</div>
	</section>
	<!-- /.content-wrapper -->
</layout:defaultPage>

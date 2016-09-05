<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="contextPath"
	value="${pageContext.request.contextPath}/dashboard" />

<layout:defaultPage bodyCss="skin-blue">
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Gestion des managers</h1>
		</section>
		<jsp:useBean id="now" class="java.util.Date" />

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Liste des managers</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id</th>
										<th>utilisateur</th>
										<th>location</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listManager}" var="manager">
										<tr>
											<td>${manager.id}</td>
											<td>${manager.user.login}</td>
											<td>${manager.location.name}</td>
											<td><a href="${contextPath}/manager/update/${manager.id}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/manager/delete/${manager.id}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet utilisateur ??')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>utilisateur</th>
										<th>location</th>
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
									test="${(manager.id != null)}">Modifier manager</c:if>

								<c:if
									test="${(manager.id == null)}">Ajouter manager</c:if>

							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form:form action="${contextPath}/manager/add" commandName="manager">
							<div class="box-body">

								<div class="form-group">
									<c:if
										test="${(manager.id != null)}">
										<form:label path="id">id</form:label>
										<form:input path="id" cssClass="form-control" readonly="true" />
									</c:if>
								</div>
								<div class="form-group">
									<form:label path="location.locationID"> location id : </form:label>
									<form:input path="location.locationID" cssClass="form-control" />
									<form:errors path="location.locationID" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="user.id"> utilisateur id: </form:label>
									<form:input path="user.id" cssClass="form-control" />
									<form:errors path="user.id" cssClass="error" />
								</div>

							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${(manager.id != null)}">
									<input type="submit" value="Modifier un manager"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet utilisateur?')" />
								</c:if>
								<c:if
									test="${(manager.id == null)}">
									<input type="submit" value="Ajouter un manager"
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

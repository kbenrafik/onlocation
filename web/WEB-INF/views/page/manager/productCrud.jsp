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
			<h1>Gestion des produits</h1>
		</section>
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Liste des produits</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nom</th>
										<th>photo</th>
										<th>description</th>
										<th>prix</th>
										<th>Visibilité</th>
										<th>Créé le</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<jsp:useBean id="now" class="java.util.Date" />

									<c:forEach items="${listProduct}" var="product">
										<tr>
											<td>${product.id}</td>
											<td>${product.name}</td>
											<td><img src="${product.photo}" alt="" height="50"
												width="60"></td>
											<td>${product.description}</td>
											<td>${product.price} DHS</td>
											<td><c:if test="${product.isVisible}"><span class="label label-success">Visible</span></c:if>
											<c:if test="${!product.isVisible}"><span class="label label-danger">Non visible</span></c:if></td>
											<jsp:setProperty name="now" property="time"
												value="${product.createdAt}" />

											<td><fmt:formatDate value="${now}"
													pattern="MM/dd/yyyy HH:mm" /></td>
											<td><a
												href="${contextPath}/product/update/${product.id}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/product/delete/${product.id}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet produit ?')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>Nom</th>
										<th>photo</th>
										<th>description</th>
										<th>prix</th>
										<th>Visibilité</th>
										<th>Créé le</th>
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
								<c:if test="${product.id != null}">Modifier produit</c:if>
								<c:if test="${product.id == null}">Ajouter produit</c:if>
							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form:form action="${contextPath}/product/add"
							commandName="product">
							<div class="box-body">
								<div class="form-group">
									<c:if test="${product.id != null}">
										<form:label path="id">product ID</form:label>
										<form:input path="id" cssClass="form-control" readonly="true" />
										<form:label path="createdAt"> Date de création :</form:label>
										<form:input path="createdAt" cssClass="form-control"
											readonly="true" />
									</c:if>
									<c:if test="${empty product.name}">
										<form:hidden path="id" />
									</c:if>

								</div>

								<div class="form-group">
									<form:label path="name">Nom :</form:label>
									<form:input path="name" cssClass="form-control" />
									<form:errors path="name" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="photo">Chemin image :</form:label>
									<form:input path="photo" cssClass="form-control" />
									<form:errors path="photo" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="description">description :</form:label>
									<form:input path="description" cssClass="form-control" />
									<form:errors path="description" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="price">Prix :</form:label>
									<form:input path="price" cssClass="form-control" />
									<form:errors path="price" cssClass="error" />
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
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${((!empty photo.prefix && !empty suffix && !empty photo.createdAt && !empty photo.isVisible && !empty photo.location.name ) && (photo.id != null)) or (photo.id != null)}">

									<input type="submit" value="Modifier produit"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet Photo?')" />
								</c:if>
								<c:if
									test="${((empty photo.prefix or empty suffix or empty photo.createdAt or empty photo.isVisible or empty photo.location.name ) && (photo.id == null)) or (photo.id == null)}">
									<input type="submit" value="Ajouter produit"
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

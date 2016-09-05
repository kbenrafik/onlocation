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
			<h1>Gestion des promotions</h1>
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
							<h3 class="box-title">Liste des promotions</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Promotion Id</th>
										<th>Type Promotion</th>
										<th>Date Debut</th>
										<th>Date Fin</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listPromotion}" var="promotion">
										<tr>
											<td>${promotion.promotionID}</td>
											<td>${promotion.promotionType}</td>
											<td>${promotion.startDate}</td>
											<td>${promotion.endDate}</td>
											<td><a
												href="${contextPath}/promotion/update/${promotion.promotionID}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/promotion/delete/${promotion.promotionID}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet promotion?')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Promotion Id</th>
										<th>Type Promotion</th>
										<th>Date Debut</th>
										<th>Date Fin</th>
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
									test="${((!empty promotion.startDate && !empty promotion.endDate && !empty promotion.promotionType) && (promotion.promotionID != null)) or (promotion.promotionID != null)}">Modifier Promotion</c:if>
								<c:if
									test="${((empty promotion.startDate or empty promotion.endDate or empty promotion.promotionType) && (promotion.promotionID == null)) or (promotion.promotionID == null)}">Ajouter Promotion</c:if>
							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form:form action="${contextPath}/promotion/add"
							commandName="promotion">
							<div class="box-body">

								<div class="form-group">
									<c:if
										test="${((!empty promotion.startDate && !empty promotion.endDate && !empty promotion.promotionType) && (promotion.promotionID != null)) or (promotion.promotionID != null)}">
										<form:label path="promotionID">promotion ID</form:label>
										<form:input path="promotionID" cssClass="form-control"
											readonly="true" />
									</c:if>
									<c:if test="${empty promotion.startDate}">
										<form:hidden path="promotionID" />
									</c:if>

								</div>
								<div class="form-group">
									<form:label path="promotionType"> Type promotion:</form:label>
									<form:input path="promotionType" cssClass="form-control" />
									<form:errors path="promotionType" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="startDate"> Date Debut(dd/MM/YYYY): </form:label>
									<fmt:formatDate value="${promotion.startDate}" var="dateString"
										pattern="dd/MM/yyyy" />
									<form:input path="startDate" cssClass="form-control"
										value="${dateString}" />

									<form:errors path="startDate" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="endDate">Date Fin(dd/MM/YYYY):</form:label>
									<fmt:formatDate pattern='dd/MM/yyyy' type='date'
										value='${promotion.endDate}' var="formattedDate" />
									<form:input path="endDate" cssClass="form-control"
										data-format="dd/MM/yyyy" type="text" value="${formattedDate}" />
									<form:errors path="endDate" cssClass="error" />
								</div>

							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${((!empty promotion.startDate && !empty promotion.endDate && !empty promotion.promotionType) && (promotion.promotionID != null)) or (promotion.promotionID != null)}">
									<input type="submit" value="Modifier promotion"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet promotion?')" />
								</c:if>
								<c:if
									test="${((empty promotion.startDate or empty promotion.endDate or empty promotion.promotionType) && (promotion.promotionID == null)) or (promotion.promotionID == null)}">
									<input type="submit" value="Ajouter promotion"
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

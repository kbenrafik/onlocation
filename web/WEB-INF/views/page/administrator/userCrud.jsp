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
		<c:if test="${not empty message}">
			<div class="callout callout-info"
				style="margin-bottom: 0 !important;">${message}</div>
		</c:if>
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Gestion des utilisateurs</h1>
		</section>
		<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
		<jsp:useBean id="now" class="java.util.Date" />

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Liste des utilisateurs</h3>
						</div>
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nom</th>
										<th>Prenom</th>
										<th>Email</th>
										<th>Tel</th>
										<th>Adresse</th>
										<th>Créer_le</th>
										<th>Login</th>
										<th>Role</th>
										<th>Photo</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listUser}" var="user">
										<tr>
											<td>${user.id}</td>
											<td>${user.lastName}</td>
											<td>${user.firstName}</td>
											<td>${user.email}</td>
											<td>${user.phone}</td>
											<td>${user.adresse}</td>
											<jsp:setProperty name="now" property="time"
												value="${user.createdAt}" />
											<td><fmt:formatDate value="${now}"
													pattern="MM/dd/yyyy HH:mm" /></td>
													<!--  dateTime.monthOfYear()
													<p>Formatted Date (2): <fmt:formatDate type="date" value="${now}" /></p>
													-->
											<td>${user.login}</td>
											<td>${user.roles.authority}</td>
											<td><img src="${user.pathPhoto}" alt="" height="50" width="60"></td>
											<td><a href="${contextPath}/user/update/${user.id}"
												class="btn btn-default btn-sm"><i
													class="fa fa-fw fa-edit"></i></a>&nbsp;<a
												href="${contextPath}/user/delete/${user.id}"
												class="btn btn-default btn-sm"
												onclick="return confirm('Êtes vous sûr de vouloir SUPPRIMER cet utilisateur ??')"><i
													class="fa fa-trash-o"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>Nom</th>
										<th>Prenom</th>
										<th>Email</th>
										<th>Tel</th>
										<th>Adresse</th>
										<th>Créer_le</th>
										<th>Login</th>
										<th>Role</th>
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
									test="${((!empty user.login && !empty user.password && !empty user.lastName && !empty user.firstName && !empty user.adresse && !empty user.phone && !empty user.email) && (user.roles.idRole != null)) or (user.id != null)}">Modifier utilisateur</c:if>

								<c:if
									test="${((empty user.login or empty user.password or empty user.lastName or empty user.firstName or user.adresse or empty user.phone or empty user.email) or (user.roles.idRole == null)) or(user.id == null)}">Ajouter utilisateur</c:if>

							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form:form action="${contextPath}/user/add" commandName="user"
							enctype="multipart/form-data">
							<div class="box-body">

								<div class="form-group">
									<c:if
										test="${((!empty user.login && !empty user.password && !empty user.lastName && !empty user.firstName && !empty user.adresse && !empty user.phone && !empty user.email) && (user.roles.idRole != null)) or (user.id != null)}">
										<form:label path="id">id</form:label>
										<form:input path="id" cssClass="form-control" readonly="true" />
										<form:label path="createdAt">Créer le</form:label>
										<form:input path="createdAt" cssClass="form-control"
											readonly="true" />
									</c:if>
									<c:if test="${empty user.login}">
										<form:hidden path="id" />
									</c:if>

								</div>
								<div class="form-group">

									<form:label path="roles.idRole"> Role : </form:label>

									<form:select path="roles.idRole">

										<form:option value="" label="--Choisir un role--"
											selected="true" />
										<form:options items="${listRole}" itemLabel="authority"
											itemValue="idRole" />

									</form:select>
									<form:errors path="roles.idRole" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="lastName"> Nom : </form:label>
									<form:input path="lastName" cssClass="form-control" />
									<form:errors path="lastName" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="firstName"> Prénom : </form:label>
									<form:input path="firstName" cssClass="form-control" />
									<form:errors path="firstName" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="phone"> Téléphone : </form:label>
									<form:input path="phone" cssClass="form-control" />
									<form:errors path="phone" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="email"> Email : </form:label>
									<form:input path="email" cssClass="form-control" />
									<form:errors path="email" cssClass="error" />
								</div>
								<div class="form-group">
									<form:label path="adresse"> Adresse : </form:label>
									<form:input path="adresse" cssClass="form-control" />
									<form:errors path="adresse" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="login"> Login : </form:label>
									<form:input path="login" cssClass="form-control" />
									<form:errors path="login" cssClass="error" />
								</div>

								<div class="form-group">
									<form:label path="password"> Mot de Passe: </form:label>
									<form:input path="password" cssClass="form-control" />
									<form:errors path="password" cssClass="error" />
								</div>
								<!--  <div class="form-group">
									<form:label path="createdAt"> Crée le : </form:label>
									<form:input path="createdAt" cssClass="form-control" />
									<form:errors path="createdAt" cssClass="error" />
								</div> -->
								Inserer une photo : <input type="file" name="file" />

							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<c:if
									test="${((!empty user.login && !empty user.password && !empty user.lastName && !empty user.firstName && !empty user.adresse && !empty user.phone && !empty user.email) && (user.roles.idRole != null)) or (user.id != null)}">
									<input type="submit" value="Modifier un utilisateur"
										class="btn btn-primary"
										onclick="return confirm('Êtes vous sûr de vouloir MODIFIER cet utilisateur?')" />
								</c:if>
								<c:if
									test="${((empty user.login or empty user.password or empty user.lastName or empty user.firstName or user.adresse or empty user.phone or empty user.email) or (user.roles.idRole == null)) && (user.id == null)}">
									<input type="submit" value="Ajouter un utilisateur"
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

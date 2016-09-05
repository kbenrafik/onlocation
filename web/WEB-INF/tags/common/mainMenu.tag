<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="sidebar-menu">
	<li class="header">MENU</li>
	<!-----------------------------MENU ADMINISTRATOR ------------------------------- -->
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-circle-o"></i>Tableau de board</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'user')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard/user/manage"><i class="fa fa-circle-o"></i>Gérer les utilisateurs</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'manager')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard/manager/manage"><i class="fa fa-circle-o"></i>Gérer les managers</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'location')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard/location/manage"><i class="fa fa-circle-o"></i>Gérer les emplacements</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'category')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard/categoryLocation/manage"><i class="fa fa-circle-o"></i>Gérer les categories</a>
	</security:authorize>
	<!-----------------------------MENU MANAGER ------------------------------------- -->
	<security:authorize access="hasRole('ROLE_MANAGER')">
		<li><a href="${pageContext.request.contextPath}/dashboard-manager"><i class="fa fa-circle-o"></i>Tableau de board</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'product')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard-manager/product/manage"><i class="fa fa-circle-o"></i>Gérer les produits</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'photo')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard-manager/photo/manage"><i class="fa fa-circle-o"></i>Gérer les photos</a></li>
		<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'order')}">active</c:if>"><a href="${pageContext.request.contextPath}/dashboard-manager/order/manage"><i class="fa fa-circle-o"></i>Gérer les commandes</a></li>
    </security:authorize>

</ul>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<%@ attribute name="bodyCss" required="false" type="java.lang.String" %>

<template:master bodyCss="${bodyCss}">
	<jsp:body>
         <common:header />
         <common:aside/>
         <jsp:doBody />
         <common:footer />
     </jsp:body>
</template:master>

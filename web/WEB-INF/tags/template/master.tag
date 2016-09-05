<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" type="java.lang.String" %>
<%@ attribute name="bodyCss" required="false" type="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${pageContext.request.contextPath}/src/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/src/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/src/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/src/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    
	<title>${title}</title>
</head>
<body class="${bodyCss}">

	<jsp:doBody/>

    <script src="${pageContext.request.contextPath}/src/js/jQuery-2.1.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/src/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/src/js/jquery.dataTables.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/src/js/dataTables.bootstrap.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/src/js/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src='${pageContext.request.contextPath}/src/js/fastclick.min.js'></script>
    <script src="${pageContext.request.contextPath}/src/js/app.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/src/js/demo.js" type="text/javascript"></script>

    <script type="text/javascript">
      $(function () {
        $("#example1").dataTable();
        $('#example2').dataTable({
          "bPaginate": true,
          "bLengthChange": false,
          "bFilter": false,
          "bSort": true,
          "bInfo": true,
          "bAutoWidth": false
        });
      });
    </script>
    
</body>
</html>
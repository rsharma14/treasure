<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%--     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <title>Sales Stock</title> -->
<title>.</title>
<link rel="stylesheet" type="text/css" href="../css/plugins/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/plugins/font-awesome.css">
<link rel="stylesheet" type="text/css" href="../css/plugins/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="../css/salesstock.css">

<script src="../js/plugins/jquery-3.3.1.js"></script>
<script src="../js/plugins/jquery.validate.min.js"></script>
<script src="../js/plugins/bootstrap.min.js"></script>
<script src="../js/plugins/jquery.dataTables.min.js"></script>
<script src="../js/plugins/bootbox.js"></script>
<script src="../js/plugins/jquery.blockUI.js"></script>
<script src="../js/plugins/notify.min.js"></script>

<script src="../js/constants.js"></script>
<script src="../js/services.js"></script>
<script src="../js/salesstock.js"></script>
</head>
<body> 
	<div>
	<div id="menu-header"><jsp:include page="jsp/includepages/header.jsp" /></div>
		<div id="main-content"></div>
		<div  id="menu-footer"></div>

	</div>

	<script type="text/javascript">
$(document).ready(function(){
	attachMenuEvents();
});
</script>
</body>
</html>
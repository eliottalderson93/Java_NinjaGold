<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ninja Gold</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
<div class = "wrapper">
<p>Your Gold: <c:out value="${gold}"></c:out></p>
<form class = "square" method = "post" action = "/find"><p>Farm</p><p>earn 10-20</p><input type = "hidden" name = "farm" value = <c:out value="${farm}"></c:out>><button>Find Gold</button></form>
<form class = "square" method = "post" action = "/find"><p>Cave</p><p>earn 5-10</p><input type = "hidden" name = "cave" value = <c:out value="${cave}"></c:out>><button>Find Gold</button></form>
<form class = "square" method = "post" action = "/find"><p>House</p><p>earn 2-5</p><input type = "hidden" name = "house" value = <c:out value="${house}"></c:out>><button>Find Gold</button></form>
<form class = "square" method = "post" action = "/find"><p>Casino</p><p>earn -50 to 50</p><input type = "hidden" name = "casino" value = <c:out value="${casino}"></c:out>><button>Find Gold</button></form>
<p>Activities:</p>
<div class = "activities">
<c:forEach var="activity" items="${activityList}">
    <p><c:out value="${activity}"/><p>
</c:forEach>
</div>
</div>
</body>
</html>
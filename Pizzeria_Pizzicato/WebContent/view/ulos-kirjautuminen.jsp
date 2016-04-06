<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Uloskirjautuminen</title>
</head>
<body>
<%
String userName = null;
//allow access only if session exists
if(session.getAttribute("kayttaja") == null){
    response.sendRedirect("pizzaMenu");
}else userName = (String) session.getAttribute("kayttaja");
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("kayttaja")) userName = cookie.getValue();
}
}
%>
<h3>Hei <%=userName %>, do the checkout.</h3>
<br>
<form action="<%=response.encodeURL("uloskirjautuminen") %>" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>
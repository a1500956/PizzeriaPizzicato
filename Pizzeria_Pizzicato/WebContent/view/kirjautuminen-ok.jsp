<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Kirjautuminen</title>
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("kayttaja") == null){
    response.sendRedirect("pizzaMenu");
}else user = (String) session.getAttribute("kayttaja");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("kayttaja")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
    sessionID = session.getId();
}
%>
<h3>Hei <%=userName %>, kirjautuminen onnistui.</h3>
<br>

<!-- need to encode all the URLs where we want session information to be passed -->
<a href="<%=response.encodeRedirectURL("pizzaMenu") %>">Etusivu</a>
<a href="<%=response.encodeRedirectURL("listaaPizzat") %>">Pizzalista</a>
<form action="<%=response.encodeRedirectURL("uloskirjautuminen") %>" method="post">
<br>
<br>
<input type="submit" value="Uloskirjaus" >
</form>
</body>
</html>
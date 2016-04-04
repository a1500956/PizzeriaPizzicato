<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="kayttajaTunnus" type="java.lang.String" scope="request" />
<jsp:useBean id="kayttajaNimi" type="java.lang.String" scope="request" />
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
<h3>Hi <%=kayttajaTunnus %>, Login successful. Your Session ID=<%=sessionID %></h3>
<br>
User=<%=kayttajaNimi %>
<br>
<!-- need to encode all the URLs where we want session information to be passed -->
<a href="<%=response.encodeURL("etusivu.jsp") %>">Etusivu</a>
<a href="<%=response.encodeURL("listaa-pizzat.jsp") %>">Pizzalista</a>
<form action="<%=response.encodeURL("uloskirjaus") %>" method="post">
<input type="submit" value="Uloskirjaus" >
</form>
</body>
</html>
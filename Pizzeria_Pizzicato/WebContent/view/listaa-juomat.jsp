<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Juoma"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma> "
scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Juomalista</title>
</head>
	<body>
	<%
	int ryhma= 1;
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("ryhma").equals(ryhma)){
		userName = (String) session.getAttribute("kayttaja");
		
	}else{ response.sendRedirect("pizzaMenu");
}	
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
<header>
<div class="login">
    <form action="<%=response.encodeURL("uloskirjautuminen") %>" method="post">
      <div class="loginrow2">
      <h4><%=userName %>, olet kirjautuneena.</h4>
	<input type="submit" value="Uloskirjaus" >
      </div>
    </form>
</div>
</header>

<h1>JUOMALISTA</h1>
		
		<p>${message}</p>
<c:remove var="message" scope="session" /> 

		

		<a href="<%=response.encodeURL("lisaa-juoma") %>">Lis‰‰ juoma</a>
		<a href="<%=response.encodeURL("listaaPizzat") %>">N‰yt‰ pizzat</a>
		<a href="<%=response.encodeURL("listaaAktiivisetTilaukset") %>">N‰yt‰ aktiiviset tilaukset</a>
		
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>MENUSSA</h4></td>
			<td><h4>JUOMAT</h4></td>
			<td><h4>HINTA (&euro;)</h4></td>
			<td><h4>LITRAKOKO</h4></td>
			<td><h4>TOIMINNOT</h4></td>
				
			<%for(int i = 0; i < juomat.size(); i++) {%>
			<tr>
				<td><div class="nakyvyys"><%if (juomat.get(i).getNakyy()==1){out.print("kyll‰");}else{out.print("ei");}%></div></td>
				<td><div class="juomat"><%=juomat.get(i).getNimi()%></div></td>
				<td><div class="juomat"><%=nf.format(juomat.get(i).getHinta())%></div></td>
				<td><div class="juomat"><%=nf.format(juomat.get(i).getLitrakoko())%></div></td>
				<td><div class="toiminnot"> 
				<a href="muokkaa-juoma?id=<%=juomat.get(i).getId()%>" class="button">
				Muokkaa
				</a>		
				<a href="poista-juoma?id=<%=juomat.get(i).getId()%>&id2=<%=juomat.get(i).getNimi()%>" class="button">
				Poista
				</a></div>
				</td>								
			</tr>
			<% } %>
		</table><br>
		
</body>
</html>
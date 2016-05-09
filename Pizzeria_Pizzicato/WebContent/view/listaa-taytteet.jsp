<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="taytteet" type="java.util.ArrayList<Tayte> "
scope="request" />

<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Täytelista</title>


</head>
	<body class="body2">
	
<%
//allow access only if session exists
int ryhma= 1;
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("ryhma").equals(ryhma)){
		userName = (String) session.getAttribute("kayttaja");
		
	}else{ response.sendRedirect("pizzaMenu");
}	
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("kayttaja")) userName = cookie.getValue();

}
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
		
		<h1>TÄYTELISTA</h1>
		
		<p>${message}</p>
<c:remove var="message" scope="session" />

<div class="container">
<a href="<%=response.encodeURL("listaaPizzat") %>" class="btn btn-info" role="button">Takaisin</a>
<a href="lisaa-tayte" class="btn btn-info" role="button">Lisää täyte</a>
</div>
		
		

		<table class="listaa-pizzat" width="auto" border="1" align="center">
		
		<tr>
			<td><h4>TÄYTE</h4></td>
			<td><h4>TÄYTE ENG</h4></td>
			<td><div class="toiminnot2"><h4>HINTA/KG</h4></div></td>
			<td><h4>TOIMINNOT</h4></td>
				
		</tr>
			<%for(int i = 0; i < taytteet.size(); i++) {%>
			<tr>
				
				<td><div class="pizzat"><%=taytteet.get(i).getTayte_nimi()%></div></td>
				<td><div class="pizzat"><%=taytteet.get(i).getTayte_nimi_en()%></div></td>
				<td><div class="pizzat"><%=nf.format(taytteet.get(i).getTayte_hinta())%></div></td>
				
				<td><div class="toiminnot"><a href="muokkaa-tayte?id=<%=taytteet.get(i).getTayte_id()%>" class="btn btn-primary btn-sm" role="button">Muokkaa</a>
				
				<a href="poista-tayte?id=<%=taytteet.get(i).getTayte_id()%>&id2=<%=taytteet.get(i).getTayte_nimi()%>" class="btn btn-primary btn-sm" role="button">Poista</a>
				
				</div>
				</td>							
			</tr>
			<% } %>
		</table><br>
				
 
<script type="text/javascript"> window.onload = alertName; </script>
		
		
	</body>
</html>

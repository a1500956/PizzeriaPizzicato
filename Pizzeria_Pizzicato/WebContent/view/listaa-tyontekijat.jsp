<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Kayttaja"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:useBean id="tyontekijat" type="java.util.ArrayList<Kayttaja> "
scope="request" />

<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title>Työntekijät</title>
</head>
	<body class="body2">
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

<h1>TYÖNTEKIJÄT</h1>
		
		<p>${message}</p>
<c:remove var="message" scope="session" /> 


	<div class="container">
    <a href="luoTyontekija" class="btn btn-info" role="button">Lisää työntekijä</a>
	<a href="listaaPizzat" class="btn btn-info" role="button">Pizzat</a>
	</div>
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><div class="toiminnot2"><h4>ETUNIMI</h4></div></td>
			<td><div class="toiminnot2"><h4>SUKUNIMI</h4></div></td>
			<td><div class="toiminnot2"><h4>PUHNRO</h4></div></td>
			<td><div class="toiminnot2"><h4>KÄYTTÄJÄTUNNUS</h4></div></td>
			<td><div class="toiminnot2"><h4>TOIMINNOT</h4></div></td>
				
			<%for(int i = 0; i < tyontekijat.size(); i++) {%>
			<tr>
				<%if(tyontekijat.get(i).getRyhma_id()==2 || tyontekijat.get(i).getRyhma_id()==4 || tyontekijat.get(i).getRyhma_id()==5) { %>
				<td><div class="tyontekijat"><%=tyontekijat.get(i).getKayttaja_enimi()%></div></td>
				<td><div class="tyontekijat"><%=tyontekijat.get(i).getKayttaja_snimi()%></div></td>
				<td><div class="tyontekijat"><%=tyontekijat.get(i).getKayttaja_puhnro()%></div></td>
				<td><div class="tyontekijat"><%=tyontekijat.get(i).getKayttaja_ktunnus()%></div></td>
				
				<td><div class="toiminnot2">
				
				<a href="poistaTyontekija?id=<%=tyontekijat.get(i).getKayttaja_id()%>&id2=<%=tyontekijat.get(i).getKayttaja_snimi()%>" class="btn btn-primary btn-sm" role="button">Poista</a>
				
				</div>
				</td>				
				
								
			</tr>
			<% } %>
			<% } %>
		</table><br>
		
		
</body>
</html>
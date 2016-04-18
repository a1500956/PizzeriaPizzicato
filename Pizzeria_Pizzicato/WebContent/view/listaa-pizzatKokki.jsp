
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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Täytelista</title>


</head>
	<body>
	
<%
//allow access only if session exists
String userName = null;
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
		
		<p class="viesti">${message}</p>
<c:remove var="message" scope="session" /> 
		
		
		<div class ="button"><a href="<%=response.encodeURL("listaaPizzat") %>">Palaa pizzalistaan</a></div><br>
		<a href="lisaa-tayte" class="button">Lisää täyte</a>
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		
		<tr>
			<td><h4>TÄYTE</h4></td>
			<td><h4>TÄYTE ENG</h4></td>
			<td><h4>HINTA/KG</h4></td>
			<td><h4>TOIMINNOT</h4></td>
				
		</tr>
			<%for(int i = 0; i < taytteet.size(); i++) {%>
			<tr>
				
				<td><div class="pizzat"><%=taytteet.get(i).getTayte_nimi()%></div></td>
				<td><div class="pizzat"><%=taytteet.get(i).getTayte_nimi_en()%></div></td>
				<td><div class="pizzat"><%=nf.format(taytteet.get(i).getTayte_hinta())%></div></td>
				
				<td><div class="toiminnot"> 
				<a href="muokkaa-tayte?id=<%=taytteet.get(i).getTayte_id()%>" class="button">
				Muokkaa
				</a>		
				<a href="poista-tayte?id=<%=taytteet.get(i).getTayte_id()%>&id2=<%=taytteet.get(i).getTayte_nimi()%>" class="button">
				Poista
				</a></div>
				</td>								
			</tr>
			<% } %>
		</table><br>
				
 
<script type="text/javascript"> window.onload = alertName; </script>
		
		
	</body>
</html>

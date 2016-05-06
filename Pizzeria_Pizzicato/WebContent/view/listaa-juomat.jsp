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

<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Juomalista</title>
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

<h1>JUOMALISTA</h1>
		
		<p>${message}</p>
<c:remove var="message" scope="session" /> 

<<<<<<< HEAD
		<div class="container">
<a href="lisaa-juoma" class="btn btn-info" role="button">Lis‰‰ juoma</a>
<a href="listaaPizzat" class="btn btn-info" role="button">Pizzat</a>
<a href="listaaAktiivisetTilaukset" class="btn btn-info" role="button">Tilaukset</a>
</div>
=======
		
<table align="center" border="0">
		<tr>
	
			<td>	
				<form action="lisaa-juoma">
   	 				<input type="submit" value="Lis‰‰ juoma">
				</form>
			</td>
			
			<td>	
				<form action="listaaPizzat">
   	 				<input type="submit" value="Pizzat">
				</form>
			</td>
			
			<td>	
				<form action="listaaAktiivisetTilaukset">
   	 				<input type="submit" value="Tilaukset">
				</form>
			</td>
		</tr>
</table>
		
>>>>>>> branch 'master' of https://github.com/a1500956/PizzeriaPizzicato.git
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><div class="toiminnot2"><h4>MENUSSA</h4></div></td>
			<td><div class="toiminnot2"><h4>JUOMAT</h4></div></td>
			<td><div class="toiminnot2"><h4>HINTA (&euro;)</h4></div></td>
			<td><div class="toiminnot2"><h4>LITRAKOKO</h4></div></td>
			<td><div class="toiminnot2"><h4>TOIMINNOT</h4></div></td>
				
			<%for(int i = 0; i < juomat.size(); i++) {%>
			<tr>
				<td><div class="nakyvyys"><%if (juomat.get(i).getNakyy()==1){out.print("kyll‰");}else{out.print("ei");}%></div></td>
				<td><div class="juomat"><%=juomat.get(i).getNimi()%></div></td>
				<td><div class="juomat"><%=nf.format(juomat.get(i).getHinta())%></div></td>
				<td><div class="juomat"><%=nf.format(juomat.get(i).getLitrakoko())%></div></td>
				<td><div class="toiminnot2"><a href="muokkaa-juoma?id=<%=juomat.get(i).getId()%>" class="btn btn-primary btn-sm" role="button">Muokkaa</a>
				
				<a href="poista-juoma?id=<%=juomat.get(i).getId()%>&id2=<%=juomat.get(i).getNimi()%>" class="btn btn-primary btn-sm" role="button">Poista</a>
				
				</div>
				</td>					
			</tr>
			<% } %>
		</table><br>
		
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>



<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />

<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Pizzalista</title>


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

		<h1>PIZZALISTA</h1>
		
		<p>${message}</p>
<c:remove var="message" scope="session" /> 


<div class="container">
<a href="lisaa-pizza" class="btn btn-info" role="button">Lisää pizza</a>
<a href="listaa-taytteet" class="btn btn-info" role="button">Täytteet</a>
<a href="listaaJuomat" class="btn btn-info" role="button">Juomat</a>
<a href="listaaAktiivisetTilaukset" class="btn btn-info" role="button">Tilaukset</a>
<a href="listaaArkisto" class="btn btn-info" role="button">Arkisto</a>
<a href="listaaPizzatkuski" class="btn btn-info" role="button">Kuskin näkymä</a>
<a href="luoTyontekija" class="btn btn-info" role="button">Lisää työntekijä</a>
<a href="listaaTyontekijat" class="btn btn-info" role="button">Työntekijät</a>
</div>

	
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>MENUSSA</h4></td>
			<td><h4>PIZZAT</h4></td>
			<td><h4>HINTA (&euro;)</h4></td>
			<td><h4>TÄYTTEET</h4></td>
			<td><h4>TOIMINNOT</h4></td>
				
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			<tr>
				<td><div class="nakyvyys"><%if (pizzat.get(i).getNakyy()==1){out.print("kyllä");}else{out.print("ei");}%></div></td>
				<td><div class="pizzat"><%=pizzat.get(i).getNimi()%></div></td>
				<td><div class="pizzat"><%=nf.format(pizzat.get(i).getHinta())%></div></td>
				<td><div class="taytteet"><%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>
												 </div></td>
				<td><div class="toiminnot"><a href="muokkaa-pizza?id=<%=pizzat.get(i).getId()%>" class="btn btn-primary btn-sm" role="button">Muokkaa</a>
				
				<a href="poista-pizza?id=<%=pizzat.get(i).getId()%>&id2=<%=pizzat.get(i).getNimi()%>" class="btn btn-primary btn-sm" role="button">Poista</a>
				
				</div>
				</td>								
			</tr>
			<% } %>
		</table><br>
		
	
		
	</body>
</html>

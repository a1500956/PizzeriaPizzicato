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

<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Pizzalista</title>


</head>
	<body>

		<h1>PIZZALISTA</h1>
		
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
    
		<a href="lisaa-pizza" class="button">Lisää pizza</a>
		<a href="listaa-taytteet" class="button">Näytä täytteet</a>
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>MENUSSA</h4></td>
			<td><h4>PIZZAT</h4></td>
			<td><h4>HINTA</h4></td>
			<td><h4>TÄYTTEET</h4></td>
			<td><h4>TOIMINNOT</h4></td>
				
		</tr>
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
				<td><div class="toiminnot"> 
				<a href="muokkaa-pizza?id=<%=pizzat.get(i).getId()%>" class="button">
				Muokkaa
				</a>		
				<a href="poista-pizza?id=<%=pizzat.get(i).getId()%>&id2=<%=pizzat.get(i).getNimi()%>" class="button">
				Poista
				</a></div>
				</td>								
			</tr>
			<% } %>
		</table><br>
		
		
				
			<%
session.setMaxInactiveInterval(2);
%>

 <script type="text/javascript">
var Msg ='<%=session.getAttribute("viesti")%>';
    if (Msg == "y") {
 function alertName(){
 alert("Tallennus onnistui!");
 } 
 }
 </script> 
<script type="text/javascript"> window.onload = alertName; </script>
		
		
	</body>
</html>

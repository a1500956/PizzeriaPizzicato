<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="pizzeria_pizzicato.model.Tilaus"%>
<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Juoma"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus> "
scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />
<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Tarjoilijan näkymä</title>


</head>
	<body class="body2">
	
	
	<%
	int ryhma= 1;
	int ryhma2= 2;
	int ryhma3= 4;
	int ryhma4= 5;
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("ryhma").equals(ryhma) || session.getAttribute("ryhma").equals(ryhma2) || session.getAttribute("ryhma").equals(ryhma3) || session.getAttribute("ryhma").equals(ryhma4)){
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

<%
response.setIntHeader("Refresh", 5);
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


		
		
		<h1>NOUDETTAVAT</h1>
		
		<table align="center" border="0">
		<tr>
		<%if(session.getAttribute("ryhma").equals(1)){ %>
		<td>
		<a href="listaaPizzat" class="btn btn-info" role="button">Takaisin</a>&nbsp;&nbsp;
		</td>
		<%} %>
		<td>
		<a href="listaaPizzatkokki" class="btn btn-info" role="button" style="margin-right:6px;">Kokin näkymä</a>
		</td>
		
		<td>
		<a href="listaaPizzatkuski" class="btn btn-info" role="button">Kuljettajan näkymä</a>
		</td>
		</tr>
		</table>
	
		 <div class="listaa-tilaukset">
		<form class="kuski" action="" method="post">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><div class="toiminnot2"><h4>Tilausnumero</h4></div></td>
			<td><div class="toiminnot2"><h4>Aika</h4></div></td>
			<td><div class="toiminnot2"><h4>Status</h4></div></td>
			<td><div class="toiminnot2"><h4>Tuote</h4></div></td>	
			<td><div class="toiminnot2"><h4>Lukumäärä</h4></div></td>	
			<td><div class="toiminnot2"><h4>Valkosipuli</h4></div></td>
			<td><div class="toiminnot2"><h4>Oregano</h4></div></td>
			<td><div class="toiminnot2"><h4>Osoite</h4></div></td>
			<td><div class="toiminnot2"><h4>Puhnro</h4></div></td>
		</tr>
		
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
				<%for(int j = 0; j < tilaukset.get(i).getTilatutTuotteet().size(); j++) {%>
			
	
					<tr>
					<%if(tilaukset.get(i).getStatusID()==3 && tilaukset.get(i).getOsoite().contains("nouto")){%>
						<td><%=tilaukset.get(i).getId() %></td>
						<td><%=tilaukset.get(i).getAika()%></td>
						<td><%=tilaukset.get(i).getStatusNimi()%></td>
						<td><%=tilaukset.get(i).getTilattuTuote(j).getTuote().getNimi() %></td>
						<td><%=tilaukset.get(i).getTilattuTuote(j).getLkm()%></td>
						<td><%if(tilaukset.get(i).getTilattuTuote(j).getvSipuli()==1){out.print("kyllä");}else{out.print("ei");} %></td>
						<td><%if(tilaukset.get(i).getTilattuTuote(j).getOregano()==1){out.print("kyllä");}else{out.print("ei");} %></td>
						<td><%=tilaukset.get(i).getOsoite()%></td>
						<td><%=tilaukset.get(i).getPuhnro() %></td>
						<% }%>
						</tr>				
					<% }%>
				<% } %>
				</table>
</form>
</div>
<div class="listaa-tilaukset2">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<p>Noudettujen tilauksien kuittaus</p>
		<tr>
			<td><h4>Tilausnumero</h4></td>
			<td><h4>Noudettu</h4></td>
				
		</tr>
		
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
			<%if(tilaukset.get(i).getStatusID()==3 && tilaukset.get(i).getOsoite().contains("nouto")){%>
			
					<tr>
					<td><div class="toiminnot2"><form class="kuski2" action="" method="post">
						<input type="hidden" name="toimitettu" value="<%=tilaukset.get(i).getId() %>"><%=tilaukset.get(i).getId() %></td>						
  						<td><input type="submit" class="btn btn-success btn-xs" value="Kyllä" />
						</form></div></td>
  						
						</tr>				
					<% }%>
				<% } %>
		</table>
		</div>
				
	</body>
</html>

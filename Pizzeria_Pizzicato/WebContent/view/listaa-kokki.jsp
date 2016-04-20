<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="pizzeria_pizzicato.model.Tilaus"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus> "
scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Tilaukset</title>


</head>
	<body>
	
	<iframe id="autopost"></iframe>
<script>
setInterval(function(){
  if (!document.version) document.version=1;
  document.version++;
  document.getElementById('autopost').src='poster.htm?v='+document.version;
},100000);
</script>
	
	<%
	int ryhma= 2;
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
		
		<h1>TILAUKSET</h1>
		

		
	
		<div class ="button"><a href="<%=response.encodeURL("listaaPizzat") %>">Palaa pizzalistaan</a></div><br>
		<form id="valmis" action="" name="valmis" method="post">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>Tilausnumero</h4></td>
			<td><h4>Aika</h4></td>
			<td><h4>Status</h4></td>
			<td><h4>Tuote</h4></td>	
			<td><h4>Lukumäärä</h4></td>
			<td><h4>Valkosipuli</h4></td>
			<td><h4>Oregano</h4></td>
			<td><h4>Valmis</h4></td>
			
		</tr>
			
		
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
				<%for(int j = 0; j < tilaukset.get(i).getTilatutTuotteet().size(); j++) {%>
					<tr>
						<td><%=tilaukset.get(i).getId() %></td>
						<td><%=tilaukset.get(i).getAika()%></td>
						<td><%=tilaukset.get(i).getStatusNimi() %></td>
						<td><%=tilaukset.get(i).getTilattuTuote(j).getTuote().getNimi() %></td>
						<td><%=tilaukset.get(i).getTilattuTuote(j).getLkm()%></td>
						<td><%if(tilaukset.get(i).getTilattuTuote(j).getvSipuli()==1){out.print("kyllä");}else{out.print("ei");} %></td>
						<td><%if(tilaukset.get(i).getTilattuTuote(j).getOregano()==1){out.print("kyllä");}else{out.print("ei");} %></td>
						<td><input type="checkbox" name="valmis" value="<%=tilaukset.get(i).getTilattuTuote(j).getTuote().getId() %>"></td>		
					</tr>
				<% } %>
				<% } %>
				
			
	
		</table>
 
</form>
<script>
  document.getElementById('valmis').checkbox();
</script>
		
		
	</body>
</html>

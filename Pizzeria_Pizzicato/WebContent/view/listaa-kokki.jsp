<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="pizzeria_pizzicato.model.Tilaus"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>



<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />

<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus> "
scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Kokin näkymä</title>


</head>
	<body>
		
		<h1>Kokin näkymä</h1>
		

		
	
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>Tilaus numero</h4></td>
			<td><h4>Aika</h4></td>
			<td><h4>Status</h4></td>
			<td><h4>Tuote</h4></td>
			<td><h4>Lukumäärä</h4></td>
			<td><h4>Tilaaja</h4></td>
			<td><h4>Status</h4> </td>
	
				
		</tr>
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
			<tr>
				<td><%=tilaukset.get(i).getId() %></td>
				<td><%=tilaukset.get(i).getAika()%></td>
				<td><%=tilaukset.get(i).getStatusNimi() %></td>
				<td><%=tilaukset.get(i).getTuoteNimi() %></td>
				<td><%=tilaukset.get(i).getLukumaara() %></td>
				<td><%=tilaukset.get(i).getKtunnus()%></td>
				
				
				<td><div class="toiminnot"> 
				<p><b id='boldStuff<%=tilaukset.get(i).getId() %>'>Ei paistettu</b> </p> 
				<input type='button' onclick='changeText()' value='Change Text'/>
				<script>
function changeText()
{
 document.getElementById('boldStuff'+<%=tilaukset.get(i).getId() %>).innerHTML = 'Paistettu';
}
</script>
				<a href="#"<%=tilaukset.get(i).getId()%>" class="button">
				Ei paistettu</a> 
							</div></td>	
			</tr>
			<% } %>
		</table><br>
 

		
		
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>

<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizza lista</title>
<link href="styles.css" rel="stylesheet" type="text/css">

</head>
	<body>

		<h1>Pizza lista</h1>
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			
			<td><h4>PIZZAT</h4></td>
			<td><h4>HINTA</h4></td>
			<td><h4>TOIMINNOT</h4></td>
				
		</tr>
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			<tr>
				
				<td><div class="pizzat"><%=pizzat.get(i).getNimi()%></div></td>
				<td><div class="pizzat"><%=pizzat.get(i).getHinta()%></div></td>
				<td><div class="toiminnot"> 
				<a href="muokkaa-pizza?id=<%=pizzat.get(i).getId()%>" class="button">
				Muokkaa pizzaa
				</a>		
				<a href="poista-pizza?id=<%=pizzat.get(i).getId()%>&id2=<%=pizzat.get(i).getNimi()%>" class="button">
				Poista pizza
				</a></div>
				</td>								
			</tr>
			<% } %>
		</table><br>
		<a href="lisaa-pizza" class="button">Lisää pizza</a>
		
	</body>
</html>
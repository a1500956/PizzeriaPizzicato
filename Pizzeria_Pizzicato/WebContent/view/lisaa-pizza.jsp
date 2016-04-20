<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="kaikkitaytteet" type="java.util.ArrayList<Tayte>" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">

<title>Lis�� pizza</title>
</head>
<body>

<%
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
	<h1>Lis�� pizza</h1>
		
	<form action="" method="post">
		<table class="lisaa-pizza" align=center>

			<tr>
				<td>Pizzan nimi:</td>
				<td><input type="text" value="" name="nimi" size="20"
					pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkki�" />
				</td>
			</tr>
			<tr>
				<td>Pizzan hinta:</td>
				<td><input type="number" step=0.01 value="" name="hinta"
					size="5" min="6" max="100" required
					title="Arvon tulee olla v�lilt� 0.0 ja 100.0" />&euro;</td>
					<p>${message2}</p>
					<c:remove var="message2" scope="session" /> 
			</tr>
			<tr>
				<td>Pizza n�kyy menussa:</td>
				<td><input type="radio" name="nakyy" value="1"
					checked="checked"> Kyll� <input type="radio" name="nakyy"
					value="0"> Ei</td>
			</tr>
			<tr>
				<td>T�ytteet:</td>
				<td>
				<%for(int i = 0; i<kaikkitaytteet.size(); i++){ %>
				<input type="checkbox" name="tayte" value="<%=kaikkitaytteet.get(i).getTayte_id()%>" /><%=kaikkitaytteet.get(i).getTayte_nimi()%><br />	
				<%}%>
				</td>
				
			<tr>
				<td><br>
				<div class="button">
						<a href="<%=response.encodeURL("listaaPizzat") %>">Palaa pizzalistaan</a>
					</div></td>
				<td><br> <input type="submit" name="submit-button"
					class="submit-button" value="Tallenna" /></td>
			</tr>
		</table>
	</form>


</body>
</html>

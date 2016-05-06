<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Tayte"%>



<jsp:useBean id="valittuN" type="java.lang.String" scope="request" />
<jsp:useBean id="valittuN_en" type="java.lang.String" scope="request" />
<jsp:useBean id="valittuH" type="java.lang.String" scope="request" />

<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Muokkaa täytteitä</title>
</head>
<body class="body2">
<%
//allow access only if session exists
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
<h1>Muokkaa täytettä</h1>
		<form action="" method="post">
			<table class="lisaa-pizza" align=center>
				<tr>
					<td>Täytteen nimi suomeksi:</td>
					<td><input type="text" value="<%=valittuN%>" name="nimi" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkkiä" />
					</td>					
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
					<td>Täytteen nimi englanniksi:</td>
					<td><input type="text" value="<%=valittuN_en%>" name="nimi_en" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkkiä" />
					</td>					
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
					<td>Täytteen hinta/kg:</td>
					<td><input type="number" value=<%=valittuH%> step=0.01 name="hinta" size="5" min="0" max="100" required title="Arvon tulee olla väliltä 0.0 ja 100.0" />&euro;
					</td>					
				</tr>
				<tr>
				<td><p> </p></td>
				<td><br>
				<a href="listaa-taytteet" class="btn btn-info btn-sm" role="button">Takaisin</a>&nbsp;&nbsp;
				<input type="submit" class="btn btn-success btn-md" value="Tallenna" /></td>
			</tr>		
			</table>
			</form>
</body>
</html>
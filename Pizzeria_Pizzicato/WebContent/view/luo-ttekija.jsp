<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title>Lis�� ty�ntekij�</title>
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


 		 <article>
 <br>
 <br>
 <br><br>
  <h1 class="pizzaotsikko">REKISTER�I TY�NTEKIJ�</h1>
  <br>
  <br>
  
  <p>${message}</p>
<c:remove var="message" scope="session" /> 
    
			
<form action="" method="post">
			<table class="lisaa-pizza" align=center>
				
				<tr>
					<td>K�ytt�j�nimi:</td>
					<td><input type="text" value="" name="Knimi" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkki�" />
					</td>					
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
					<td>Etunimi:</td>
					<td><input type="text" value="" name="Enimi" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkki�" />
					</td>					
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
					<td>Sukunimi:</td>
					<td><input type="text" value="" name="Snimi" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkki�" />
					</td>					
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
				<td>Katuosoite:</td>
					<td><input type="text" value="" name="KatuOs" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkki�" />
				</td>	
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
				<td>Puhelinnumero:</td>
					<td><input type="text" value="" name="Puhno" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkki�" />
				</td>	
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
				<td>S�hk�posti:</td>
					<td><input type="email" value="" name="Sposti" size="20" pattern=".{6,20}" required title="Pituuden tulee olla 6-20 merkki�" />
				</td>
				</tr>
				<tr>
			<td><p></p> </td>
			</tr>
				<tr>
				<td>Salasana:</td>
					<td><input type="text" value="" name="SS" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkki�" />
				</td>
				</tr>
				<tr>
				
				<td><br>
				<a href="<%=response.encodeURL("listaaTyontekijat") %>" class="btn btn-info btn-sm" role="button">Takaisin</a>&nbsp;&nbsp;
  				
				</td>
				<td><br> <input type="submit" class="btn btn-success btn-md" value="Tallenna" /></td>
			</tr>
			</table>
			</form>	
    </section>
    
   
  <!-- end .content --></article>


</body>
</html>
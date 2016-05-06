<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">

<title>Lisää työntekijä</title>
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
  <h1 class="pizzaotsikko"><br>REKISTERÖI TYÖNTEKIJÄ</h1>
  
  <p>${message}</p>
<c:remove var="message" scope="session" /> 
    
			
<form action="" method="post">
			<table class="lisaa-pizza" align=center>
				
				<tr>
					<td>Käyttäjänimi:</td>
					<td><input type="text" value="" name="Knimi" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkkiä" />
					</td>					
				</tr>
				<tr>
					<td>Etunimi:</td>
					<td><input type="text" value="" name="Enimi" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
					</td>					
				</tr>
				<tr>
					<td>Sukunimi:</td>
					<td><input type="text" value="" name="Snimi" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
					</td>					
				</tr>
				<tr>
				<td>Katuosoite:</td>
					<td><input type="text" value="" name="KatuOs" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
				</td>	
				</tr>
				<tr>
				<td>Puhelinnumero:</td>
					<td><input type="text" value="" name="Puhno" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
				</td>	
				</tr>
				<tr>
				<td>Sähköposti:</td>
					<td><input type="text" value="" name="Sposti" size="20" pattern=".{6,20}" required title="Pituuden tulee olla 6-20 merkkiä" />
				</td>
				</tr>
				<tr>
				<td>Salasana:</td>
					<td><input type="text" value="" name="SS" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
				</td>
				</tr>
				<tr>
					<td><br><div class ="button"><a href="listaaPizzat">Palaa pizzalistaan</a></div></td>
					<td><br>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>	
    </section>
    
   
  <!-- end .content --></article>


</body>
</html>
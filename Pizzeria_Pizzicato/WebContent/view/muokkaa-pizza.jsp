<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>

<jsp:useBean id="valittuN" type="java.lang.String" scope="request" />
<jsp:useBean id="valittuH" type="java.lang.String" scope="request" />
<jsp:useBean id="taytteet" type="java.util.ArrayList<String>"
	scope="request" />
<jsp:useBean id="kaikkitaytteet" type="java.util.ArrayList<Tayte>" scope="request" />
<jsp:useBean id="nakyykovaiei" type="java.lang.Integer" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Muokkaa pizza</title>
</head>
<body>
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
	<h1>Muokkaa pizzaa</h1>
	<form action="" method="post">
		<table class="lisaa-pizza" align=center>
			<tr>
				<td>Pizzan nimi:</td>
				<td><input type="text" value="<%=valittuN%>" name="nimi"
					size="20" pattern=".{4,20}" required
					title="Pituuden tulee olla 4-20 merkkiä" /></td>
			</tr>
			<tr>
				<td>Pizzan hinta:</td>
				<td><input type="number" value=<%=valittuH%> step=0.01
					name="hinta" size="5" min="6" max="100" required
					title="Arvon tulee olla väliltä 0.0 ja 100.0" />&euro;</td>
			</tr>
			<tr>
				<td>Pizza näkyy menussa:</td>
				<td><input type="radio" name="nakyy" value="1"
					<%if(nakyykovaiei==1) {out.print("checked=\"checked\"");} %>>
					Kyllä <input type="radio" name="nakyy" value="0"
					<%if(nakyykovaiei==0) {out.print("checked=\"checked\"");} %>>
					Ei</td>
			</tr>
			<tr>
				<td>Täytteet:</td>
				<td><br>
				<br>
				<td>
				<%for(int i = 0; i<kaikkitaytteet.size(); i++){ %>
				
				
				<input type="checkbox" name="tayte"
					<%if(taytteet.contains(kaikkitaytteet.get(i).getTayte_nimi())) {out.print("checked=\"checked\"");} %>
					value="<%=kaikkitaytteet.get(i).getTayte_id() %>" /><%=kaikkitaytteet.get(i).getTayte_nimi() %><br />
					<%}%>
					</td>
			</tr>
			<tr>
				<td><br>
				<div class="button">
						<a href="listaaPizzat">Palaa pizzalistaan</a>
					</div></td>
				<td><br> <input type="submit" name="submit-button"
					class="submit-button" value="Tallenna" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
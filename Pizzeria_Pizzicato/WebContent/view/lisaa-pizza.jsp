<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">

<title>Lisää pizza</title>
</head>
<body>

<%
//allow access only if session exists
String userName = null;
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
	<h1>Lisää pizza</h1>
		
	<form action="" method="post">
		<table class="lisaa-pizza" align=center>

			<tr>
				<td>Pizzan nimi:</td>
				<td><input type="text" value="" name="nimi" size="20"
					pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkkiä" />
				</td>
			</tr>
			<tr>
				<td>Pizzan hinta:</td>
				<td><input type="number" step=0.01 value="" name="hinta"
					size="5" min="6" max="100" required
					title="Arvon tulee olla väliltä 0.0 ja 100.0" />&euro;</td>
			</tr>
			<tr>
				<td>Pizza näkyy menussa:</td>
				<td><input type="radio" name="nakyy" value="1"
					checked="checked"> Kyllä <input type="radio" name="nakyy"
					value="0"> Ei</td>
			</tr>
			<tr>
				<td>Täytteet:</td>
				<td><br>
				<br>
				<input type="checkbox" name="tayte" value="2" />Tomaattikastike<br />
					<input type="checkbox" name="tayte" value="3" />Juusto<br /> <input
					type="checkbox" name="tayte" value="4" />Herkkusieni<br /> <input
					type="checkbox" name="tayte" value="5" />Sipuli<br /> <input
					type="checkbox" name="tayte" value="6" />Oliivi<br /> <input
					type="checkbox" name="tayte" value="7" />Pinaatti<br /> <input
					type="checkbox" name="tayte" value="8" />Tonnikala<br /> <input
					type="checkbox" name="tayte" value="9" />Katkarapu<br /> <input
					type="checkbox" name="tayte" value="10" />Simpukka<br /> <input
					type="checkbox" name="tayte" value="11" />Kinkku<br /> <input
					type="checkbox" name="tayte" value="12" />Salami<br /> <input
					type="checkbox" name="tayte" value="13" />Pepperoni<br /> <input
					type="checkbox" name="tayte" value="14" />Jalopeno<br /></td>
			</tr>
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
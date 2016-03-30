<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">

<title>Lis�� t�yte</title>
</head>
<body>
<h1>Lis�� t�yte</h1>
		<form action="" method="post">
			<table class="lisaa-pizza" align=center>
				
				<tr>
					<td>T�ytteen nimi:</td>
					<td><input type="text" value="" name="nimi" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkki�" />
					</td>					
				</tr>
				<tr>
					<td>T�ytteen hinta/kg:</td>
					<td><input type="number" step=0.01 value="" name="hinta" size="5" min="0" max="100" required title="Arvon tulee olla v�lilt� 0.0 ja 100.0" />&euro;
					</td>					
				</tr>
				<tr>
					<td><br><div class ="button"><a href="listaa-taytteet">Palaa t�ytelistaan</a></div></td>
					<td><br>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
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
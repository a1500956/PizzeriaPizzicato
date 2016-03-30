<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Tayte"%>



<jsp:useBean id="valittuN" type="java.lang.String" scope="request" />
<jsp:useBean id="valittuH" type="java.lang.String" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Muokkaa täytteitä</title>
</head>
<body>
<h1>Muokkaa täytettä</h1>
		<form action="" method="post">
			<table class="lisaa-pizza" align=center>
				<tr>
					<td>Täytteen nimi:</td>
					<td><input type="text" value="<%=valittuN%>" name="nimi" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkkiä" />
					</td>					
				</tr>
				<tr>
					<td>Täytteen hinta/kg:</td>
					<td><input type="number" value=<%=valittuH%> step=0.01 name="hinta" size="5" min="0" max="100" required title="Arvon tulee olla väliltä 0.0 ja 100.0" />&euro;
					</td>					
				</tr>
					<td><br><div class ="button"><a href="listaa-taytteet">Palaa täytelistaan</a></div></td>
					<td><br>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Syntax Terror">

<link href="styles.css" rel="stylesheet" type="text/css">
<title>Lis�� pizza</title>
</head>

<body>
	<h1>Lis�� pizza</h1>
		<form method="post">
			<table class="lisaa-pizza" align=center>
				
				<tr>
					<td>Pizzan nimi:</td>
					<td><input type="text" value="" name="nimi" size="20" required />
					</td>					
				</tr>
				<tr>
					<td>Pizzan hinta:</td>
					<td><input type="text" value="" name="hinta" size="5" required />
					</td>					
				</tr>
				<tr>
					<td>Pizzan n�kyvyys:</td>
					<td><input type="radio" name="nakyy" value="1" checked="checked"> Kyll�
						<input type="radio" name="nakyy" value="0"> Ei
					</td>					
				</tr>
				<tr>
					<td><br><div class ="button"><a href="listaaPizzat">Peruuta</a></div></td>
					<td><br>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="Syntax Terror">

<link href="styles.css" rel="stylesheet" type="text/css">
<title>Lisää pizza</title>
</head>

<body>
	<h1>Lisää pizza</h1>
		<form action="checkboxes" method="post">
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
					<td>Pizzan näkyvyys:</td>
					<td><input type="text" value="" name="nakyy" size="1" required />
				</td>					
				</tr>
				<tr>
					<td>Täytteet:</td>
					<td><br><br><input type="checkbox" name="tayte" value="2"/>Tomaattikastike<br/>
              		<input type="checkbox" name="tayte" value="3"/>Juusto<br/>
					<input type="checkbox" name="tayte" value="4"/>Herkkusieni<br/>
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
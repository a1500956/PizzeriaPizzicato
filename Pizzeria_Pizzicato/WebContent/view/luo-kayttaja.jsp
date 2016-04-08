<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


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
					<td><br><div class ="button"><a href="listaa-taytteet">Palaa etusivulle</a></div></td>
					<td><br>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>


</body>
</html>
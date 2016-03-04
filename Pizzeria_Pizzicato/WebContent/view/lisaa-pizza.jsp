<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Lisää pizza</h1>
		<form action="" method="post">
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
					<td>Pizza näkyy menussa:</td>
					<td><input type="radio" name="nakyy" value="1" checked="checked"> Kyllä
						<input type="radio" name="nakyy" value="0"> Ei
				</td>					
				</tr>
				<tr>
					<td>Täytteet:</td>
					<td><br><br><input type="checkbox" name="tayte" value="2"/>Tomaattikastike<br/>
              		<input type="checkbox" name="tayte" value="3"/>Juusto<br/>
					<input type="checkbox" name="tayte" value="4"/>Herkkusieni<br/>
					<input type="checkbox" name="tayte" value="5"/>Sipuli<br/>
					<input type="checkbox" name="tayte" value="6"/>Oliivi<br/>
					<input type="checkbox" name="tayte" value="7"/>Pinaatti<br/>
					<input type="checkbox" name="tayte" value="8"/>Tonnikala<br/>
					<input type="checkbox" name="tayte" value="9"/>Katkarapu<br/>
					<input type="checkbox" name="tayte" value="10"/>Simpukka<br/>
					<input type="checkbox" name="tayte" value="11"/>Kinkku<br/>
					<input type="checkbox" name="tayte" value="12"/>Salami<br/>
					<input type="checkbox" name="tayte" value="13"/>Pepperoni<br/>
					<input type="checkbox" name="tayte" value="14"/>Jalopeno<br/>
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
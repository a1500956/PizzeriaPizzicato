<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.PizzaTayte"%>

<jsp:useBean id="valittuN" type="java.lang.String" scope="request" />
<jsp:useBean id="valittuH" type="java.lang.String" scope="request" />
<jsp:useBean id="taytteet" type="java.util.ArrayList<PizzaTayte>"
	scope="request" />
<jsp:useBean id="nakyykovaiei" type="java.lang.Integer" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Muokkaa pizza</title>
</head>
<body>
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
					name="hinta" size="5" min="0" max="100" required
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
				<input type="checkbox" name="tayte"
					<%if(taytteet.contains("tomaattikastike")) {out.print("checked=\"checked\"");} %>
					value="2" />Tomaattikastike<br /> <input type="checkbox"
					name="tayte"
					<%if(taytteet.contains("juusto")) {out.print("checked=\"checked\"");} %>
					value="3" />Juusto<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("herkkusieni")) {out.print("checked=\"checked\"");} %>
					value="4" />Herkkusieni<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("sipuli")) {out.print("checked=\"checked\"");} %>
					value="5" />Sipuli<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("oliivi")) {out.print("checked=\"checked\"");} %>
					value="6" />Oliivi<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("pinaatti")) {out.print("checked=\"checked\"");} %>
					value="7" />Pinaatti<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("tonnikala")) {out.print("checked=\"checked\"");} %>
					value="8" />Tonnikala<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("katkarapu")) {out.print("checked=\"checked\"");} %>
					value="9" />Katkarapu<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("simpukka")) {out.print("checked=\"checked\"");} %>
					value="10" />Simpukka<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("kinkku")) {out.print("checked=\"checked\"");} %>
					value="11" />Kinkku<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("salami")) {out.print("checked=\"checked\"");} %>
					value="12" />Salami<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("pepperoni")) {out.print("checked=\"checked\"");} %>
					value="13" />Pepperoni<br /> <input type="checkbox" name="tayte"
					<%if(taytteet.contains("jalopeno")) {out.print("checked=\"checked\"");} %>
					value="14" />Jalopeno<br /></td>
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
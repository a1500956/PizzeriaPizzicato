<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="valittuNimi" type="java.lang.String" scope="request" />
<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Poista pizza</title>

</head>
<body class="body2">
	<h3 class="hclass3">
		Oletko varma, että haluat poistaa pizzan: "
		<%=valittuNimi%>
		"
	</h3>
	<div class="lomake">

		<form method="post">
			<table class="" align=center>

				<tr>
					<td><br><a href="listaaPizzat" class="btn btn-info" role="button">Peruuta</a>&nbsp;&nbsp;</td>

					<td><br><input type="submit" class="btn btn-danger" value="Poista"></td>
					
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
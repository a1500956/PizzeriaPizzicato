<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Tuote"%>
<%@ page import="pizzeria_pizzicato.model.Tilaus"%>
<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="pizzeria_pizzicato.model.TilattuTuote"%>
<%@ page import="pizzeria_pizzicato.model.dao.TayteDAO"%>
<%@ page import="pizzeria_pizzicato.model.dao.TuoteDAO"%>
<%@ page import="java.text.NumberFormat" %>
<%@page import="java.util.ArrayList" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus> "
scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />
<jsp:useBean id="tilatutTuotteet" type="java.util.ArrayList<TilattuTuote> "
scope="request" />

<html class="html2">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Tilaukset</title>

<SCRIPT TYPE="text/javascript">  function popup(mylink, windowname) { if (! window.focus)return true; var href; if (typeof(mylink) == 'string') href=mylink; else href=mylink.href; window.open(href, windowname, 'width=600,height=800,left=50,top=100scrollbars=yes'); return false; } </SCRIPT>


</head>
	<body class="body2">
	
	
	<%

	int ryhma= 1;
	int ryhma2= 2;
	int ryhma3= 4;
	int ryhma4= 5;

	String userName = null;
	//allow access only if session exists

	if(session.getAttribute("ryhma").equals(ryhma) || session.getAttribute("ryhma").equals(ryhma2) || session.getAttribute("ryhma").equals(ryhma3) || session.getAttribute("ryhma").equals(ryhma4)){

		userName = (String) session.getAttribute("kayttaja");
		
	}else{ response.sendRedirect("pizzaMenu");
}	
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("kayttaja")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
   sessionID = session.getId();
}
%>

<%
response.setIntHeader("Refresh", 5);
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
		
		<h1>KOKIN NÄKYMÄ</h1>
		
		<table align="center" border="0">
		<tr>
		<%if(session.getAttribute("ryhma").equals(1)){ %>
		<td>
		<a href="listaaPizzat" class="btn btn-info" role="button">Takaisin</a>&nbsp;&nbsp;
		</td>
		<%} %>
		<td>
		<a href="listaaTilauksettarjoilija" class="btn btn-info" role="button" style="margin-right:6px;">Tarjoilijan näkymä</a>
		</td>
		<td>
		<a href="listaaPizzatkuski" class="btn btn-info" role="button">Kuskin näkymä</a>
		<a href="kokinLista2" class="btn btn-info" role="button" onClick="return popup(this, 'notes')">Pizzalista</a>
		</td>
		</tr>
		</table>
	
		
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><div class="toiminnot2"><h4>Tilausnumero</h4></div></td>
			<td><div class="toiminnot2"><h4>Lukumäärä</h4></div></td>
			<td><div class="toiminnot2"><h4>Aika</h4></div></td>
			<td><div class="toiminnot2"><h4>Status</h4></div></td>
			<td><div class="toiminnot2"><h4>Tuote</h4></div></td>
			<td><div class="toiminnot2"><h4>Lisätäytteet</h4></div></td>			
			<td><div class="toiminnot2"><h4>Valkosipuli</h4></div></td>
			<td><div class="toiminnot2"><h4>Oregano</h4></div></td>
			<td><div class="toiminnot2"><h4>Tehty</h4></div></td>	
		</tr>
		
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
				<%for(int j = 0; j < tilaukset.get(i).getTilatutTuotteet().size(); j++) {%>
			
					<% if(tilaukset.get(i).getTilattuTuote(j).getStatus()==1){%>
	
					<tr>
						<td><div class="toiminnot2"><%=tilaukset.get(i).getId() %></div></td>
						<td><div class="toiminnot2"><%=tilaukset.get(i).getTilattuTuote(j).getLkm() %></div></td>
						<td><div class="toiminnot2"><%=tilaukset.get(i).getAika()%></div></td>
						<td><div class="toiminnot2"><%if(tilaukset.get(i).getTilattuTuote(j).getStatus()==1){out.print("Tekemätön");}else{out.print("Tehty");} %></div></td>
						<td><div class="toiminnot2"><%=tilaukset.get(i).getTilattuTuote(j).getTuote().getNimi() %></div></td>
						<%TuoteDAO TUDAO= new TuoteDAO();
						if(TUDAO.pizzaVaiJuoma(tilaukset.get(i).getTilattuTuote(j).getTuote().getId())){
							if(tilaukset.get(i).getTilattuTuote(j).getLisataytteet().isEmpty()){
								 %><td></td><%
							}else{
								
								%><td>&nbsp;&nbsp;<% 
								for(int k=0; k<tilaukset.get(i).getTilattuTuote(j).getLisataytteet().size(); k++){%>
									<%=tilaukset.get(i).getTilattuTuote(j).getLisatayte(k).getTayte_nimi()%>&nbsp;&nbsp;
							<% }%></td>
							<%} %>
							
							
						<%}else{ %><td></td><%} %>
						
						<td><div class="toiminnot2"><%if(tilaukset.get(i).getTilattuTuote(j).getvSipuli()==1){out.print("kyllä");}else{out.print("ei");} %></div></td>
						<td><div class="toiminnot2"><%if(tilaukset.get(i).getTilattuTuote(j).getOregano()==1){out.print("kyllä");}else{out.print("ei");} %></div></td>
						<td><div class="toiminnot2"><form class="" action="" method="post"><input type="hidden" name="valmis" value="<%=tilaukset.get(i).getTilattuTuote(j).getTilausId() %>"/>
						<input type="hidden" name="valmis2" value="<%=tilaukset.get(i).getTilattuTuote(j).getTilausRivi() %>"/>
						<input type="hidden" name="valmis3" value="<%=tilaukset.get(i).getTilattuTuote(j).getTuoteId() %>"/>					
  						<input type="submit" class="btn btn-success btn-xs" value="Kyllä" /></form></div></td>
						</tr>				
					
				<% } %>
			<% } %>	
			<% } %>	
					
		</table>


	
	</body>
</html>

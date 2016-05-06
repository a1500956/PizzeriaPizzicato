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


</head>
	<body class="body2">
	
	
	<%

	int ryhma= 1;
	int ryhma2= 2;
	int ryhma3= 4;

	String userName = null;
	//allow access only if session exists

	if(session.getAttribute("ryhma").equals(ryhma) || session.getAttribute("ryhma").equals(ryhma2) || session.getAttribute("ryhma").equals(ryhma3)){

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

		<table align="center" border="0">
		<tr>
		<%if(session.getAttribute("ryhma").equals(1)){ %>
		<td>
		<form action="listaaPizzat">
   	 				<input type="submit" value="Takaisin">
		</form>
		</td>
		<%} %>
		<td>
		<form action="listaaPizzatkuski">
   	 				<input type="submit" value="Kuskin näkymä">
		</form>
		</td>
		</tr>
		</table>
		
		 <p>Pizzalista</p> 
		<tr><td colspan="2"><input type="radio" name="lista" value="Piilossa" checked>  <label for="lista" style="color:black;">Piilossa</label>
		<div><input type="radio" id="koti" name="lista" value="Näkyvissä" required><label for="lista" style="color:black;">Näkyvissä</label>
		<br><br>
		<div class="reveal-if-active">
  		<h1>PIZZALISTA</h1>
	
		
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			
			<td><h4>PIZZAT</h4></td>
			
			<td><h4>TÄYTTEET</h4></td>
			
				
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			<tr>
				
				<td><div class="pizzat"><%=pizzat.get(i).getNimi()%></div></td>
				
				<td><div class="taytteet"><%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>
												 </div></td>
					
			</tr>
			<% } %>
</table>		
</div>
</div>
</table>		  
  
 
  

<script>
var FormStuff = {
		  
		  init: function() {
		    this.applyConditionalRequired();
		    this.bindUIActions();
		  },
		  
		  bindUIActions: function() {
		    $("input[type='radio'], input[type='checkbox']").on("change", this.applyConditionalRequired);
		  },
		  
		  applyConditionalRequired: function() {
		  	
		    $(".require-if-active").each(function() {
		      var el = $(this);
		      if ($(el.data("require-pair")).is(":checked")) {
		        el.prop("required", true);
		      } else {
		        el.prop("required", false);
		      }
		    });
		    
		  }
		  
		};

		FormStuff.init();
</script>
		
		<h1>KOKIN NÄKYMÄ</h1>
	
		
		
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>Tilausnumero</h4></td>
			<td><h4>Lukumäärä</h4></td>
			<td><h4>Aika</h4></td>
			<td><h4>Status</h4></td>
			<td><h4>Tuote</h4></td>
			<td><h4>Lisätäytteet</h4></td>			
			<td><h4>Valkosipuli</h4></td>
			<td><h4>Oregano</h4></td>
			<td><h4>Tehty</h4></td>	
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

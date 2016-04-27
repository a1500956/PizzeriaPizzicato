<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Tuote"%>
<%@ page import="pizzeria_pizzicato.model.Tilaus"%>
<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="java.text.NumberFormat" %>
<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus> "
scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Tilaukset</title>


</head>
	<body>
	
	
	<%
	int ryhma= 2;
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("ryhma").equals(ryhma)){
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
		
		<h1>TILAUKSET</h1>
	
		 <div class="listaa-tilaukset">
		<form class="kokki" action="" method="post">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			<td><h4>Tilausnumero</h4></td>
			<td><h4>Aika</h4></td>
			<td><h4>Status</h4></td>
			<td><h4>Tuote</h4></td>
			<td><h4>Täytteet</h4></td>			
			<td><h4>Lukumäärä</h4></td>
			<td><h4>Valkosipuli</h4></td>
			<td><h4>Oregano</h4></td>		
		</tr>
		
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
				<%for(int j = 0; j < tilaukset.get(i).getTilatutTuotteet().size(); j++) {%>
			
	
					<tr>
						<td><%=tilaukset.get(i).getId() %></td>
						<td><%=tilaukset.get(i).getAika()%></td>
						<td><%=tilaukset.get(i).getStatusNimi() %></td>
						<td><%=tilaukset.get(i).getTilattuTuote(j).getTuote().getNimi() %></td>
						<%Tuote t = tilaukset.get(i).getTilattuTuote(j).getTuote(); Pizza p = null;
						if(Tuote.class.isAssignableFrom(p.getClass())){ %>
							<%p = (Pizza) tilaukset.get(i).getTilattuTuote(j).getTuote();%>
								<td><%for(int k = 0; k<p.getTaytteet().size(); k++){%>
								<%=p.getTayte(k).getTayte_nimi()%> ,
								</td>
							<%} %>
						<%}else{ %><td>asdf</td><%} %>
						<td><%=tilaukset.get(i).getTilattuTuote(j).getLkm()%></td>
						<td><%if(tilaukset.get(i).getTilattuTuote(j).getvSipuli()==1){out.print("kyllä");}else{out.print("ei");} %></td>
						<td><%if(tilaukset.get(i).getTilattuTuote(j).getOregano()==1){out.print("kyllä");}else{out.print("ei");} %></td>
						</tr>				
					
				<% } %>
			<% } %>	
					
		</table>
</form>
</div>	
	<div class="listaa-tilaukset2">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<p>Valmiiden tilauksien kuittaus</p>
		<tr>
			<td><h4>Tilausnumero</h4></td>
			<td><h4>Valmis</h4></td>
				
		</tr>
		
			<%for(int i = 0; i < tilaukset.size(); i++) {%>
					<tr>
					<td><form class="kokki2" action="" method="post">
						<input type="hidden" name="valmis" value="<%=tilaukset.get(i).getId() %>"><%=tilaukset.get(i).getId() %></td>						
  						<td><input type="submit" value="Kyllä">
						</form></td>
  						
						</tr>				
					
				<% } %>
		</table>
		</div>
	</body>
</html>

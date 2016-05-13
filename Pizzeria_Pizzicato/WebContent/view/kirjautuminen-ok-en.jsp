<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Juoma"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="pizzeria_pizzicato.model.Tuote"%>
<%@ page import="pizzeria_pizzicato.model.Ostoskori"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.NumberFormat" %>

<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>
<%
Ostoskori ostoskori = new Ostoskori();
ostoskori = (Ostoskori) session.getAttribute("ostoskori");
%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />
<jsp:useBean id="pizzaFantasia" type="java.util.ArrayList<Pizza> "
scope="request" />
<jsp:useBean id="kaikkitaytteet" type="java.util.ArrayList<Tayte> "
scope="request" />
<jsp:useBean id="fantasiaTayteValintaLista" type="java.util.ArrayList<Tayte> "
scope="request" />
<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma> "
scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizzeria Pizzicato</title>

<link href="puhelin.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 0px)
and (max-width: 770px)" >

<link href="etusivu.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 771px)">
<style type="text/css">



</style><!--[if lt IE 9]>




<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]--></head>

	<body>

<%
//allow access only if session exists
String user =null;
if(session.getAttribute("kayttaja")==null){
	response.sendRedirect("pizzaMenu");
}
else user = (String) session.getAttribute("kayttaja");
String userName = null;
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


<div class="container">
<nav class=isoruutu>
<a href="/Pizzeria_Pizzicato/kirjautuminenOk"> <img  src="Kuvia/FI_lippu.png" alt="suomeksi" id="flag"  /></a>
<img class="pizzamies" src="Kuvia/pizzamies.png" id="logo"/>
<div class="nav2">
<div class="nav1">
 <h4 class="h4c">Pizzeria Pizzicato<br> Meilahdentie 1, 00210 Helsinki.<br> Phone: (09) 300 300 30</h4>
 <h6 class="h6c">Open Mon-Thu 11-21, Fri-Sat 12-23, Sun 12-21</h6>
 </div>
 <div class="ostos">
<a href="/Pizzeria_Pizzicato/vahvistaTilausEn"><img src="Kuvia/icon-cart.png" alt="X" style="width:20%; height:20%; padding-right:2px"/>Cart(<%=ostoskori.getMaara()%>)</a>
 	<form action="<%=response.encodeURL("uloskirjautuminen") %>" method="post">
      <div class="loginrow2">
      <h3>Welcome <%=userName %>! </h3><input type="submit" value="Sign out" >
      </div>
    </form>
</div>
 </div>
</nav>

  <article>
  <br> <br>
 <br>
 <br>
 <div class="column row">

<ul class="tabs" data-tabs id="example-tabs" style = "list-style-type: none;">
<li class="tabs-title is-active"><a href="#panel1" aria-selected="true">Pizzas</a></li>
<li class="tabs-title"><a href="#panel2">Drinks</a></li>
</ul>
<div class="tabs-content" data-tabs-content="example-tabs">
<div class="tabs-panel is-active" id="panel1">

<div class="pizzat">

  <h1 class="pizzaotsikko">PIZZA MENU</h1>
  
    <section>
   
    <span class="pizzalista2">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>	
			<th>PIZZAS</th>
			<th>PRICE</th>
			<th> </th>
			<!--  <th>TOIMINNOT</th>-->
				
		</tr>
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			
			<tr>
				
				<td><div class="pizzat"><%out.print(i+1);%>. <b><%=pizzat.get(i).getNimi()%></b></div></td>
				<td><div class="pizzat2"><%=nf.format(pizzat.get(i).getHinta())%>€ </div></td>
				<td  rowspan="2" >
				<form class="postii" method="post">
					<input class="mauste" type="checkbox" name="oregano" value="1">Oregano <br>
				 	<input class="mauste" type="checkbox" name="vSipuli" value="1">Garlic
					<input type="hidden" name="pizzaID" value="<%=pizzat.get(i).getId()%>">
					<td  rowspan="2" style="padding-left: 10px; vertical-align: top;">
					<select name="maara">
				 		<%for(int n=0; n<10;n++){%>
				 			<option value="<%=n+1%>"><%=n+1%></option>
				 		<%}%>
					</select>Pcs 
					<input  class="koriin" type="submit" value="Add to Cart" >
				</form></td>			
			</tr>
			<tr><td><div class="taytteet"> <%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi_en()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi_en()%>
												 
			</div></td></tr>
			<% } %>
			
		<tr>
		<th><br>FANTASY PIZZAS</th>
		<th><br></th>
		<th></th>


			
			</tr>
				
			<%for(int i = 0; i < pizzaFantasia.size(); i++) {%>
			<%int taytemaara = i+2; %>
				<tr>
				
				<td><div class="pizzat"><%out.print(i+(pizzat.size()+1));%>. <b><%=pizzaFantasia.get(i).getNimi()%></b></div></td>
				<td  rowspan="2" style= "vertical-align: top;"><div class="pizzat2"><%=nf.format(pizzaFantasia.get(i).getHinta())%>€ </div></td>
				<td  rowspan="2" style= "vertical-align: top;">
				<form class="mauste2" method="post">
				<input class="mauste" type="checkbox" name="oregano" value="1">Oregano <br>
				<input class="mauste" type="checkbox" name="vSipuli" value="1">Garlic
				<input type="hidden" name="pizzaID" value="<%=pizzaFantasia.get(i).getId()%>">
				<td  rowspan="2" style="padding-left: 10px; vertical-align: top;">
				<select name="maara">
				 	<%for(int n=0; n<10;n++){%>
				 		<option value="<%=n+1%>"><%=n+1%></option>
				 	<%}%>
				 </select>Pcs
				<input type="submit" value="Add to Cart">
				</td>
				</tr>
				
			<tr><td><div class="taytteet"> <%int j=0; for(j = 0; j<pizzaFantasia.get(i).getTaytteet().size()-1;j++) {%>

												 <%= pizzaFantasia.get(i).getTaytteet().get(j).getTayte_nimi_en()%>, 
												<%  }%>
												 <%=pizzaFantasia.get(i).getTaytteet().get(j).getTayte_nimi_en()%> + <%=(i +2)%> toppings of your choice. <br>
												 
				
				
											<%int k=0; for(k=0; k<taytemaara; k++) {%>
												<select class="lisatayteValikko" name="lisatayte">
												<option selected disabled>Topping <%=k+1%></option>
													<%for(int l=0; l<fantasiaTayteValintaLista.size();l++){ %>
														 <option value=<%=fantasiaTayteValintaLista.get(l).getTayte_id() %>><%=fantasiaTayteValintaLista.get(l).getTayte_nimi_en() %></option>
													<%} %>
												</select>
											<%} %>
												
												 
				
					</div></td></tr>
					</form>
				
			
			<% } %>
		</table><br>
    </span></div>


</div>
<div class="tabs-panel" id="panel2">
<div class="row medium-up-3 large-up-5">

<div a name="juomat">
    <h1>DRINKS</h1>
       <span class="juomalista">
    <table class="listaa-pizzat" border="1" align="center">
		<tr>	
			<th>DRINKS</th>
			<th>PRICE (&euro;)</th>
		
			
			<!--  <th>TOIMINNOT</th>-->
				
		</tr>
		<%for(int i = 0; i < juomat.size(); i++) {%>
		<tr>
				<td><div><%=juomat.get(i).getNimi()%></div></td>
				<td><div><%=nf.format(juomat.get(i).getHinta())%>€ </div></td>
				<td><div><%=nf.format(juomat.get(i).getLitrakoko())%> litres</div></td>
				
				<td class="juomavalinta">
				<form method="post">
					<input type="hidden" name="juomaID" value="<%=juomat.get(i).getId()%>">
					<select name="maara">
				 		<%for(int n=0; n<10;n++){%>
				 			<option value="<%=n+1%>"><%=n+1%></option>
				 		<%}%>
					</select>Pcs
					<input class="koriin" type="submit" value="Add to Cart">
				</form></td>		
				
			
		 </tr>		<% } %>
    
    </table>
    <br><br>
    
    
    
    </span></div>


</div>
</div>
</div>
</div>
 
 


 
    <br><br><br>
    
        
    </section>
    
   
  <!-- end .content --></article>
 	
  <footer>
     
  <p>Ratapihantie 13, 00100 Helsinki.  
  <p>Puh. (09) 123 123 12</p>
    <address>
      
    </address>
  </footer>
  <!-- end .container --></div>
	</body>
	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src="http://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.js"></script>
	<script>
      $(document).foundation();
    </script>
	</body>
</html>
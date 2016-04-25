<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
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





<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
	scope="request" />
<jsp:useBean id="pizzaFantasia" type="java.util.ArrayList<Pizza> "
	scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizzeria Pizzicato</title>

<link href="puhelin.css" rel="stylesheet" type="text/css"
	media="only screen and (min-width: 0px)
and (max-width: 770px)">

<link href="etusivu.css" rel="stylesheet" type="text/css"
	media="only screen and (min-width: 771px)">
<style type="text/css">
</style>
<!--[if lt IE 9]>




<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
		<body>
	<div class="container">
<nav class=isoruutu>
<img src="Kuvia/pizzamies.png" id="logo" />
 <h4>Pizzeria Pizzicato is located in Meilahti, Helsinki.</h4> 
<ul>
   <a href="/Pizzeria_Pizzicato/pizzaMenu"> <img  src="Kuvia/FI_lippu.png" alt="english" id="flag" /></a>
 
 	<li style{text-align; right}><a href="/Pizzeria_Pizzicato/vahvistaTilaus"><img src="Kuvia/ostoskori.png" alt="X" style="width:15px;height:15px; padding-right:2px"/>Shopping cart(<%=ostoskori.getMaara()%>)</a> </li>
       <div class="dropdown">
  <button onclick="myFunction()" class="dropbtn"> Login</button>
  <div id="myDropdown" class="dropdown-content">
  <form action="kirjautuminen" method="post">
    <ul><li>	<input class="textField" type="text" name="kayttaja" maxlength="30" id="kayttaja" placeholder="username" />
 	<li>	<input class="textField" type="password" name="salasana" maxlength="30" id="salasana" placeholder="password" />&nbsp;
       	 	 	<button onclick="myFunction()" class="submitImage"><img src="Kuvia/loginbutton.png" id="LoginLogo" width="auto" height="22"/>
  </form>
 </button>
</ul>
  </div>
</div>

 	
</ul>
<p class="p1">${message3}</p>
		<c:remove var="message3" scope="session" />
</nav>



  <article>
  
    <h1><br>PIZZA MENU</h1>
    
    
    <section>
   
    <span class="pizzalista">
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
				<td><div class="pizzat"><%=nf.format(pizzat.get(i).getHinta())%>€ </div></td>
				<td>
				<form method="post">
				 Oregano<input type="checkbox" name="oregano" value="1"> 
				 Garlic   <input type="checkbox" name="vSipuli" value="1">
				<input type="hidden" name="pizzaID" value="<%=pizzat.get(i).getId()%>">
				<input type="submit" value="Select">
				</form></td>			
			</tr>							
			<tr><td><div class="taytteet"> <%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi_en()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi_en()%>
												 
			</div></td></tr>
			<% } %>
		</table><br>
    </span>
    
    <span class="pizzalista">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>	
			<th>FANTASIAPIZZAS</th>
			<th>PRICE</th>
			<th> </th>
			<!--  <th>TOIMINNOT</th>-->
				
		</tr>
			<%for(int i = 0; i < pizzaFantasia.size(); i++) {%>
			
			<tr>
				
				<td><div class="nimet"><%out.print(i+1);%>. <b><%=pizzaFantasia.get(i).getNimi()%></b></div></td>
				<td><div class="pizzat"><%=nf.format(pizzaFantasia.get(i).getHinta())%>€ </div></td>
				<td>
				<form style="width: 350px;" method="post">
				 Oregano<input type="checkbox" name="oregano" value="1"> 
				 Garlic<input type="checkbox" name="vSipuli" value="1">
				<input type="hidden" name="pizzaID" value="<%=pizzaFantasia.get(i).getId()%>">
				<input type="submit" value="Select">
				</form></td>			
			</tr>
			<tr><td><div class="taytteet2"> <%int j=0; for(j = 0; j<pizzaFantasia.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzaFantasia.get(i).getTaytteet().get(j).getTayte_nimi_en()%>, 
												<%  }%>
												 <%= pizzaFantasia.get(i).getTaytteet().get(j).getTayte_nimi_en()%> + <%=i+2%> toppings of your choice.
												 
			</div></td></tr>
			<% } %>
			
		</table><br>
    </span>
 
     
    </section>

  
  
 <!-- end .content --></article>
		<footer>

		<p>Ratapihantie 13, 00100 Helsinki. Phone (09) 123 123 12</p>
		<address></address>
		</footer>
		<!-- end .container -->
	</div>

	</body>

</html>
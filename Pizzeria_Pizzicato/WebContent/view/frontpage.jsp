<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.text.NumberFormat" %>

<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
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
</head>
<body>
	<div class="container">
		<nav class=isoruutu>
		<ul> <a href="/Pizzeria_Pizzicato/pizzaMenu"> <img src="Kuvia/FI_lippu.png" class="lippu"alt="suomi" id="flag"/></a>
   			<a href="/Pizzeria_Pizzicato/pizzaMenuen"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
			<li><a href="#"> Frontpage</a></li>
			<li><a href="#"> Drinks</a></li>
			
		</ul>
 <form action="/kirjaudu.html" method="post">
      <div class="loginrow2">
       <input type="image" class="submitImage" src="Kuvia/loginbutton.png" id="LoginLogo" width="auto" height="25">
        <input class="textField" type="text" name="username" maxlength="30" id="login-username" placeholder="username" />&nbsp;
        <input class="textField" type="password" name="password" maxlength="30" id="login-password" placeholder="password" />&nbsp;
    
     
        
      </div>
    </form>



		</nav>

		<div class="dropdown">

			<button onclick="myFunction()" class="dropbtn">
				<img src="http://www.teleliban.com.lb/images/hamburger.png"
					width="25" height="25">
			</button>
			<div id="myDropdown" class="dropdown-content">
				<ul>
				
					<li><a href="#"> Frontpage</a></li>
					<li><a href="#"> Drinks</a></li>
					<li><a href="/Pizzeria_Pizzicato/listaaPizzat"> Omistajan
							sivut</a></li>
				</ul>

    </form>
				
			</div>
		</div>
  <header>
   <img src="Kuvia/pizzamies.png" id="logo" width="100"/> <br>  
  <div class="kielet"> 
 <a href="/Pizzeria_Pizzicato/pizzaMenuen"> ENG</a>
 <a href="/Pizzeria_Pizzicato/pizzaMenu"> FIN</a>
  </div>
	<h6> Restaurant Pizzicato is super trendy restaurant in the heart of Helsinki!<br></h6>
  </header>

		

		

 
   
  </header>
 
  <article>
  
    <span class="pizzalista"><h1>Pizzamenu</h1></span>
    
    <section>
   
    <span class="pizzalista">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			
			<th>PIZZAS</th>
			<th>PRICE</th>
			<!--  <th>TOIMINNOT</th>-->
				
		</tr>
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			
			<tr>
				
				<td><div class="pizzat"><%out.print(i+1);%>. <b><%=pizzat.get(i).getNimi()%></b></div></td>
				<td><div class="pizzat"><%=nf.format(pizzat.get(i).getHinta())%>€ </div></td>
										
			</tr>
			<tr><td><div class="pizzat"> <%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi_en()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi_en()%>
												 
			</div></td></tr>
			<% } %>
		</table><br>
    </span>
 
     
    </section>

   <a href ="/Pizzeria_Pizzicato/tilaaPizzaEn" class="tilaa"> Make an Order</a>
  
  
 <!-- end .content --></article>
		<footer>

		<p>Ratapihantie 13, 00100 Helsinki. Phone (09) 123 123 12</p>
		<address></address>
		</footer>
		<!-- end .container -->
	</div>

	</body>

</html>
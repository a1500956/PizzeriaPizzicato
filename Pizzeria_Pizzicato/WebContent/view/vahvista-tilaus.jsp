<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzeria_pizzicato.model.TilattuTuote"%>
<%@ page import="pizzeria_pizzicato.model.Tuote"%>


<jsp:useBean id="tilauslista" type="java.util.ArrayList<TilattuTuote> "
scope="request" />


<%@ page import="java.text.NumberFormat" %>

<%
    NumberFormat nf = NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    nf.setMinimumFractionDigits(2);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vahvista Tilaus</title>
</head>
<body>

<link href="puhelin.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 0px)
and (max-width: 770px)" >

<link href="etusivu.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 771px)">
<style type="text/css">



</style><!--[if lt IE 9]>




<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]--></head>
</head>
	<body>
	
	  <%
  	session.setAttribute("tilauslista", tilauslista);
  
  %>
	
	<div class="container">
<nav class=isoruutu>
<ul>
   
   <a href="/Pizzeria_Pizzicato/pizzaMenuEng"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
 	
 	
	<li><a href ="/Pizzeria_Pizzicato/pizzaMenu"> Etusivu</a></li>
    <form action="kirjautuminen" method="post">
      <div class="loginrow2">
        <input type="image" class="submitImage" src="Kuvia/loginbutton.png" id="LoginLogo" width="auto" height="25">
        <input class="textField" type="password" name="password" maxlength="30" id="login-password" placeholder="salasana" />&nbsp;
        <input class="textField" type="text" name="username" maxlength="30" id="login-username" placeholder="k�ytt�j�tunnus" />&nbsp;
       
       
      </div>
    </form>

 	
</ul>


	
</nav>

 <div class="dropdown">
 
  <button onclick="myFunction()" class="dropbtn"> <img src="http://www.teleliban.com.lb/images/hamburger.png" width="25" height="25"></button>
  <div id="myDropdown" class="dropdown-content">
    <ul>
 	<li>		<a href ="/Pizzeria_Pizzicato/pizzaMenu"> Palaa takaisin etusivulle</a></li>
 	<li>	<a href ="#"> Juomat</a></li>
 	<li>	<a href ="/Pizzeria_Pizzicato/listaaPizzat"> Omistajan sivut</a></li>
 	
 	
</ul>

  </div>
  <article>
 
    <section>
   
   <form action="vahvistaTilaus" method="post" style="padding-top:100px">
  

    <span class="pizzalista">
     <h1 class=hMode2>TILAUKSENNE</h1>
    <div class=button><a href="tilaaPizza">Takaisin</a></div><br><br>
		<table class="listaa-pizzat2" width="auto" border="1" align="center">	
  		<p style="color:white;">Toimitusosoite:<input type="text" name="osoite" size="40" pattern=".{6,40}" required></p>
  		<p style="color:white;">Puhelinnumero:<input type="text" name="puhnro" size="40" pattern=".{10,10}" required></p><br>
		<tr>
			
			<th style="border-bottom: solid 1px grey;">PIZZAT</th>
			<th style="border-bottom: solid 1px grey;">KAPPALEHINTA</th>
			<th style="border-bottom: solid 1px grey;">LUKUM��R�</th>
			
			<!--  <th>TOIMINNOT</th>-->
			
		</tr>
		<%double summa=0;%>

			<%for(int i = 0; i < tilauslista.size(); i++) {%>
			<tr>
				<td><div class=""><%=tilauslista.get(i).getTuote().getNimi()%></div></td>
				<td><div class="tilauslista"><%=nf.format(tilauslista.get(i).getTuote().getHinta())%>&euro;</div></td>
				<%summa+=(tilauslista.get(i).getTuote().getHinta()*tilauslista.get(i).getLkm());%>
				<td><div class="tilauslista"><%=tilauslista.get(i).getLkm()%> kpl</div></td>
				<td>
				</td>								
			</tr>
			<% } %>
		
		</table><br>
		<p style="color:white;"><u>SUMMA:</u> <%=nf.format(summa) %>&euro;</p><input type="submit" name="submit-button"
					class="submit-button" value="Vahvista Tilaus" />
		
		
    </span>
    </form>

     
    </section>
    

    
  <!-- end .content --></article>
 	
  <footer>
     
  <p>Ratapihantie 13, 00100 Helsinki.  Puh. (09) 123 123 12</p>
    <address>
      
    </address>
  </footer>
  <!-- end .container --></div>
	</body>
</html>
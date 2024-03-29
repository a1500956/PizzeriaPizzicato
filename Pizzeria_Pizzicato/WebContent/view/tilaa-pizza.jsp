<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="pizzeria_pizzicato.model.Tuote"%>
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
and (max-width: 770px)" >

<link href="etusivu.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 771px)">
<style type="text/css">



</style><!--[if lt IE 9]>




<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]--></head>
</head>
	<body>
	<div class="container">
<nav class=isoruutu>
<ul>

   <a href="/Pizzeria_Pizzicato/tilaaPizzaEn"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
 	

 	
	<li><a href ="/Pizzeria_Pizzicato/pizzaMenu"> Etusivu</a></li>
    <form action="kirjautuminen" method="post">
      <div class="loginrow2">
        <input type="image" class="submitImage" src="Kuvia/loginbutton.png" id="LoginLogo" width="auto" height="22">
        
        <input class="textField" type="text" name="username" maxlength="30" id="login-username" placeholder="käyttäjätunnus" />&nbsp;
       <input class="textField" type="password" name="password" maxlength="30" id="login-password" placeholder="salasana" />&nbsp;
       
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
 
</div>


  <header>
   <img src="Kuvia/pizzamies.png" id="logo" width="300" height="250"/><br>
  
  <div class="kielet"> 
  <a href="/Pizzeria_Pizzicato/tilaaPizzaEn">ENG</a>
  <a href="/Pizzeria_Pizzicato/tilaaPizza">FIN</a>
  </div>
  </header>
 
  <article>
  
    
    <section>
   <form action="" method="post">
   <tr><div class=button><a href="pizzaMenu">Takaisin</a></div><br>
		<input type="submit" name="submit-button"
					class="submit-button" value="Jatka Tilausta" /></tr>
    <span class="pizzalista">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			
			<th>PIZZAT</th>
			<th>HINTA</th>
			<th class="pcs">KPL</th>
			<!--  <th>TOIMINNOT</th>-->
			
		</tr>
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			
			<tr>
				
				<td><div class="pizzat"><%out.print(i+1);%>. <b><%=pizzat.get(i).getNimi()%></b></div></td>
				<td><div class="pizzat"><%=nf.format(pizzat.get(i).getHinta())%>€ </div></td>
				<td><div class="maara"><input name=<%=pizzat.get(i).getId()%> type="text" value="0" size="1" ></div></td>
										
			</tr>
			<tr><td><div class="taytteet"> <%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>
												 
			</div></td></tr>
			<% } %>
		</table>
		
    </span>
    </form>

     
    </section>
    

    
  <!-- end .content --></article>
 	
  <footer>
     
  <p>Kuusitie 6, Meilahti, Helsinki.  Puh. 040 123 456</p>
    <address>
      
    </address>
  </footer>
  <!-- end .container --></div>
	</body>
</html>

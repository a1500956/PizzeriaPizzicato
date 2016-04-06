<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
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
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("kayttaja") == null){
    response.sendRedirect("pizzaMenu");
}else user = (String) session.getAttribute("kayttaja");
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
<ul>
 	<li>	<a href ="#"> Etusivu</a></li>
 	<li>	<a href ="#"> Juomat</a></li>
    <form action="<%=response.encodeURL("uloskirjautuminen") %>" method="post">
      <div class="loginrow2">
      <h3><%=userName %>, olet kirjautuneena.</h3>
	<input type="submit" value="Uloskirjaus" >
      </div>
    </form>

<<<<<<< HEAD
 	
</ul>


	
</nav>

 <div class="dropdown">
 
  <button onclick="myFunction()" class="dropbtn"> <img src="http://www.teleliban.com.lb/images/hamburger.png" width="25" height="25"></button>
  <div id="myDropdown" class="dropdown-content">
    <ul>
 	<li>		<a href ="#"> Etusivu</a></li>
 	<li>	<a href ="#"> Juomat</a></li>
 	
</ul>
  </div>
</div>


  <header>
  <section><img src= "Kuvia/pizzamies.png" alt="Pizzicato logo" id="Insert_logo"  />
<a href="/Pizzeria_Pizzicato/pizzaMenu"> <img src="Kuvia/FI_lippu.png" class="lippu"alt="suomi" id="flag"/></a>
		<a href="/Pizzeria_Pizzicato/pizzaMenuen"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
  </section>
   
  </header>
 
  <article>
  
    <span class="pizzalista"><h1>Pizza menumme</h1></span>
    
    <section>
   
    <span class="pizzalista">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>
			
			<th>PIZZAT</th>
			<th>HINTA</th>
			<!--  <th>TOIMINNOT</th>-->
				
		</tr>
			<%for(int i = 0; i < pizzat.size(); i++) {%>
			
			<tr>
				
				<td><div class="pizzat"><%out.print(i+1);%>. <b><%=pizzat.get(i).getNimi()%></b></div></td>
				<td><div class="pizzat"><%=nf.format(pizzat.get(i).getHinta())%>€ </div></td>
										
			</tr>
			<tr><td><div class="pizzat"> <%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>
												 
			</div></td></tr>
			<% } %>
		</table><br>
    </span>

    
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
=======
<!-- need to encode all the URLs where we want session information to be passed -->
<a href="<%=response.encodeRedirectURL("pizzaMenu") %>">Etusivu</a>
<a href="<%=response.encodeRedirectURL("listaaPizzat") %>">Pizzalista</a>
<form action="<%=response.encodeRedirectURL("uloskirjautuminen") %>" method="post">
<br>
<br>
<input type="submit" value="Uloskirjaus" >
</form>
</body>
</html>
>>>>>>> branch 'master' of https://github.com/a1500956/PizzeriaPizzicato.git

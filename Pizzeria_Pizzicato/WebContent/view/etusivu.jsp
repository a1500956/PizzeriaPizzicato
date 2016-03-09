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
	<div class="container">
<nav><ul>
  <li><a href ="Etusivu.jsp"> Etusivu</a></li>
 	<li>	<a href ="juomaSivut.jsp"> Juomat</a></li>
 	<li>	<a href ="listaa-pizzat.jsp"> Omistajan sivut</a></li>
</ul>


	
</nav>

 <div class="dropdown">
  <button onclick="myFunction()" class="dropbtn"> </button>
  <div id="myDropdown" class="dropdown-content">
    <a href="etusivu.jsp">Etusivu</a>
    <a href="juomaSivut.jsp">Juomat</a>
    <a href="listaa-pizzat.jsp">Omistajan sivut</a>
  </div>
</div>


  <header>
  <section><img src="http://www.greenitalia.ie/images/slider-2.jpg" alt="Pizzicato" width="55%" height="250" id="Insert_logo" style="background-color: #C6D580; " />
  <p><h5>Pizzeria Pizzicato</h5></p>
  <p>Ratapihantie 13, 00100 Helsinki.  Puh. (09) 123 123 12</p>
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
    <p>Yhteystietoja yms</p>
    <address>
      
    </address>
  </footer>
  <!-- end .container --></div>

	</body>
</html>

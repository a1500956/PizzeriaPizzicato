<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>
<%@ page import="pizzeria_pizzicato.model.Tayte"%>
<%@ page import="pizzeria_pizzicato.model.Tuote"%>
<%@ page import="pizzeria_pizzicato.model.Juoma"%>
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
<style type="text/css"></style>

<!--!
<link href="tabletti.css" rel="stylesheet" type="text/css"
media="only screen and (min-device-width: 0px)
and (max-device-width: 770px)" >
<style type="text/css"></style>
-->

<link href="puhelin.css" rel="stylesheet" type="text/css"
media="device" >
<style type="text/css"></style>


<link href="etusivu.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 771px)">
<style type="text/css"></style>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">




<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

	<script>
	var tilausLapi = "${tilausLapi}";
	document.write(tilausLapi);
	if(tilausLapi == "true"){
		$(function() {
		$( "#dialog" ).dialog();
		});
	}
	</script>

</head>
		<body>
	<%if((String) session.getAttribute("tilausLapi") == "true") {%>	
	<div id="dialog" title="Tilaus lähetetty">
  		<p style="color:black;">Tilaus lähetetty eteenpäin!<br>
  		Tilauksenne on valmis tunnin sisällä (kiirevarauksella)</p>
	</div>
	
	<script>
	$(function(){
		$("dialog").focus();
		
	});
	</script>
	<%}%>
	
	
	<c:remove var="tilausLapi" scope="session" />
	
	<div class="container">
<nav class=isoruutu>
<img class="pizzamies" src="Kuvia/pizzamies.png" id="logo"/>
 <h4 class="esittely">Pizzeria Pizzicato, Meilahdentie 1, 00210 Helsinki. Puh. (09) 300 300 30</h4>
 <h6>Avoinna ma-to klo 11-21, pe-la klo 12-23, su klo 12-21</h6> 
<ul>
   <a href="/Pizzeria_Pizzicato/pizzaMenuEn"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
 <li style{text-align; right}><a href="/Pizzeria_Pizzicato/vahvistaTilaus"><img src="Kuvia/ostoskori.png" alt="X" style="width:15px;height:15px; padding-right:2px"/>Ostoskori(<%=ostoskori.getMaara()%>)</a> </li>
 

<div class="dropdown">
  <button onclick="myFunction()" class="dropbtn"> Kirjaudu sisään</button>
  <div id="myDropdown" class="dropdown-content">
  <form action="kirjautuminen" method="post">
    <ul>
    	<li><a href="rekisteroidy"><small>Tiliä vailla? Rekisteröidy.</small></a></li>
    	<li><input class="textField" type="text" name="kayttaja" maxlength="30" id="kayttaja" placeholder="käyttäjätunnus" />
 		<li><input class="textField" type="password" name="salasana" maxlength="30" id="salasana" placeholder="salasana" />&nbsp;
       	<button onclick="myFunction()" class="submitImage"><img src="Kuvia/loginbutton.png" id="LoginLogo" width="auto" height="22"/>
 		</button>
 		
 	
	</ul>
  </form>
  </div>
</div>
</ul>
<p class="p1">${message3}</p>
		<c:remove var="message3" scope="session" />
</nav>
<!--
<nav class="puhelin">
 <div class="dropdown">
 <li style{text-align; right}><a href="/Pizzeria_Pizzicato/vahvistaTilaus"><img src="Kuvia/ostoskori.png" alt="X" style="width:15px;height:15px; padding-right:2px"/>Ostoskori(<%=ostoskori.getMaara()%>)</a> </li>
  
  <button onclick="myFunction()" class="dropbtn"> Kirjaudu sisään</button>
  <div id="myDropdown" class="dropdown-content">
  <form action="kirjautuminen" method="post">
    <ul>	
    <li><input class="textField" type="text" name="kayttaja" maxlength="30" id="kayttaja" placeholder="käyttäjätunnus" />
 	<li>	<input class="textField" type="password" name="salasana" maxlength="30" id="salasana" placeholder="salasana" />&nbsp;
       	 	 	<button onclick="myFunction()" class="submitImage"><img src="Kuvia/loginbutton.png" id="LoginLogo" width="auto" height="22"/>
 </button>
</ul>
  </div>
</div>
</nav>
!-->


  <article>
  <br> <br> <br>
 <br>
 <div class="column row">

<ul class="tabs" data-tabs id="example-tabs" style = "list-style-type: none;">
<li class="tabs-title is-active"><a href="#panel1" aria-selected="true">Pizzat</a></li>
<li class="tabs-title"><a href="#panel2">Juomat</a></li>
</ul>
<div class="tabs-content" data-tabs-content="example-tabs">
<div class="tabs-panel is-active" id="panel1">

<div class="pizzat">

  <h1 class="pizzaotsikko"><br>PIZZA MENU</h1>
  
    <section>
   
    <span class="pizzalista2">
		<table class="listaa-pizzat" width="auto" border="1" align="center">
		<tr>	
			<th>PIZZAT</th>
			<th>HINTA</th>
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
				 	<input class="mauste" type="checkbox" name="vSipuli" value="1">Valkosipuli
					<input type="hidden" name="pizzaID" value="<%=pizzat.get(i).getId()%>">
					<td  rowspan="2" style="padding-left: 10px; vertical-align: top;">
					<select name="maara">
				 		<%for(int n=0; n<10;n++){%>
				 			<option value="<%=n+1%>"><%=n+1%></option>
				 		<%}%>
					</select>Kpl <br>
					<input  class="koriin" type="submit" value="Koriin" >
				</form></td>			
			</tr>
			<tr><td><div class="taytteet"> <%int j=0; for(j = 0; j<pizzat.get(i).getTaytteet().size()-1;j++) { %>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>, 
												<%  }%>
												 <%= pizzat.get(i).getTaytteet().get(j).getTayte_nimi()%>
												 
			</div></td></tr>
			<% } %>
			
		<tr>
		<th><br>FANTASIAPIZZAT</th>
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
				<input class="mauste" type="checkbox" name="vSipuli" value="1">Valkosipuli
				<input type="hidden" name="pizzaID" value="<%=pizzaFantasia.get(i).getId()%>">
				<td  rowspan="2" style="padding-left: 10px; vertical-align: top;">
				<select name="maara">
				 	<%for(int n=0; n<10;n++){%>
				 		<option value="<%=n+1%>"><%=n+1%></option>
				 	<%}%>
				 </select>Kpl <br>
				<input type="submit" value="Koriin">
				</td>
				</tr>
				
			<tr><td><div class="taytteet"> <%int j=0; for(j = 0; j<pizzaFantasia.get(i).getTaytteet().size()-1;j++) {%>

												 <%= pizzaFantasia.get(i).getTaytteet().get(j).getTayte_nimi()%>, 
												<%  }%>
												 <%=pizzaFantasia.get(i).getTaytteet().get(j).getTayte_nimi()%> + <%=(i +2)%> kpl valitsemaasi täytettä. <br>
												 
				
				
											<%int k=0; for(k=0; k<taytemaara; k++) {%>
												<select class="lisatayteValikko" name="lisatayte">
												<option selected disabled>Valitse täyte <%=k+1%></option>
													<%for(int l=0; l<fantasiaTayteValintaLista.size();l++){ %>
														 <option value=<%=fantasiaTayteValintaLista.get(l).getTayte_id() %>><%=fantasiaTayteValintaLista.get(l).getTayte_nimi() %></option>
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
    <h1>JUOMAT</h1>
       <span class="juomalista">
    <table class="listaa-pizzat" border="1" align="center">
		<tr>	
			<th>JUOMAT</th>
			<th>HINTA (&euro;)</th>
		
			
			<!--  <th>TOIMINNOT</th>-->
				
		</tr>
		<%for(int i = 0; i < juomat.size(); i++) {%>
		<tr>
				<td><div><%=juomat.get(i).getNimi()%></div></td>
				<td><div><%=nf.format(juomat.get(i).getHinta())%>€ </div></td>
				<td><div><%=nf.format(juomat.get(i).getLitrakoko())%> litraa</div></td>
				
				<td class="juomavalinta">
				<form method="post">
					<input type="hidden" name="juomaID" value="<%=juomat.get(i).getId()%>">
					<select name="maara">
				 		<%for(int n=0; n<10;n++){%>
				 			<option value="<%=n+1%>"><%=n+1%></option>
				 		<%}%>
					</select>Kpl
					<input class="koriin" type="submit" value="Koriin">
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
</html>

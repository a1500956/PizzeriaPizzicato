<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Ostoskori"%>

<%
Ostoskori ostoskori = new Ostoskori();
ostoskori = (Ostoskori) session.getAttribute("ostoskori");
%>



<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
<style type="text/css">



</style>




<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>
</head>
		<body>
	<div class="container">
<nav class=isoruutu>
<img class="pizzamies" src="Kuvia/pizzamies.png" id="logo"/>
 <h4 class="esittely">Pizzeria Pizzicato sijaitsee Meilahdessa, Helsingissä.</h4> 
<ul>
   <a href="/Pizzeria_Pizzicato/pizzaMenuEn"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
 <li style{text-align; right}><a href="/Pizzeria_Pizzicato/vahvistaTilaus"><img src="Kuvia/ostoskori.png" alt="X" style="width:15px;height:15px; padding-right:2px"/>Ostoskori(<%=ostoskori.getMaara()%>)</a> </li>
 

<div class="dropdown">
  <button onclick="myFunction()" class="dropbtn"> Kirjaudu sisään</button>
  <div id="myDropdown" class="dropdown-content">
  <form action="kirjautuminen" method="post">
    <ul>
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
 <br>
 <br>
 <br><br>
  <h1 class="pizzaotsikko"><br>REKISTERÖI KÄYTTÄJÄ</h1>
  
  <p>${message}</p>
<c:remove var="message" scope="session" /> 
    
			
<form action="" method="post">
			<table class="lisaa-pizza" align=center>
				
				<tr>
					<td>Käyttäjänimi:</td>
					<td><input type="text" value="" name="Knimi" size="20" pattern=".{4,20}" required title="Pituuden tulee olla 4-20 merkkiä" />
					</td>					
				</tr>
				<tr>
					<td>Etunimi:</td>
					<td><input type="text" value="" name="Enimi" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
					</td>					
				</tr>
				<tr>
					<td>Sukunimi:</td>
					<td><input type="text" value="" name="Snimi" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
					</td>					
				</tr>
				<tr>
				<td>Katuosoite:</td>
					<td><input type="text" value="" name="KatuOs" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
				</td>	
				</tr>
				<tr>
				<td>Puhelinnumero:</td>
					<td><input type="text" value="" name="Puhno" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
				</td>	
				</tr>
				<tr>
				<td>Sähköposti:</td>
					<td><input type="text" value="" name="Sposti" size="20" pattern=".{6,20}" required title="Pituuden tulee olla 6-20 merkkiä" />
				</td>
				</tr>
				<tr>
				<td>Salasana:</td>
					<td><input type="text" value="" name="SS" size="20" pattern=".{3,20}" required title="Pituuden tulee olla 3-20 merkkiä" />
				</td>
				</tr>
				<tr>
					<td><br><div class ="button"><a href="pizzaMenu">Palaa etusivulle</a></div></td>
					<td><br>
						<input type="submit" name="submit-button" class="submit-button" value="Tallenna" />
					</td>
				</tr>	
			</table>
			</form>	


        
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
</html>
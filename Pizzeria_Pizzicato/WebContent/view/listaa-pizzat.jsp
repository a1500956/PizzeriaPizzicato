<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="pizzeria_pizzicato.model.Pizza"%>

<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza> "
scope="request" />




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pizza lista</title>

<link href="etusivu.css" rel="stylesheet" type="text/css">


<link href="puhelin.css" rel="stylesheet" type="text/css"
media="only screen and (min-width: 0px)
and (max-width: 360px)" >

<link href="tabletti.css"
rel="stylesheet" type="text/css"
media="only screen and (min-width: 361px)
and (max-width: 768px)" >

<style type="text/css">


</style>

<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>

<body>
	<div class="container">
		<nav>
 			<h1>Pizzeria Piccicato</h1>
 			<h5>Ratapihantie 13, 00100 Helsinki.  Puh. (09) 123 123 12</h5>
		</nav>
	
  <header>
  
  <background-image a href="#"><img src="http://www.greenitalia.ie/images/slider-2.jpg" alt="Pizzeria Pizzicato" id="Insert_logo" />
  </header>
  
  </a><p>Header ja navi vaihtavat paikkojaan jatkossa.</p>
 
   
 
  <article>
  
    <span class="pizzalista"><h1>Pizzamenu</h1></span>
    
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
				
				<td><div class="pizzat"><%=pizzat.get(i).getId()%>. <b><%=pizzat.get(i).getNimi()%></b></div></td>
				<td><div class="pizzat"><%=pizzat.get(i).getHinta()%> €</div></td>
				<!--<td><div class="toiminnot"> 
				<a href="muokkaa-pizza?id=<%=pizzat.get(i).getId()%><%=pizzat.get(i).getNimi()%>" class="button">
				Muokkaa pizzaa
				</a>		
				<a href="poista-pizza?id=<%=pizzat.get(i).getId()%><%=pizzat.get(i).getNimi()%>" class="button">
				Poista pizza
				</a></div>
				</td>-->								
			</tr>
			<tr><td>tähän tulee täytteet</td></tr>
			<% } %>
		</table><br>
		<!-- <a href="lisaa-pizza" class="button">Lisää pizza</a> -->
    </span>
    
    </section>
    <section class="menu">
    <table width="auto" border="1" align="center">
		<tr>
				<th>Pizza</th>
				<th>Hinta</th>
				
		</tr><tr></tr>
        <tr>
				<td>1. <b>Margaharita </b></td>
				<td>7,90</td>	
		</tr>
        <tr>
        <td colspan="2">tomaattikastike, juusto</td></tr>
         <tr><tr><tr></tr>
				<td>2.<b> Vegetara </b></td>
				<td>8,90</td>	
		</tr>
        <tr>
        <td colspan="2">tomaattikastike, juusto, herkkusieni, sipuli, oliivi, pinaatti</td></tr>
        
          <tr><tr><td></td></tr>
				<td>3. <b>Frutti di Mare </b></td>
				<td>9,90</td>	
		</tr>
        <tr>
        <td colspan="2">tomaattikastike, juusto, tonnikala, katkarapu, simpukka</td><br></tr>
      
        
          <tr><tr><td></td></tr>
				<td>4. <b>Capricciosa </b></td>
				<td>9,90</td>	
		</tr>
        
       
        
        <tr>
        <td colspan="2">tomaattikastike, juusto, katkarapu, kinkku, herkkusieni</td></tr>
        
          <tr><tr><td></td></tr>
				<td>5. <b>Vesuvio </b></td>
				<td>9,90</td>	
		</tr>
        <tr>
        <td colspan="2">tomaattikastike, juusto, salami, pepperoni, jalopeno</td></tr>
        
        
        
        
        <tr><tr><td></td></tr>
       
				<td>6. <strong>Fantasia kahdella täytteellä</strong></td>
				<td>9,90</td>	
		</tr>
        <tr>
        <td >


        
        <FORM NAME="joe">
<p>Valitse kaksi täytettä pizzaasi</p> 

<INPUT TYPE="checkbox" NAME="juusto" 
onClick="return KeepCount1()"> Juusto

<INPUT TYPE="checkbox" NAME="ananas" 
onClick="return KeepCount1()"> Ananas

<INPUT TYPE="checkbox" NAME="salami" 
onClick="return KeepCount1()"> Salmi

<INPUT TYPE="checkbox" NAME="herkkusieni" 
onClick="return KeepCount1()"> Herkkusieni

<INPUT TYPE="checkbox" NAME="tomaatti" 
onClick="return KeepCount1()"> Tomaatti

</FORM>
      <SCRIPT LANGUAGE="javascript">

function KeepCount1() {

var NewCount1 = 0

if (document.joe.juusto.checked)
{NewCount1 = NewCount1 + 1}

if (document.joe.ananas.checked)
{NewCount1 = NewCount1 + 1}

if (document.joe.salami.checked)
{NewCount1 = NewCount1 + 1}

if (document.joe.herkkusieni.checked)
{NewCount1 = NewCount1 + 1}

if (document.joe.tomaatti.checked)
{NewCount1 = NewCount1 + 1}

if (NewCount1 == 3)
{
alert('Valitse vain kaksi täytettä')
document.joe; return false;
}
} 
</SCRIPT>
</td></tr></tr>

 
        <tr>
				<td>7. <strong>Fantasia kolmella täytteellä</strong></td>
				<td>9,90</td>	
		</tr>
        <tr>
        <td >


        
        <FORM NAME="doe">
<p>Valitse kolme täytettä pizzaasi</p> 

<INPUT TYPE="checkbox" NAME="juusto" 
onClick="return KeepCount()"> Juusto

<INPUT TYPE="checkbox" NAME="ananas" 
onClick="return KeepCount()"> Ananas

<INPUT TYPE="checkbox" NAME="salami" 
onClick="return KeepCount()"> Salmi

<INPUT TYPE="checkbox" NAME="herkkusieni" 
onClick="return KeepCount()"> Herkkusieni

<INPUT TYPE="checkbox" NAME="tomaatti" 
onClick="return KeepCount()"> Tomaatti

</FORM>
      <SCRIPT LANGUAGE="javascript">

function KeepCount() {

var NewCount = 0

if (document.doe.juusto.checked)
{NewCount = NewCount + 1}

if (document.doe.ananas.checked)
{NewCount = NewCount + 1}

if (document.doe.salami.checked)
{NewCount = NewCount + 1}

if (document.doe.herkkusieni.checked)
{NewCount = NewCount + 1}

if (document.doe.tomaatti.checked)
{NewCount = NewCount + 1}

if (NewCount == 4)
{
alert('Valitse vain kolmetäytettä')
document.doe; return false;
}
} 
</SCRIPT>
</td></tr></table>

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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="pizzeria_pizzicato.model.TilattuTuote"%>
<%@ page import="pizzeria_pizzicato.model.Tuote"%>
<%@ page import="pizzeria_pizzicato.model.Ostoskori"%>
<%@ page import="pizzeria_pizzicato.model.Juoma"%>
<%@ page import="pizzeria_pizzicato.model.Pizza"%>
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

<jsp:useBean id="juomat" type="java.util.ArrayList<Juoma>"
scope="request" />
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
scope="request" />

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
	
	<div class="container">
<nav class=isoruutu>
<img src="Kuvia/pizzamies.png" id="logo" />
 <h4>Pizzeria Pizzicato sijaitsee Meilahdessa, Helsingissä.</h4> 
<ul>
   <a href="/Pizzeria_Pizzicato/pizzaMenuEng"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
 
 	<li style{text-align; right}><a href="/Pizzeria_Pizzicato/vahvistaTilaus"><img src="Kuvia/ostoskori.png" alt="X" style="width:15px;height:15px; padding-right:2px"/>Ostoskori(<%=ostoskori.getMaara()%>)</a> </li>
     <% if (user!=null){%>
        <form action="<%=response.encodeURL("uloskirjautuminen") %>" method="post">
      <div class="loginrow2">
      <h3><%=userName %>, olet kirjautuneena.</h3><input type="submit" value="Uloskirjaus" >

      </div>
    </form> <% }%> 
</ul>
  </div>
</div>

 	
</ul>
<p class="p1">${message3}</p>
		<c:remove var="message3" scope="session" />
</nav>
  <article>
 
    <section>
   
   <form action="vahvistaTilaus" method="post" style="padding-top:100px ">
  

    <span class="pizzalista">
     <h1 class=hMode2>TILAUKSENNE</h1>
    <div class=button><a href="pizzaMenu">Takaisin</a></div><br><br>
    
    <%double summa=0;%>

    <%if(ostoskori.getOstoskori() != null || ostoskori.getKoko() != 0){ %>
		<table class="listaa-pizzat2" width="auto" border="1" align="center">	
		
		
		<div class="yhteystiedot" align="center" margin-right="100px">
		
		<p style="color:white; align:right;">Etunimi:<input type="text" name="enimi" size="40" pattern=".{2,40}" required></p>
		<p style="color:white;">Sukunimi:<input type="text" name="snimi" size="40" pattern=".{2,40}" required></p>
		<p style="color:white;">Puhelinnumero:<input type="text" name="puhnro" size="40" pattern=".{9,10}" required></p><br>
		<input type="radio" name="toimitustapa" value="nouto" checked>  <label for="toimitustapa" style="color:white;">Nouto</label>
		<div><input type="radio" id="koti" name="toimitustapa" value="kotiinkuljetus" required><label for="toimitustapa" style="color:white;">Kotiinkuljetus</label>
		
		<div class="reveal-if-active">
  		<p style="color:white;">Toimitusosoite:
  		<input type="text" name="osoite" class="require-if-active" data-require-pair="#koti" size="40" pattern=".{6,40}" required></p><br>
  		</div>
  		  </div>
  </div>
  
<script>
var FormStuff = {
		  
		  init: function() {
		    this.applyConditionalRequired();
		    this.bindUIActions();
		  },
		  
		  bindUIActions: function() {
		    $("input[type='radio'], input[type='checkbox']").on("change", this.applyConditionalRequired);
		  },
		  
		  applyConditionalRequired: function() {
		  	
		    $(".require-if-active").each(function() {
		      var el = $(this);
		      if ($(el.data("require-pair")).is(":checked")) {
		        el.prop("required", true);
		      } else {
		        el.prop("required", false);
		      }
		    });
		    
		  }
		  
		};

		FormStuff.init();
</script>
		</div> 
		
		
		
		
		
		<tr style="width=350px;">
			
			<th style="border-bottom: solid 1px grey;">PIZZAT</th>
			<th style="border-bottom: solid 1px grey;">KAPPALEHINTA</th>
			<th style="border-bottom: solid 1px grey;">OREGANO</th>
			<th style="border-bottom: solid 1px grey;">V.SIPULI</th>
			<th style="border-bottom: solid 1px grey;">KPL</th>
			<th></th>
			
			
			<!--  <th>TOIMINNOT</th>-->
			
		</tr>
		<%for(int j = 0; j<pizzat.size();j++){%>
			<%for(int i = 0; i <ostoskori.getKoko(); i++) {%>
				<%if(pizzat.get(j).getId() == ostoskori.getOstoskori().get(i).getTuote().getId()){ %>
					<tr>
						<td><div class=""><%=ostoskori.getTuote(i).getTuote().getNimi()%></div></td>
						<td><div class="tilauslista"><%=nf.format(ostoskori.getTuote(i).getHinta())%>&euro;</div></td>
							<%summa+=(ostoskori.getTuote(i).getHinta()*ostoskori.getTuote(i).getLkm());%>
							<%String oregano = "kyllä";
							if(ostoskori.getTuote(i).getOregano() == 0){
								oregano = "ei";
							}%>
						<td><div class="tilauslista"><%=oregano%></div></td>
							<%String vSipuli = "kyllä";
							if(ostoskori.getTuote(i).getvSipuli() == 0){
								vSipuli = "ei";
							}%>
						<td><div class="tilauslista"><%=vSipuli%></div></td>
						<td><div class="tilauslista"><%=ostoskori.getTuote(i).getLkm()%> kpl</div></td>
						<td><a href="poistaPizzaKorista?pizzaID=<%=ostoskori.getTuote(i).getTuote().getId()%>&oregano=<%=ostoskori.getTuote(i).getOregano()%>&vSipuli=<%=ostoskori.getTuote(i).getvSipuli()%>&osoite=<%=ostoskori.getTuote(i).getvSipuli()%>&puhnro=<%=ostoskori.getTuote(i).getvSipuli()%>" class="submit-button">
						<img  src="Kuvia/miinusICON.png" alt="Poista" style="width:17px;height:17px;" />
						</a></td>								
					</tr>
				<%}%>
			<%} %>
		<%} %>
		
		<tr style="width=350px;">
			
			<th style="border-bottom: solid 1px grey;">JUOMAT</th>
			<th style="border-bottom: solid 1px grey;">KOKO</th>
			<th style="border-bottom: solid 1px grey;">KAPPALEHINTA</th>
			<th style="border-bottom: solid 1px grey;">KPL</th>
			<th></th>
			
			
			<!--  <th>TOIMINNOT</th>-->
			
		</tr>
		
		
		<%for(int j = 0; j<juomat.size();j++){%>
			<%for(int i = 0; i <ostoskori.getKoko(); i++) {%>
				<%if(juomat.get(j).getId() == ostoskori.getOstoskori().get(i).getTuote().getId()){ %>
					<tr>
						<td><div class=""><%=ostoskori.getTuote(i).getTuote().getNimi()%></div></td>
						<td><div class=""><%=juomat.get(j).getLitrakoko() + "l"%></div></td>
						<td><div class="tilauslista"><%=nf.format(ostoskori.getTuote(i).getHinta())%>&euro;</div></td>
						<%summa+=(ostoskori.getTuote(i).getHinta()*ostoskori.getTuote(i).getLkm());%>
						<td><div class="tilauslista"><%=ostoskori.getTuote(i).getLkm()%> kpl</div></td>
						<td><a href="poistaPizzaKorista?pizzaID=<%=ostoskori.getTuote(i).getTuote().getId()%>&oregano=<%=ostoskori.getTuote(i).getOregano()%>&vSipuli=<%=ostoskori.getTuote(i).getvSipuli()%>&osoite=<%=ostoskori.getTuote(i).getvSipuli()%>&puhnro=<%=ostoskori.getTuote(i).getvSipuli()%>" class="submit-button">
						<img  src="Kuvia/miinusICON.png" alt="Poista" style="width:17px;height:17px;" />
						</a></td>								
					</tr>
				<%}%>
			<%} %>
		<%} %>
			
		
		</table><br>
		<p style="color:white;"><u>SUMMA:</u> <%=nf.format(summa) %>&euro;</p><input type="submit" name="submit-button"
					class="submit-button" value="Vahvista Tilaus" />
		
		<%}else{%>
		<p>Ostoskori on tyhjä</p>
		<%} %>
		
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

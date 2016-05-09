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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<%
//allow access only if session exists
String user = (String) session.getAttribute("kayttaja");
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

<%
String eNimi=null;
if(session.getAttribute("eNimi") != null){
eNimi = (String) session.getAttribute("eNimi");
}

String sNimi=null;
if(session.getAttribute("sNimi") != null){
sNimi = (String) session.getAttribute("sNimi");
}

String osoite=null;
if(session.getAttribute("osoite") != null){
osoite = (String) session.getAttribute("osoite");
}
String puhnro=null;
if(session.getAttribute("puhnro") != null){
puhnro = (String) session.getAttribute("puhnro");
}
String sposti=null;
if(session.getAttribute("sposti") != null){
sposti = (String) session.getAttribute("sposti");
}
%>

	<body>
	
	<div class="container">
<nav class=isoruutu>
<img class="pizzamies" src="Kuvia/pizzamies.png" id="logo" />
 <h4>Pizzeria Pizzicato sijaitsee Meilahdessa, Helsingissä.</h4> 
<ul>
   <a href="/Pizzeria_Pizzicato/vahvistaTilausEn"> <img  src="Kuvia/UK_lippu.png" alt="english" id="flag" /></a>
 
 	<li style{text-align; right}><a href="/Pizzeria_Pizzicato/vahvistaTilaus"><img src="Kuvia/ostoskori.png" alt="X" style="width:15px;height:15px; padding-right:2px"/>Ostoskori(<%=ostoskori.getMaara()%>)</a> </li>

          <% if (user!=null){%>
  <form action="<%=response.encodeURL("uloskirjautuminen") %>" method="post">
      <div class="loginrow2">
      <h3><%=userName %>, olet kirjautuneena.</h3><input type="submit" value="Uloskirjaus" >

      </div>
    </form> <% }else{%> 
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
</div> <% }%>
</ul>

<p class="p1">${message3}</p>
		<c:remove var="message3" scope="session" />
</nav>
  <article>
 
    <section>
   
   <form id="vahvista" action="vahvistaTilaus" method="post" style="padding-top:100px">
   
	<script>
		function vahvistaTilaus(){
			alert("Tilaus lähetetty!");
		}
	</script>
    
     <h1 class=hMode2>TILAUKSENNE</h1>
    <div class=button><a href="kirjautuminenOk">Takaisin</a></div><br><br>
    
    <p>${message4}</p>
    
    <c:remove var="message4" scope="session" />
	
		
		

    
    <%double summa=0;%>

    <%if(ostoskori.getOstoskori() != null || ostoskori.getKoko() != 0){ %>

<table class="" width="auto" border="1" align="center">
		<tr><td style="text-align:right;">
		Etunimi:</td><td><input type="text" name="enimi" size="40" pattern=".{2,40}" <%if(eNimi != null){ %> value="<%=eNimi%>" <%} %> required></td></tr>
		<tr><td style="text-align:right;">Sukunimi:</td><td><input type="text" name="snimi" size="40" pattern=".{2,40}" <%if(sNimi != null){ %> value="<%=sNimi%>" <%} %>  required></td></tr>
		<tr><td style="text-align:right; ">Puhelinnumero:</td><td><input type="text" name="puhnro" size="40" pattern=".{9,10}" <%if(puhnro != null){ %> value="<%=puhnro%>" <%} %> required></td></tr>
		 
		<tr><td colspan="2"><input type="radio" name="toimitustapa" value="nouto" checked>  <label for="toimitustapa" style="color:white;">Nouto</label>
		<div><input type="radio" id="koti" name="toimitustapa" value="kotiinkuljetus" required><label for="toimitustapa" style="color:white;">Kotiinkuljetus</label>
		<br><br>
		<div class="reveal-if-active">
  		<p style="color:white;">Toimitusosoite:
  		<input type="text" name="osoite" class="require-if-active" data-require-pair="#koti" size="40" pattern=".{6,40}" <%if(osoite != null){ %> value="<%=osoite%>" <%} %>></p>
  		<p style="color:white;">Sähköposti:
  		<input type="text" name="sposti" class="require-if-active" data-require-pair="#koti" size="40" pattern=".{6,40}"  <%if(sposti != null){ %> value="<%=sposti%>" <%} %>></p><br>
  		</div><br>
  		  </div>
  </table>
 
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
		  	
		    $(".require-if-active1").each(function() {
		    	
		      var el = $(this);
		      if ($(el.data("require-pair")).is(":checked")) {
		        el.prop("required", true);
		      } else {
		        el.prop("required", false);
		        el.prop("value=null")
		      }
		    });
		    
		  }
		  
		};

		FormStuff.init();
</script>
		
	<table class="" width="auto" border="1" align="center">		
		
		
		
		
		<tr style="width=350px;">
			
			<th style="border-bottom: solid 1px grey;">PIZZAT</th>
			<th style="border-bottom: solid 1px grey;">LISÄTÄYTTEET</th>
			<th style="border-bottom: solid 1px grey;">KAPPALEHINTA</th>
			<th style="border-bottom: solid 1px grey;">MAUSTEET</th>
			<th style="border-bottom: solid 1px grey;">KPL</th>
			<th></th>
			
			
			<!--  <th>TOIMINNOT</th>-->
			
		</tr>
		
		<%for(int j = 0; j<pizzat.size();j++){%>
			<%for(int i = 0; i <ostoskori.getKoko(); i++) {%>
				<%if(pizzat.get(j).getId() == ostoskori.getOstoskori().get(i).getTuote().getId()){ %>
					<tr>
						<td><div class=""><%=ostoskori.getTuote(i).getTuote().getNimi()%></div></td>
						<td><div class=""><% if(ostoskori.getTuote(i).getLisataytteet() != null){%><%for(int n = 0; n<ostoskori.getTuote(i).getLisataytteet().size(); n++){ %><%=ostoskori.getTuote(i).getLisataytteet().get(n).getTayte_nimi()%> <%}%><%} %> </div></td>
						<td><div class="tilauslista"><%=nf.format(ostoskori.getTuote(i).getHinta())%>&euro;</div></td>
							<%summa+=(ostoskori.getTuote(i).getHinta()*ostoskori.getTuote(i).getLkm());%>
						<td style="text-align: center;"><div class="">
							<%if(ostoskori.getTuote(i).getOregano() != 0){%>
								<img src="Kuvia/oregano.png" title="Oregano" alt="Oregano" style="width:16px;height:16px;" />
								<!--Icon made by Freepik from www.flaticon.com-->
							<%}%>
							<%if(ostoskori.getTuote(i).getvSipuli() != 0){%>
								<img src="Kuvia/garlic.png" title="Valkosipuli" alt="Valkosipuli" style="width:16px;height:16px;" />
								<!--Icon made by Madebyoliver from www.flaticon.com-->
							<%}%>
						</div></td>
						<td><div class="tilauslista"><%=ostoskori.getTuote(i).getLkm()%> kpl</div></td>
						<td><a href="poistaPizzaKorista?paikkaID=<%=i%>&action=poista" class="submit-button">
						<img src="Kuvia/minus2.png" title="Poista" alt="Poista" style="width:16px;height:16px;" />
						</a>
						<a href="poistaPizzaKorista?paikkaID=<%=i%>&action=lisaaPizza" class="submit-button">
						<img src="Kuvia/plus.png" title="Lisää" alt="Lisaa" style="width:16px;height:16px;" />
						<!--Icon made by Dave Gandy from www.flaticon.com-->
						</a></td>
					</tr>
				<%}%>
			<%} %>
		<%} %>
		
		<tr></tr> <!-- Tilaa loppejen väliin! -->
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		
		<tr style="width=350px; border-bottom: solid 1px grey;">
			
			<th style="border-bottom: solid 1px grey;">JUOMAT</th>
			<th style="border-bottom: solid 1px grey;">KOKO</th>
			<th style="border-bottom: solid 1px grey;">KAPPALEHINTA</th>
			<th style="border-bottom: solid 1px grey;"></th>
			<th style="border-bottom: solid 1px grey;">KPL</th>
			
			
			
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
						<td></td>
						<td><div class="tilauslista"><%=ostoskori.getTuote(i).getLkm()%> kpl</div></td>
						<td><a href="poistaPizzaKorista?paikkaID=<%=i%>&action=poista" class="submit-button">
						<img src="Kuvia/minus2.png" title="Poista" alt="Poista" style="width:18px;height:18px;" />
						</a>
						<a href="poistaPizzaKorista?paikkaID=<%=i%>&action=lisaaJuoma" class="submit-button">
						<img src="Kuvia/plus.png" title="Lisää" alt="Lisaa" style="width:18px;height:18px;" />
						<!--Icon made by Dave Gandy from www.flaticon.com-->
						</a></td>								
					</tr>
				<%}%>
			<%} %>
		<%} %>
		
		
		</table><br>
		<p style="color:white;"><u>SUMMA:</u> <%=nf.format(summa) %>&euro;</p>
		<a href="poistaPizzaKorista?action=poistaKori" style="text-decoration: underline;" class="submit-button" >Tyhjennä kori</a>
		<input type="submit" name="submit-button"class="submit-button" value="Vahvista Tilaus" />
		
		
		
    </span>
    </form>
    <script>
		document.getElementById("vahvista").onsubmit = function() {myFunction()};
		
		function myFunction() {
		    alert("Tilaus lähetetty");
		}
	</script>
		<%}else{%>
		<p>Ostoskori on tyhjä</p>
		<%} %>
     
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

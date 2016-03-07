<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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
    
    



</body>
</html>
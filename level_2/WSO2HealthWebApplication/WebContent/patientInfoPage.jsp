<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style>
        p.serif{font-family:"Courier New", Courier, monospace;}		
		table.resultTable{
		border-collapse:collapse;
		}
		td.a,th.a 
		{
		border:1px solid black;
		}
    </style>
    <title>Patient Search Results</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>
            <form>
                <div style="letter-spacing:12px;" align="center">Patient Search Results</div>
            </form>
        </td>
    </tr>
    </tbody>
</table>
<div style="opacity:0.5;position:absolute;right:50px;width:300px;height:500px;background-color:#40B3DF;"></div>
<img src="./images/all-in-one.png" style="float:right" align="top" alt="" width="250" height="250">
<div style="font-family:verdana;padding:20px;border-radius:100px;border:50px solid #EE872A;">
    <div style="opacity:0.3;position:absolute;right:120px;width:400px;height:600px;background-color:#8AC007;"></div>
<table>
    <tbody>

    <tr>
        <td></td>
        <td></td>
        <td><h3>Patients having ID 
            <c:out value=" ${requestScope.patientNumber}"/>
            </h3>
        </td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>
          <%-- Using JSTL forEach and out to loop a list and display items in table --%>
		<table class="resultTable">
		<tbody>
		<tr>
		<th class= "a">First Name</th>
		<th class= "a">Last Name</th>
		<th class= "a">Phone Num</th>
		<th class= "a">Street</th>
		<th class= "a">City</th>
		<th class= "a">Country</th>
		</tr>
		<c:forEach items="${requestScope.patientList}" var="patientListVar">
		<tr>
			<td class= "a"><c:out value="${patientListVar.patientFirstName}"></c:out></td>
			<td class= "a"><c:out value="${patientListVar.patientLastName}"></c:out></td>
			<td class= "a"><c:out value="${patientListVar.phone}"></c:out></td>
			<td class= "a"><c:out value="${patientListVar.streetname}"></c:out></td>
			<td class= "a"><c:out value="${patientListVar.city}"></c:out></td>
			<td class= "a"><c:out value="${patientListVar.country}"></c:out></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
        </td>
    </tr>
    <tr>
    	<td></td>
    	<td></td>
    	<td></td>
    </tr>
    <tr>
    	<td></td>
    	<td></td>
    	<td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>            
        <form>
            <li><a href="landingPage.html">Home</a></li>
        </form>
        </td>
    </tr>
    </tbody>
</table>
</div>
</body>
</html>
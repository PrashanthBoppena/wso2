<%@page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<html>
<head>
    <style>
        p.serif{font-family:"Courier New", Courier, monospace;}
    </style>
    <title>Register a Patient</title>
</head>
<table>
    <tbody>
    <tr>
        <td>
            <form>
                <div style="letter-spacing:12px;" align="center">Register New Patient</div>
            </form>
        </td>
    </tr>
    </tbody>
</table>
<div style="opacity:0.5;position:absolute;right:50px;width:300px;height:500px;background-color:#40B3DF;"></div>
<img src="./images/all-in-one.png" style="float:right" align="top" alt="" width="250" height="250">
<div style="font-family:verdana;padding:20px;border-radius:100px;border:50px solid #EE872A;">
    <div style="opacity:0.3;position:absolute;right:120px;width:400px;height:600px;background-color:#8AC007;"></div>
<form action="Register">
<table>
    <tbody>
    <tr>
        <td>Patient #</td>
        <td>:</td>
        <td><input type="text" name="patientNumber" size="20"></td>
    </tr>
    <tr>
        <td>Patient Last Name</td>
        <td>:</td>
        <td><input type="text" name="patientLastName" size="20"></td>
    </tr>
    <tr>
        <td>Patient First Name</td>
        <td>:</td>
        <td><input type="text" name="patientFirstName" size="20"></td>
    </tr>
    <tr>
        <td>Contact Number</td>
        <td>:</td>
        <td><input type="text" name="phone" size="20"></td>
    </tr>
    <tr>
        <td>City</td>
        <td>:</td>
        <td><input type="text" name="city" size="20"></td>
    </tr>
    <tr>
        <td>Street name</td>
        <td>:</td>
        <td><input type="text" name="streetname" size="20"></td>
    </tr>
    <tr>
        <td>Country</td>
        <td>:</td>
        <td><input type="text" name="country" size="20"></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td><input type="submit" name="submit" value="Register">
            <input type="reset" value="Reset"></td>
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
</form>
</div>
</body>
</html>
<%-- 
    Document   : RegisterCustomer
    Created on : 27-Jun-2017, 19:04:33
    Alba Airways application M813-TMA02-RegisterCustomer
    https://github.com/jc184/M813-TMA02-RegCustomer
    @author james chalmers Open University F6418079
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>AlbaAirways</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <script type="text/javascript" src="scripts/custvalidation.js"></script>
        <script type="text/javascript" src="scripts/datepickercontrol.js"></script>
        <link type="text/css" rel="stylesheet" href="styles/datepickercontrol.css"/>

    </head>
    <body>
        <!-- UNCOMMENT HTML WITH THIS -->
        <div class="outer">
            <div class="header"><h1>AlbaAirways</h1></div>

            <div class="box"><h2>New Customer Registration</h2>
                <p>Enter your details in the form below and click 'add' to register</p>
                <form name="form" action="CustomerServlet" method="POST">
                    <table cellspacing="8" border="0">
                        <!-- UNCOMMENT TO USE HTML BELOW -->
                        <tr>
                            <td align="left"><p><label for="title">Title:</label></p></td>
                            <td><input type="text" name="title" id="title" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="firstName">Forename:</label></p></td>
                            <td><input type="text" name="firstName" id="firstName" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="surname">Surname:</label></p></td>
                            <td><input type="text" name="surname" id="surname" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="mobileNo">Mobile No:</label></p></td>
                            <td><input type="text" name="mobileNo" id="mobileNo" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="homePhoneNumber">HomePhone No:</label></p></td>
                            <td><input type="text" name="homePhoneNumber" id="homePhoneNumber" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="emailAddress">Email Address:</label></p></td>
                            <td><input type="text" name="emailAddress" id="emailAddress" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="loginName">Login name:</label></p></td>
                            <td><input type="text" name="loginName" id="loginName" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="loginPassword">Login password:</label></p></td>
                            <td><input type="password" name="loginPassword" id="loginPassword" value="" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="dateOfBirth">Date of Birth:</label></p></td>
                            <td><input type="text" name="dateOfBirth" id="dateOfBirth" value="" datepicker="true" datepicker_format="YYYY-MM-DD" /></td>
                        </tr>
                        <tr>
                            <td><p>Add new Customer</p></td>
                            <td><input type="submit" value="add" name="submit" style="width:75px" onclick="validate(this.form);return false;" /><br /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>

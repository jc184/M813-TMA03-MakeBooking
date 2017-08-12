<%-- 
    Document   : LoginPage
    Created on : 09-Aug-2017, 15:45:06
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>AlbaAirways Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <!-- <script type="text/javascript" src="scripts/userlogin.js"></script>  -->
    </head>
    <body>
        <div class="header"><h1>Alba Airways</h1></div>
        <div class="box"><h2>Login Page</h2>


            <br />
            <script type='text/javascript'>
                function validateform2() {
                    'use strict';
                    var name = document.userlogin.loginName.value;
                    var password = document.userlogin.loginPasswd.value;

                    if (name === null || name === "") {
                        alert("Please enter your username");
                        document.userlogin.loginName.focus();
                        return false;
                    } else if (password === null || password === "") {
                        alert("Please enter your password");
                        document.userlogin.loginPasswd.focus();
                        return false;
                    } else {
                        document.userlogin.submit();
                    }
                }
            </script>
            <form name="userlogin" method="POST" action="LoginServlet" onsubmit="return validateform2()">
                <h3>Customer Login with login name and login password</h3>
                <p> To login, enter details below:</p>
                <table cellspacing="8" border="0">
                    <tr>
                        <td align="left"><p>Login name:</p></td>
                        <td><input type="text" name="loginName" id="loginName"/></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Login password:</p></td>
                        <td><input type="password" name="loginPassword" id="loginPassword"/></td>
                    </tr>
                    <tr>
                        <td align="left"><input type="submit" value="userlogin" name="submit" style="width:75px" /><br /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

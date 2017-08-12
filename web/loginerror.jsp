<%-- 
    Document   : loginerror
    Created on : 28-Jun-2017, 12:21:34
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="box">
            <h1>Alba Airways</h1><br />
            <h3>Login Form - Error</h3>
            <h3>You did not log in successfully.</h3><br />
            <p>Please check your username and password and try again</p><br />
            <table cellspacing="8" border="0">
                <td><p>Back to previous page</p></td>
                <td><input type="button" value="back" onclick="location.href = document.referrer; return false;"" style="width:75px" /></td>
            </table>
        </div>
    </body>
</html>

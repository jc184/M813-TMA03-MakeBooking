<%-- 
    Document   : customerexists.jsp
    Created on : 27-Jun-2017, 20:11:48
    Alba Airways application M813-TMA02-RegisterCustomer
    https://github.com/jc184/M813-TMA02-RegCustomer
    @author james chalmers Open University F6418079
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <div class="box"><h3><%= request.getAttribute("msg")%></h3><br />
        <table>
            <tr>
                <!-- UNCOMMENT HTML WITH THIS -->
                <td><p>Back to previous page</p></td>
                <td><input type="button" value="back" onclick="location.href = document.referrer; return false;" style="width:75px" /></td>
            </tr>
        </table>
        <table>
            <tr>
                <!-- COMMENT HTML WITH THIS -->
                <td><form name="backtologin" action="CustomerServlet" method="POST"> <p>Back to Login</p></td>
                <td><input type="submit" value="login" name="submit" style="width:75px" /></td></form>
            </tr>
        </table>
    </div>
</html>

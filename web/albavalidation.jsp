<%-- 
    Document   : albavalidation
    Created on : 10-Aug-2017, 21:23:40
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="header"><h1>AlbaAirways</h1></div>
    <div class="box"><h3>Sorry, but you entered your details incorrectly.</h3><br />
    <p><%= request.getAttribute("msg")%></p><br />
    <table>
        <tr>
            <td>
                <!-- UNCOMMENT HTML WITH THIS -->
                <td><p>Back to previous page</p></td>
                <td><input type="button" value="back" onclick="location.href = document.referrer; return false;" style="width:75px" /></td>
            </td>
        </tr>
    </table>
</div>
</html>

<%-- 
    Document   : flightinfopage
    Created on : 29-Jun-2017, 15:18:11
    Alba Airways application M813-TMA02-ChooseFlight
    @author james chalmers F6418079
    @version 1.0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Flight" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="outer">
            <div class="header"><h1>Alba Airways</h1></div>
            <div class="box"><h2>Flight Info page: Outbound Flights</h2>

                <% Flight outboundFlight = (Flight) request.getAttribute("outboundFlight");%>
                <table cellspacing="8" border="0">
                    <tr>
                        <td align="left"><p>Flight Id:</p></td>
                        <td><p><%= outboundFlight.getFlightId()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Flight Date:</p></td>
                        <td><p><%= outboundFlight.getFlightDate()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Leave Date Time:</p></td>
                        <td><p><%= outboundFlight.getLeaveDateTime()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Arrive Date Time:</p></td>
                        <td><p><%= outboundFlight.getArrivalDateTime()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Flight Status:</p></td>
                        <td><p><%= outboundFlight.getFlightStatus()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Gate Number:</p></td>
                        <td><p><%= outboundFlight.getGateNumber()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Stops:</p></td>
                        <td><p><%= outboundFlight.getStops()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Route ID:</p></td>
                        <td><p><%= outboundFlight.getRouteRouteId()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Aircraft ID:</p></td>
                        <td><p><%= outboundFlight.getAircraftAircraftId()%></p></td>
                    </tr>
                </table> 
            </div>
            <table cellspacing="8" border="0">
                <form name="goto" action="BookingServlet">
                    <tr>
                        <td align="left"><p>Make booking page:</p></td>
                        <td><input type="submit" value="home" name="submit" style="width:75px" onclick="" /></td>
                    </tr>
                </form>
                <tr>
                    <td>
                        <input type="button" value="back" onclick="location.href = document.referrer; return false;" style="width:75px" />
                    </td>
                </tr>
            </table>
    </body>
</html>

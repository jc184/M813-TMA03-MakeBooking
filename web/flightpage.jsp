<%-- 
    Document   : flightpage
    Created on : 29-Jun-2017, 11:35:51
    Alba Airways application M813-TMA02-ChooseFlight
    @author james chalmers F6418079
    @version 1.0
--%>

<%@page import="model.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Info Page</title>
    </head>
    <body>
        <div class="outer">
            <div class="header"><h1>Alba Airways</h1></div>
            <div class="box"><h2>View all outbound flights</h2>
                <% ArrayList<Flight> outboundFlightStore = (ArrayList<Flight>) request.getAttribute("outboundFlightStore");%>
                <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                    <!-- UNCOMMENT HTML WITH THIS -->      
                    <thead>
                        <tr>
                            <th><h3>Flight Id</h3></th>
                            <th><h3>Flight Date</h3></th>
                            <th><h3>Leave Datetime</h3></th>
                            <th><h3>Arrival DateTime</h3></th>
                            <th><h3>Flight Status</h3></th>
                            <th><h3>Gate Number</h3></th>
                            <th><h3>Stops</h3></th>
                            <th><h3>Route Id</h3></th>
                            <th><h3>Aircraft Id</h3></th>
                            <th><h3>Select</h3></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Flight outboundFlight : outboundFlightStore) {%>
                        <tr>
                            <td><%= outboundFlight.getFlightId()%></td>
                            <td><%= outboundFlight.getFlightDate()%></td>
                            <td><%= outboundFlight.getLeaveDateTime()%></td>
                            <td><%= outboundFlight.getArrivalDateTime()%></td>
                            <td><%= outboundFlight.getFlightStatus()%></td>
                            <td><%= outboundFlight.getGateNumber()%></td>
                            <td><%= outboundFlight.getStops()%></td>
                            <td><%= outboundFlight.getRouteRouteId()%></td>
                            <td><%= outboundFlight.getAircraftAircraftId()%></td>
                            <td><form name="selectOutboundFlight" action="BookingServlet">
                                    <input type="hidden" value="<%= outboundFlight.getFlightId()%>" name="outboundFlightId" />
                                    <input type="submit" value="select" name="submit" /></form></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>

            </div>
            <div class="box"><h2>View all return flights</h2>
                <% ArrayList<Flight> returnFlightStore = (ArrayList<Flight>) request.getAttribute("returnFlightStore");%>
                <table cellpadding="0" cellspacing="0" border="0" id="table" class="sortable">
                    <!-- UNCOMMENT HTML WITH THIS -->      
                    <thead>
                        <tr>
                            <th><h3>Flight Id</h3></th>
                            <th><h3>Flight Date</h3></th>
                            <th><h3>Leave Datetime</h3></th>
                            <th><h3>Arrival DateTime</h3></th>
                            <th><h3>Flight Status</h3></th>
                            <th><h3>Gate Number</h3></th>
                            <th><h3>Stops</h3></th>
                            <th><h3>Route Id</h3></th>
                            <th><h3>Aircraft Id</h3></th>
                            <th><h3>Select</h3></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Flight returnFlight : returnFlightStore) {%>
                        <tr>
                            <td><%= returnFlight.getFlightId()%></td>
                            <td><%= returnFlight.getFlightDate()%></td>
                            <td><%= returnFlight.getLeaveDateTime()%></td>
                            <td><%= returnFlight.getArrivalDateTime()%></td>
                            <td><%= returnFlight.getFlightStatus()%></td>
                            <td><%= returnFlight.getGateNumber()%></td>
                            <td><%= returnFlight.getStops()%></td>
                            <td><%= returnFlight.getRouteRouteId()%></td>
                            <td><%= returnFlight.getAircraftAircraftId()%></td>
                            <td><form name="selectReturnFlight" action="BookingServlet">
                                    <input type="hidden" value="<%= returnFlight.getFlightId()%>" name="returnFlightId" />
                                    <input type="submit" value="select" name="submit" /></form></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

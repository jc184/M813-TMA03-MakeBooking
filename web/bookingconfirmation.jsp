<%-- 
    Document   : scotiabookingconfirmation
    Created on : 12-Nov-2010, 12:36:06
    Author     : James Chalmers
    Iteration  : 5.0
--%>

<%@page import="model.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = LoginPage.jsp");
%>
<html>
    <head>
        <title>AlbaAirways</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>

    </head>
    <body>

        <!-- UNCOMMENT HTML WITH THIS -->
        <div class="outer">
            <div class="header"><h1>AlbaAirways</h1>


                <div class="box"><h2>Booking Confirmation</h2><br />
                    <div id="printconfirmslip">
                        <% Booking booking = (Booking) request.getAttribute("booking");%>
                        <h3>Thankyou. Your Booking has been made</h3>
                        <p>Your booking details are as follows:</p>
                        <p>Booking number is: <%= booking.getBookingId()%></p>
                        <p>Customer number is: <%= booking.getCustomerId()%></p>
                        <p>No of Adults: <%= booking.getNoOfAdults()%></p>
                        <p>No of Children: <%= booking.getNoOfChildren()%></p>
                        <p>No of Infants: <%= booking.getNoOfInfants()%></p>
                        <p>Outbound FlightId: <%= booking.getOutboundFlightID()%></p>
                        <p>Return FlightId: <%= booking.getReturnFlightID()%></p>

                        <table cellspacing="6" border="0" id="table" class="" >
                            <tr>
                                <td align="left"><p>Print confirmation slip:</p></td>
                                <td><input type="reset" value="print" name="submit" style="width:75px" onclick="window.print()" /></td>
                            </tr>
                            <tr>

                                <td align="left"><p>Back to previous page:</p></td>
                                <td><input type="button" value="back" onclick="location.href = document.referrer; return false;" style="width:75px" /></td>

                            </tr>
                        </table>
                        <!--  <table cellspacing="6" border="0" id="table" class="" >
                              <tr>
                                   <form name="custidfilter" action="CustomerServlet">
                                          <td align="left" ><p>View booking(s):</p></td>
                                          <td><input type="submit" value="show" name="submit" style="width:75px" onclick=""/></td>
                                          <td><input type="hidden" name="customerID"  value="" /></td>
                                   </form>
                              </tr>
                          </table>-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>



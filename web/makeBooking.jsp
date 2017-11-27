<%-- 
    Document   : makeBooking
    Created on : 09-Aug-2017, 16:02:53
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- ACKNOWLEDGEMENTS
 * DatePickerControl.v.1.1.7
 * By Hugo Ortega_Hernandez - hugorteg _no_spam_ at gmail dot com
 * License: LGPL http://www.gnu.org/licenses/lgpl.html
 * (i.e., use this code as you wish, just keep it free)
 * Provided as is, without any warranty.
 * Feel free to use this code, but don't remove this disclaimer please.
-->

<%
    int timeout = session.getMaxInactiveInterval();
    response.setHeader("Refresh", timeout + "; URL = LoginPage.jsp");
%>
<html>
    <head>
        <title>AlbaAirways</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <script type="text/javascript" src="scripts/datepickercontrol.js"></script>
        <!-- <script type="text/javascript" src="scripts/bookingvalidation.js"></script>UNCOMMENT HTML WITH THIS -->
        <link type="text/css" rel="stylesheet" href="styles/datepickercontrol.css"/>

    </head>
    <body>

        <!-- UNCOMMENT HTML WITH THIS -->
        <div class="outer">
            <div class="header"><h1>AlbaAirways</h1>
                <h3>You have logged in successfully</h3></div>

            <div class="box"><h2>New Booking</h2>
                <p>Enter your details in the form below and click 'add' to make a booking</p>
                <form name="form" action="BookingServlet" method="POST">
                    <table>
                        <!-- UNCOMMENT TO USE HTML BELOW -->
                        <tr>
                            <td align="left"><p><label for="customerId">Customer No:</label></p></td>
                            <td><input type="text" name="customerId" id="customerId" value="<%= session.getAttribute("customerId")%>" readonly="true" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="noOfAdults">Number of Adults:</label></p></td>
                            <td><select name="noOfAdults" id="noOfAdults" value="">
                                    <option selected>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="noOfChildren">Number of Children:</label></p></td>
                            <td><select name="noOfChildren" id="noOfChildren" value="">
                                    <option selected>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="noOfInfants">Number of Infants:</label></p></td>
                            <td><select name="noOfInfants" id="noOfInfants" value="">
                                    <option selected>0</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="ticketType">Ticket Type:</label></p></td>
                            <td><select name="ticketType" id="ticketType" value="">
                                    <option selected>RETURN</option>
                                    <option>SINGLE</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="outboundFlightDate">Date of Travel (yyyy-mm-dd):</label></p></td>
                            <td><input type="text" name="outboundFlightDate" id="outboundFlightDate" value="" datepicker="true" datepicker_format="YYYY-MM-DD"/></td>
                        </tr>
                        <tr>
                            <td align="left"><p>Show daily outbound flights:</p></td>
                            <td><input type="submit" value="outbound flights" name="submit" style="width:115px" onclick="validate(this.form);return false;"/></td>
                            <!--    -->
                        </tr>
                        <tr>
                            <td align="left"><p><label for="outboundFlightId">Outbound Flight Id: </label></p></td>
                            <td><input type="text" name="outboundFlightId" id="outboundFlightId" value="<%= session.getAttribute("outboundFlightId")%>" readonly="true" /></td>
                        </tr>
                        <tr>
                            <td align="left"><p><label for="returnFlightDate">Date of Travel (yyyy-mm-dd):</label></p></td>
                            <td><input type="text" name="returnFlightDate" id="returnFlightDate" value="" datepicker="true" datepicker_format="YYYY-MM-DD"/></td>
                        </tr>
                        <tr>
                            <td align="left"><p>Show daily return flights:</p></td>
                            <td><input type="submit" value="return flights" name="submit" style="width:115px" onclick="validate(this.form);return false;"/></td>
                            <!--    -->
                        </tr>
                        <tr>
                            <td align="left"><p><label for="returnFlightId">Return Flight Id: </label></p></td>
                            <td><input type="text" name="returnFlightId" id="returnFlightId" value="<%= session.getAttribute("returnFlightId")%>" readonly="true" /></td>
                        </tr>
                        <tr>
                            <td><p>Choose your Outbound Seat(s)</p></td>
                            <td><input type="submit" value="choose outbound" name="submit" style="width:115px" /><br /></td>
                            <td><%= session.getAttribute("outboundSeatList") %></td>
                        </tr>
                        <tr>
                            <td><p>Choose your Return Seat(s)</p></td>
                            <td><input type="submit" value="choose return" name="submit" style="width:115px" /><br /></td>
                            <td><%= session.getAttribute("returnSeatList") %></td>
                        </tr>

                        <tr>
                            <td><p>Add new Booking</p></td>
                            <td><input type="submit" value="add" name="submit" style="width:75px" onclick="validate(this.form);return false;" /><br /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>

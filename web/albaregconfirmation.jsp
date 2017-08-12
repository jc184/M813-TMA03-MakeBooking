<%-- 
    Document   : albaregconfirmation
    Created on : 27-Jun-2017, 20:12:27
    Alba Airways application M813-TMA02-RegisterCustomer
    https://github.com/jc184/M813-TMA02-RegCustomer
    @author james chalmers Open University F6418079
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String customerId = request.getParameter("customerId");
    String title = request.getParameter("title");
    String firstName = request.getParameter("firstName");
    String surname = request.getParameter("surname");
    String mobileNo = request.getParameter("mobileNo");
    String homePhoneNumber = request.getParameter("homePhoneNumber");
    String emailAddress = request.getParameter("emailAddress");
    String loginName = request.getParameter("loginName");
    String loginPassword = request.getParameter("loginPassword");
    String dateOfBirth = request.getParameter("dateOfBirth");

    session.setAttribute("customerId", customerId);
    session.setAttribute("title", title);
    session.setAttribute("firstName", firstName);
    session.setAttribute("surname", surname);
    session.setAttribute("mobileNo", mobileNo);
    session.setAttribute("homePhoneNumber", homePhoneNumber);
    session.setAttribute("emailAddress", emailAddress);
    session.setAttribute("loginName", loginName);
    session.setAttribute("loginPassword", loginPassword);
    session.setAttribute("dateOfBirth", dateOfBirth);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="box"><h2>Registration Confirmation</h2><br /><!-- ***ADDS THE REG TWICE IF PRESS REFRESH BUTTON*** -->
            <h3>Thankyou. Your details have been registered</h3>
            <p>Your registration details are as follows:</p>
            <p>Customer registration number is <%= request.getAttribute("customerId")%></p>
            <p>Title: <%= session.getAttribute("title")%></p>
            <p>Name: <%= session.getAttribute("firstName")%> <%= session.getAttribute("surname")%></p>
            <p>Mobile number is <%= session.getAttribute("mobileNo")%></p>
            <p>Home Phone number is <%= session.getAttribute("homePhoneNumber")%></p>
            <p>Email address <%= session.getAttribute("emailAddress")%></p>
            <p>Username is <%= session.getAttribute("loginName")%></p>
            <p>Password is <%= session.getAttribute("loginPassword")%></p>
            <p>Date of Birth is <%= session.getAttribute("dateOfBirth")%></p><br />
        </div>
    </body>
</html>

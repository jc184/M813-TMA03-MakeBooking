/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BookingManager;
import model.Flight;
import model.FlightManager;
import model.Seat;

/**
 *
 * @author james
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    BookingManager bookingManager;
    FlightManager flightManager;

    @Override
    public void init() throws ServletException {
        bookingManager = new BookingManager();
        flightManager = new FlightManager();
    }

//    @Override
//    public ServletContext getServletContext() {
//        return super.getServletContext(); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int bookingTotal = 0;
        String url = "/makeBooking.jsp";
        Date sdfOutboundFlightDate = null;
        Date sdfReturnFlightDate = null;
        HttpSession session = request.getSession();
        int noOfAdults = 0;
        int noOfChildren = 0;
        int noOfInfants = 0;

        String submit = request.getParameter("submit");
        if (submit != null && submit.length() > 0) {
            if (submit.equals("add")) {
                int customerId = Integer.parseInt(request.getParameter("customerId").trim());
                noOfAdults = Integer.parseInt(request.getParameter("noOfAdults").trim());
                noOfChildren = Integer.parseInt(request.getParameter("noOfChildren").trim());
                noOfInfants = Integer.parseInt(request.getParameter("noOfInfants").trim());
                int outboundFlightId = Integer.parseInt(request.getParameter("outboundFlightId").trim());
                int returnFlightId = Integer.parseInt(request.getParameter("returnFlightId").trim());

                bookingManager.addBooking(customerId, noOfAdults, noOfChildren, noOfInfants, outboundFlightId, returnFlightId);

            } else if (submit.equals("outbound flights")) {
                String outboundFlightDate = request.getParameter("outboundFlightDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                sdfOutboundFlightDate = formatter.parse(outboundFlightDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdfOutboundFlightDate);
                cal.getTime();
                Date today = new Date();
                Date nextMonth = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 30));
                if (outboundFlightDate.equals("")) {
                    String msg = "Click on the Back button in your browser and re-enter your details.";
                    request.setAttribute("msg", msg);
                    url = "/albavalidation.jsp";
                } else if (sdfOutboundFlightDate.before(nextMonth)) {
                    String msg = "Click on the Back button in your browser and re-enter your details. You must book at least one month in advance.";
                    request.setAttribute("msg", msg);
                    url = "/albavalidation.jsp";
                }

                request.setAttribute("outboundFlightStore", flightManager.getFilteredFlights(sdfOutboundFlightDate));

                request.setAttribute("sdfOutboundFlightDate", sdfOutboundFlightDate);
                request.setAttribute("cal", formatter.format(cal.getTime()));

                url = "/outboundflightpage.jsp";
            } else if (submit.equals("return flights")) {
                String returnFlightDate = request.getParameter("returnFlightDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                sdfReturnFlightDate = formatter.parse(returnFlightDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdfReturnFlightDate);
                cal.getTime();
                Date today = new Date();
                Date nextMonth = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 30));
                if (returnFlightDate.equals("")) {
                    String msg = "Click on the Back button in your browser and re-enter your details.";
                    request.setAttribute("msg", msg);
                    url = "/albavalidation.jsp";
                } else if (sdfReturnFlightDate.before(nextMonth)) {
                    String msg = "Click on the Back button in your browser and re-enter your details. You must book at least one month in advance.";
                    request.setAttribute("msg", msg);
                    url = "/albavalidation.jsp";
                }

                request.setAttribute("returnFlightStore", flightManager.getFilteredFlights(sdfReturnFlightDate));

                request.setAttribute("sdfReturnFlightDate", sdfReturnFlightDate);
                request.setAttribute("cal", formatter.format(cal.getTime()));

                url = "/returnflightpage.jsp";
            } else if (submit.equals("select outbound")) {
                int outboundFlightId = Integer.parseInt(request.getParameter("outboundFlightId"));

                Flight outboundFlight = flightManager.getFlight(outboundFlightId);
                request.setAttribute("outboundFlight", outboundFlight);
                request.setAttribute("outboundFlightId", outboundFlight.getFlightId());
                //int outboundFlightId = (int) request.getAttribute("outboundFlightId");
                session.setAttribute("outboundFlightId", outboundFlightId);
                url = "/outboundflightinfopage.jsp";
            } else if (submit.equals("select return")) {
                int returnFlightId = Integer.parseInt(request.getParameter("returnFlightId"));

                Flight returnFlight = flightManager.getFlight(returnFlightId);
                request.setAttribute("returnFlight", returnFlight);
                request.setAttribute("returnFlightId", returnFlight.getFlightId());
                //int returnFlightId = (int) request.getAttribute("returnFlightId");
                session.setAttribute("returnFlightId", returnFlightId);
                url = "/returnflightinfopage.jsp";
            } else if (submit.equals("choose")) {
//                int seatTotal = (int) session.getAttribute("bookingTotal");
//                List<Seat> seatList = new ArrayList<>();
//                for (int i = 0; i < seatTotal; i++) {
//                    Seat seat = null;
//                    seatList.add(seat);
//                }
                noOfAdults = Integer.parseInt(request.getParameter("noOfAdults"));
                noOfChildren = Integer.parseInt(request.getParameter("noOfChildren"));
                noOfInfants = Integer.parseInt(request.getParameter("noOfInfants"));
                bookingTotal = noOfAdults + noOfChildren + noOfInfants;
                session.setAttribute("bookingTotal", bookingTotal);
                request.setAttribute("bookingTotal", bookingTotal);
                url = "/indexRevB.jsp";
                RequestDispatcher rd = request.getRequestDispatcher("SeatingServlet");
                if (rd != null) {
                    rd.forward(request, response);
                }
            } else if (submit.equals("home")) {
                url = "/makeBooking.jsp";
            }
        }

//        RequestDispatcher dispatcher
//                = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int noOfAdults = 0;
//        int noOfChildren = 0;
//        int noOfInfants = 0;
//        int bookingTotal = 0;
//        String submit = request.getParameter("submit");
//        if (submit != null && submit.length() > 0) {
//            if (submit.equals("choose")) {
////                int seatTotal = (int) session.getAttribute("bookingTotal");
////                List<Seat> seatList = new ArrayList<>();
////                for (int i = 0; i < seatTotal; i++) {
////                    Seat seat = null;
////                    seatList.add(seat);
////                }
//                noOfAdults = Integer.parseInt(request.getParameter("noOfAdults").trim());
//                noOfChildren = Integer.parseInt(request.getParameter("noOfChildren").trim());
//                noOfInfants = Integer.parseInt(request.getParameter("noOfInfants").trim());
//                bookingTotal = noOfAdults + noOfChildren + noOfInfants;
//                //session.setAttribute("bookingTotal", bookingTotal);
//                request.setAttribute("bookingTotal", bookingTotal);
//                //url = "/indexRevB.jsp";
//                RequestDispatcher rd = request.getRequestDispatcher("/SeatingServlet/indexRevB.jsp");
//                rd.forward(request, response);
//            }
//        }
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

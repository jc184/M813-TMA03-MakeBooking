/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Booking;
import model.PassengerEnum;
import entities.Seat;
import model.SeatEnum;
import model.SeatTypeEnum;
import model.SeatManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Alba Airways application M813-TMA02-MakeBooking
 *
 * @author james chalmers Open University F6418079
 */
@WebServlet(name = "SeatingServlet", urlPatterns = {"/SeatingServlet"})
public class SeatingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    SeatManager seatManager;

    private int seatNumber;
    private final int aircraftId = 101;
    private SeatTypeEnum seatType;
    private String msg = "";
    private String url = "";
    private boolean isSeatBooked;
    private int economyCounter;
    private int firstClassCounter;
    List<Seat> outboundSeatList = new ArrayList<>();
    List<Seat> returnSeatList = new ArrayList<>();

    /*
     * Creates a new instance of SeatManager
     */
    @Override
    public void init() throws ServletException {
        seatManager = new SeatManager();
    }

    public void chooseSeat(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        HttpSession session = request.getSession();
//        if (seatManager.areAllSeatsBooked(seatManager.getSeats())) {
        String passenger = request.getParameter("Passenger");
        String outboundOrReturn = request.getParameter("OutboundOrReturn");
        int bookingTotal = (int) session.getAttribute("bookingTotal");
        String submit = request.getParameter("submit");

        isSeatBooked = false;
        if (seatManager.getSeats()[seatNumber] == true) {
//        if (seatManager.getAllSeatBookings()[seatNumber].equals(true)) {   
            msg = "This seat is already booked. Please choose another seat.";
            request.setAttribute("msg", msg);
            url = "/booked.jsp";
        } else {

            if (seatType == SeatTypeEnum.FIRSTCLASS) {

                if (firstClassCounter < 12) {

                    this.assignSeat(seatNumber, seatType.toString());
                    
                    if (outboundOrReturn.equals("Outbound")) {
                        while (outboundSeatList.size() < bookingTotal) {

//                            if (firstClassCounter + bookingTotal < 12) {
                                
                                outboundSeatList.add(seatManager.getSeat(seatNumber));
                                request.setAttribute("outboundSeatList", outboundSeatList);
                                session.setAttribute("outboundSeatList", outboundSeatList);

                                if (outboundSeatList.size() >= bookingTotal) {

                                    msg = "You have used up all your outbound seats in your booking";
                                    request.setAttribute("msg", msg);
                                    session.setAttribute("msg", msg);
                                    url = "/booked.jsp";
                                    break;

                                    //url = "/makeBooking.jsp";
                                } else {
                                    url = "/indexRevB.jsp";
                                } 
//                            } else {
//                                msg = "There are not enough seats available for your booking requirements. Please choose another flight.";
//                                request.setAttribute("msg", msg);
//                                url = "/booked.jsp";
//                            }

                        }
                        
                    } else if (outboundOrReturn.equals("Return")) {
//                    } else if (submit.equals("choose return")) {
                        while (returnSeatList.size() < bookingTotal) {
//                            if (firstClassCounter + bookingTotal < 12) {

                                returnSeatList.add(seatManager.getSeat(seatNumber));
                                request.setAttribute("returnSeatList", returnSeatList);
                                session.setAttribute("returnSeatList", returnSeatList);

                                if (returnSeatList.size() >= bookingTotal) {
                                    msg = "You have used up all your return seats in your booking";
                                    request.setAttribute("msg", msg);
                                    session.setAttribute("msg", msg);
                                    url = "/booked.jsp";
                                    break;
                                } else {

                                }
//                            } else {
//                                msg = "There are not enough seats available for your booking requirements. Please choose another flight.";
//                                request.setAttribute("msg", msg);
//                                url = "/booked.jsp";
//                            }
                        }
                        
                    }
                    isSeatBooked = true;
                    firstClassCounter++;

                } else {
                    msg = "All the First Class seats have been used up.";
                    request.setAttribute("msg", msg);
                    url = "/booked.jsp";
                }
            }

            msg = "Your Seat Booking.";

            for (SeatEnum seatEnum : SeatEnum.values()) {
                if (seatNumber == seatEnum.ordinal()) {
                    if (passenger.equals(PassengerEnum.ADULT.toString())) {
                        double seatCost = SeatEnum.valueOf(seatEnum.toString()).getAdultFare();
                        request.setAttribute("seatCost", seatCost);
                    } else if (passenger.equals(PassengerEnum.CHILD.toString())) {
                        double seatCost = SeatEnum.valueOf(seatEnum.toString()).getChildFare();
                        request.setAttribute("seatCost", seatCost);
                    } else if (passenger.equals(PassengerEnum.INFANT.toString())) {
                        double seatCost = SeatEnum.valueOf(seatEnum.toString()).getInfantFare();
                        request.setAttribute("seatCost", seatCost);
                    }
                    request.setAttribute("passengerType", passenger);
                    request.setAttribute("seatNumber", seatNumber + 1);
                    request.setAttribute("seatType", seatType);
                }
                request.setAttribute("msg", msg);
                request.setAttribute("seats", Arrays.toString(seatManager.getSeats()));

            }

            url = "/message.jsp";
        }
//        } else {
//            msg = "The Flight is fully booked. Please choose another Flight.";
//            request.setAttribute("msg", msg);
//            url = "/booked.jsp";
//        }
        //url = "/indexRevB.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        return;
    }

    public boolean[] assignSeat(int seatNumber, String seatType) throws ClassNotFoundException {
        seatManager.getSeats()[seatNumber] = true;
        Booking bookingId = null;
        seatManager.addSeatReservation(seatNumber, aircraftId, seatType, bookingId, true);
        seatManager.getSeat(seatNumber);
        return seatManager.getSeats();
    }

    public Collection<List<Seat>> getSeatCollection(int bookingTotal) {
        Collection<List<Seat>> seatCollection = null;

        seatCollection.add(outboundSeatList);
        seatCollection.add(returnSeatList);

        return seatCollection;
    }

    public void addToSeatList(HttpServletRequest request, HttpServletResponse response, Seat seat) throws ClassNotFoundException {
        int seatTotal = (int) request.getAttribute("bookingTotal");
        List<Seat> seatList = new ArrayList<>();
        for (int i = 0; i < seatTotal; i++) {
            Booking bookingId = null;
            seat = seatManager.addSeatReservation(seatNumber, aircraftId, seatType.toString(), bookingId, isSeatBooked);
            seatList.add(seat);
            request.setAttribute("seatList", seatList);
        }
    }

    public boolean[] allocateEconomySeat(HttpServletRequest request, HttpServletResponse response, String seatType) throws ServletException, IOException, ClassNotFoundException {
        String passenger = request.getParameter("Passenger");
        String outboundOrReturn = request.getParameter("OutboundOrReturn");
        HttpSession session = request.getSession();
        int bookingTotal = (int) session.getAttribute("bookingTotal");
        if (economyCounter < 12) {
//                    //If there are vacant seats, randomly select one etc...
            for (boolean seat : seatManager.getSeats()) {
                if (seatType.equals(SeatTypeEnum.ECONOMY.toString())) {
                    if (seat == false) {
                        Random random = new Random();
                        int economySeat = random.nextInt(23 - 12 + 1) + 12;
                        seatNumber = economySeat;
                    }
                }
            }
            if (outboundOrReturn.equals("Outbound")) {
//                    if (submit.equals("choose outbound")) {    
                if (economyCounter + bookingTotal < 12) {

                    outboundSeatList.add(seatManager.getSeat(seatNumber));
                    request.setAttribute("outboundSeatList", outboundSeatList);
                    session.setAttribute("outboundSeatList", outboundSeatList);
                    System.out.println(Arrays.toString(outboundSeatList.toArray()));//FOR DEBUGGING

                    if (outboundSeatList.size() >= bookingTotal) {
                        msg = "You have used up all your outbound seats in your booking";
                        request.setAttribute("msg", msg);
                        session.setAttribute("msg", msg);
                        url = "/booked.jsp";
                        //url = "/makeBooking.jsp";
                    } else {
                        url = "/indexRevB.jsp";
                    }
                } else {
                    msg = "There are not enough seats available for your booking requirements. Please choose another flight.";
                    request.setAttribute("msg", msg);
                    url = "/booked.jsp";
                }
            } else if (outboundOrReturn.equals("Return")) {
//                    } else if (submit.equals("choose return")) {
                if (economyCounter + bookingTotal < 12) {

                    returnSeatList.add(seatManager.getSeat(seatNumber));
                    request.setAttribute("returnSeatList", returnSeatList);
                    session.setAttribute("returnSeatList", returnSeatList);

                    if (returnSeatList.size() >= bookingTotal) {
                        msg = "You have used up all your return seats in your booking";
                        request.setAttribute("msg", msg);
                        session.setAttribute("msg", msg);
                        url = "/booked.jsp";
                    } else {

                    }
                } else {
                    msg = "There are not enough seats available for your booking requirements. Please choose another flight.";
                    request.setAttribute("msg", msg);
                    url = "/booked.jsp";
                }
            }

            if (seatManager.getSeats()[seatNumber] == false) {
                seatManager.getSeats()[seatNumber] = true;
                economyCounter++;
                seatManager.addSeatBooking(seatNumber, aircraftId, seatType, null, true);
                msg = "Your Economy Class Seat Booking.";
                request.setAttribute("msg", msg);
                for (SeatEnum seatEnum : SeatEnum.values()) {
                    if (seatNumber == seatEnum.ordinal()) {
                        if (passenger.equals(PassengerEnum.ADULT.toString())) {
                            double seatCost = SeatEnum.valueOf(seatEnum.toString()).getAdultFare();
                            request.setAttribute("seatCost", seatCost);
                        } else if (passenger.equals(PassengerEnum.CHILD.toString())) {
                            double seatCost = SeatEnum.valueOf(seatEnum.toString()).getChildFare();
                            request.setAttribute("seatCost", seatCost);
                        } else if (passenger.equals(PassengerEnum.INFANT.toString())) {
                            double seatCost = SeatEnum.valueOf(seatEnum.toString()).getInfantFare();
                            request.setAttribute("seatCost", seatCost);
                        }
                        request.setAttribute("passengerType", passenger);
                        request.setAttribute("seatNumber", seatNumber + 1);
                        request.setAttribute("seatType", seatType);
                    }
                    url = "/message.jsp";

                }
            }

            isSeatBooked = true;
        } else {
            msg = "All the Economy seats have been used up.";
            request.setAttribute("msg", msg);
            url = "/booked.jsp";

        }
        //url = "/indexRevB.jsp";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        return seatManager.getSeats();

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        request.setAttribute("seats", Arrays.toString(seatManager.getSeats()));
        int bookingTotal = (int) request.getAttribute("bookingTotal");

        String submit = request.getParameter("submit");
        if (submit != null && submit.length() > 0) {

            switch (submit) {
                case "seat01F":
                    seatNumber = 0;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat02F":
                    seatNumber = 1;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat03F":
                    seatNumber = 2;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat04F":
                    seatNumber = 3;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat05F":
                    seatNumber = 4;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat06F":
                    seatNumber = 5;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat07F":
                    seatNumber = 6;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat08F":
                    seatNumber = 7;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat09F":
                    seatNumber = 8;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat10F":
                    seatNumber = 9;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat11F":
                    seatNumber = 10;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
                case "seat12F":
                    seatNumber = 11;
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    this.chooseSeat(request, response);
                    break;
//                case "seat13E":
//                    seatNumber = 12;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat14E":
//                    seatNumber = 13;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat15E":
//                    seatNumber = 14;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat16E":
//                    seatNumber = 15;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat17E":
//                    seatNumber = 16;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat18E":
//                    seatNumber = 17;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat19E":
//                    seatNumber = 18;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat20E":
//                    seatNumber = 19;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat21E":
//                    seatNumber = 20;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat22E":
//                    seatNumber = 21;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat23E":
//                    seatNumber = 22;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
//                case "seat24E":
//                    seatNumber = 23;
//                    seatType = SeatTypeEnum.ECONOMY;
//                    this.chooseSeat(request, response);
//                    break;
                case "seats":
                    url = "/indexRevB.jsp";
                    break;
                case "Economy":
                    seatType = SeatTypeEnum.ECONOMY;
                    this.allocateEconomySeat(request, response, seatType.toString());
                    url = "/indexRevB.jsp";
                    break;
                case "choose outbound":
                    //CODE HERE
                    //this.outboundSeatList.add(seat);
                    url = "/indexRevB.jsp";
                    break;
                case "choose return":
                    //CODE HERE
                    //this.returnSeatList.add(seat);
                    url = "/indexRevB.jsp";
                    break;
                default:
                    break;
            }
            
        }
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
        try {
            response.setContentType("application/json;charset=utf-8");

            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            JSONObject member = new JSONObject();

            member.put("arrayData", seatManager.getSeats());
            array.add(member);

            json.put("jsonArray", array);

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
        } catch (IOException e) {
            e.getMessage();
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

        HttpSession session = request.getSession();
        if (session.isNew()) {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            int bookingTotal = (int) request.getAttribute("bookingTotal");
            pw.println("The value of bookingTotal is: " + bookingTotal);
            request.setAttribute("bookingTotal", bookingTotal);
            session.setAttribute("bookingTotal", bookingTotal);
            url = "/indexRevB.jsp";

        } else {
            try {
                url = "/indexRevB.jsp";
                processRequest(request, response);
                return;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SeatingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
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

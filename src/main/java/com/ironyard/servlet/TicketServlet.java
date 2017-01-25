package com.ironyard.servlet;

import com.ironyard.data.Ticket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasonskipper on 1/19/17.
 */
@WebServlet(name = "TicketServlet", urlPatterns = "/generate")
public class TicketServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String winningTicket = request.getParameter("winning_ticket");
        String[] numbersAsStrings = winningTicket.split(" ");

        int[] winningNumbers = new int[numbersAsStrings.length];

        // change strings into ints
        for(int i=0; i<numbersAsStrings.length; i++){
            int converted = Integer.parseInt(numbersAsStrings[i]);
            winningNumbers[i] = converted;
        }

        // get the ticket list
        ArrayList<Ticket> myTicketList = (ArrayList<Ticket>) request.getSession().getAttribute("tList");
        for(Ticket testMe:myTicketList){

            testMe.checkNumbers(winningNumbers);
        }

        // NOW EACH TICKET KNOWS how many matches it has

    }

    /**
     * This will handle the case where a user is looking to generate a random new ticket
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create a ticket
        Ticket aTicket = new Ticket();
        aTicket.writeTicketToDisk();

        // look for the list
        ArrayList<Ticket> myTicketList = (ArrayList<Ticket>) request.getSession().getAttribute("tList");

        // if not there, this is 1st time so create it and put into session (for next time)
        if(myTicketList == null){
            myTicketList = (ArrayList<Ticket>) request.getServletContext()
                    .getAttribute("tList");
            request.getSession().setAttribute("tList", myTicketList);
        }

        // add ticket to list
        myTicketList.add(aTicket);

        // set success message
        request.setAttribute("success_message", "Congrats! You made new ticket!!!");


        // send to JSP page to display tickets
        String nextJSP = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }


    @Override
    public void init() throws ServletException {
       List<Ticket> ticketList = Ticket.readAllTicketsFromDisk();

       getServletContext().setAttribute("tList",ticketList);
    }

}

package org.example.demo;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.example.demo.business.Survey;
import org.example.demo.business.User;

public class EmailListServlet extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.html";    // the "join" page
        }
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");
            String hearAboutUs = request.getParameter("hearAboutUs");
            String cdOffers = request.getParameter("cdOffers");
            String specialOffers = request.getParameter("specialOffers");

            String[] contactMethods = request.getParameterValues("method");

            // store data in User object and save User object in db
            User user = new User(firstName, lastName, email, dob);
            Survey survey = new Survey(email, hearAboutUs, cdOffers, specialOffers, contactMethods);

            // set User object in request object and set URL
            request.setAttribute("user", user);
            request.setAttribute("survey", survey);
            url = "/thanks.jsp";   // the "thanks" page
        }

        // forward request and response objects to specified URL
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}




package org.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.demo.business.User;

public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "join";
        }

        String url = "/index.jsp";

        if (action.equals("join")) {
            url = "/index.jsp";

        } else if (action.equals("add")) {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dob = request.getParameter("dob");

            User user = new User(
                    firstName,
                    lastName,
                    email,
                    dob
            );

            // Lưu người dùng hiện tại vào request
            request.setAttribute("user", user);

            // Lưu ngày hiện tại vào request
            request.setAttribute("currentDate", new Date());

            // Lấy session
            HttpSession session = request.getSession();

            // Lưu người dùng gần nhất để điền lại form
            session.setAttribute("user", user);

            // Lấy danh sách người dùng trong session
            @SuppressWarnings("unchecked")
            ArrayList<User> users =
                    (ArrayList<User>) session.getAttribute("users");

            // Nếu danh sách chưa tồn tại thì tạo mới
            if (users == null) {
                users = new ArrayList<>();
            }

            // Thêm người dùng vào danh sách
            users.add(user);

            // Lưu danh sách trở lại session
            session.setAttribute("users", users);

            url = "/thanks.jsp";
        }

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

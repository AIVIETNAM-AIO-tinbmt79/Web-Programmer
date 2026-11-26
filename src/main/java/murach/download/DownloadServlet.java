
package murach.download;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import murach.business.User;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "viewAlbums";
        }

        String url;

        switch (action) {
            case "viewAlbums":
                url = "/index.jsp";
                break;

            case "checkUser":
                url = checkUser(request);
                break;

            case "viewCookies":
                url = "/view_cookies.jsp";
                break;

            case "deleteCookies":
                deleteCookies(request, response);
                url = "/index.jsp";
                break;

            default:
                url = "/index.jsp";
                break;
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String url = "/index.jsp";

        if ("registerUser".equals(action)) {
            url = registerUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String checkUser(HttpServletRequest request) {

        String productCode = request.getParameter("productCode");

        HttpSession session = request.getSession();
        session.setAttribute("productCode", productCode);

        User user = (User) session.getAttribute("user");

        if (user == null) {
            Cookie[] cookies = request.getCookies();

            String email = CookieUtil.getCookieValue(
                    cookies, "emailCookie");

            if (email == null || email.isEmpty()) {
                return "/register.jsp";
            }

            String firstName = CookieUtil.getCookieValue(
                    cookies, "firstNameCookie");

            user = new User(firstName, "", email);
            session.setAttribute("user", user);
        }

        return "/download.jsp";
    }

    private String registerUser(HttpServletRequest request,
                                HttpServletResponse response) {

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = new User(firstName, lastName, email);

        request.getSession().setAttribute("user", user);

        addCookie(response, "emailCookie", email);
        addCookie(response, "firstNameCookie", firstName);

        return "/download.jsp";
    }

    private void addCookie(HttpServletResponse response,
                           String name, String value) {

        Cookie cookie = new Cookie(
                name, value == null ? "" : value);

        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    private void deleteCookies(HttpServletRequest request,
                               HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("emailCookie")
                        || cookie.getName().equals("firstNameCookie")) {

                    cookie.setMaxAge(0);
                    cookie.setPath("/");

                    response.addCookie(cookie);
                }
            }
        }

        request.getSession().invalidate();
    }
}


package murach.download;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("view".equals(action) || action == null) {
            request.getRequestDispatcher("/cart.jsp")
                    .forward(request, response);
        } else {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Invalid cart action"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String url;

        switch (action == null ? "" : action) {
            case "add":
                url = addToCart(request);
                break;

            case "update":
                url = updateCart(request);
                break;

            case "remove":
                url = removeFromCart(request);
                break;

            case "checkout":
                request.getSession().removeAttribute("cart");
                url = "/checkout.jsp";
                break;

            case "continue":
                url = "/index.jsp";
                break;

            default:
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid cart action"
                );
                return;
        }

        request.getRequestDispatcher(url)
                .forward(request, response);
    }

    private String addToCart(HttpServletRequest request) {
        String code = request.getParameter("productCode");
        Product product = getProduct(code);

        if (product == null) {
            request.setAttribute(
                    "cartMessage", "Sản phẩm không hợp lệ."
            );
            return "/index.jsp";
        }

        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        cart.addItem(new LineItem(product, 1));
        session.setAttribute("cart", cart);

        return "/cart.jsp";
    }

    private String updateCart(HttpServletRequest request) {
        Cart cart = getCart(request);

        if (cart == null) {
            return "/cart.jsp";
        }

        String code = request.getParameter("productCode");
        String quantityText = request.getParameter("quantity");

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity < 0) {
                request.setAttribute(
                        "cartMessage",
                        "Số lượng không được âm."
                );
                return "/cart.jsp";
            }

            cart.updateItem(code, quantity);
            request.getSession().setAttribute("cart", cart);

        } catch (NumberFormatException e) {
            request.setAttribute(
                    "cartMessage",
                    "Số lượng phải là số nguyên hợp lệ."
            );
        }

        return "/cart.jsp";
    }

    private String removeFromCart(HttpServletRequest request) {
        Cart cart = getCart(request);

        if (cart != null) {
            String code = request.getParameter("productCode");
            cart.removeItem(code);
        }

        return "/cart.jsp";
    }

    private Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Cart) session.getAttribute("cart");
    }

    private Product getProduct(String code) {
        if (code == null) {
            return null;
        }

        switch (code) {
            case "8601":
                return new Product(
                        "8601",
                        "86 (the band) - True Life Songs and Pictures",
                        14.95
                );

            case "pf01":
                return new Product(
                        "pf01",
                        "Paddlefoot - The first CD",
                        12.95
                );

            case "pf02":
                return new Product(
                        "pf02",
                        "Paddlefoot - The second CD",
                        14.95
                );

            case "jr01":
                return new Product(
                        "jr01",
                        "Joe Rut - Genuine Wood Grained Finish",
                        14.95
                );

            default:
                return null;
        }
    }
}

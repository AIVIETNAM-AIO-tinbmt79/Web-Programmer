package org.example.cart.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.example.cart.model.*;

public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/shop.html";
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        if (action.equals("add")) {
            String code = request.getParameter("productCode");

            Product product = new Product();
            if ("8601".equals(code)) {
                product.setProductCode(code);
                product.setDescription("86 (the band) - True Life Songs and Pictures");
                product.setPrice(14.95);
            } else if ("pf01".equals(code)) {
                product.setProductCode(code);
                product.setDescription("Paddlefoot - The first CD");
                product.setPrice(12.95);
            } else if ("pf02".equals(code)) {
                product.setProductCode(code);
                product.setDescription("Paddlefoot - The second CD");
                product.setPrice(14.95);
            } else if ("jr01".equals(code)) {
                product.setProductCode(code);
                product.setDescription("Joe Rut - Genuine Wood Grained Finish");
                product.setPrice(14.95);
            }

            // Khi bấm Add To Cart thì mặc định số lượng thêm là 1
            LineItem item = new LineItem();
            item.setProduct(product);
            item.setQuantity(1);

            cart.addItem(item);
            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }

        if (action.equals("update")) {
            String code = request.getParameter("productCode");
            String quantity = request.getParameter("quantity");
            for (LineItem item : cart.getItems() ) {
                if (item.getProduct().getProductCode().equals(code)) {
                    item.setQuantity(Integer.valueOf(quantity));
                }
            }

            url = "/cart.jsp";
        }

        if (action.equals("remove")) {
            String code = request.getParameter("productCode");
            for (LineItem item : cart.getItems()) {
                if (item.getProduct().getProductCode().equals(code)) {
                    cart.removeItem(item);
                    break;
                }
            }
            url = "/cart.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
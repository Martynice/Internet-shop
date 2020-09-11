package com.internet.shop.controllers.order;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/details")
public class GetOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Long orderId = Long.valueOf(req.getParameter("id"));
        Order order = orderService.get(orderId);
        req.setAttribute("orderProducts", order.getProducts());
        req.getRequestDispatcher("/WEB-INF/views/order/details.jsp").forward(req, resp);
    }
}

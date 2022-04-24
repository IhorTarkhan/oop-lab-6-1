package com.ihortarkhan.ooplab61.servlets;

import com.ihortarkhan.ooplab61.services.CoffeeUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order-drink")
public class OrderDrinkServlet extends HttpServlet {
    private final ServletUtil servletUtil = new ServletUtil();
    private final CoffeeUserService coffeeUserService = new CoffeeUserService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        servletUtil.execute(request, response, r -> {
            coffeeUserService.orderDrink(r);
            return new Object();
        }, "coffee_user");
    }
}

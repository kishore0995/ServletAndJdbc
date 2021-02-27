package com.ssb.mobileshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.mobileshop.model.Phone;
import com.ssb.mobileshop.service.PhoneService;

public class ViewServletController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -2136060435813328209L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='add'>Add New Phone</a><br>");
        out.println("<a href=" + "'" + request.getContextPath() + "/admin'>Home</a><br>");
        out.println("<h1>Phone List</h1>");

        List<Phone> phone = PhoneService.getInstance().viewPhone();
        out.print("<table border='1' width='100%'");
        out.print(
                "<tr><th>Id</th><th>Brand Name</th><th>Model Name</th><th>Ram Size</th><th>Rom Size</th><th>Price</th><th>Stock</th><th>Edit</th><th>Delete</th></tr>");
        for (Phone list : phone) {
            out.print("<tr><td>" + list.getId() + "</td><td>" + list.getBrandName() + "</td><td>" + list.getModelName()
                    + "</td><td>" + list.getRam() + "</td><td>" + list.getRom() + "</td><td>" + list.getPrice()
                    + "</td><td>" + "</td<td>" + list.getStock() + "</td><td>" + "<a href='edit?id=" + list.getId()
                    + "'>edit</a></td>" + "<td><a href='delete?id=" + list.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");
    }
}

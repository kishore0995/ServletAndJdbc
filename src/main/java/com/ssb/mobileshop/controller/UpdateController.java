package com.ssb.mobileshop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.mobileshop.dao.impl.PhoneDaoImpl;
import com.ssb.mobileshop.model.Phone;

public class UpdateController extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 4544698798498L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPut(request, response);
     }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
       doPut(request, response);
    }
    protected void doPut(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       try{
            int id = Integer.parseInt(request.getParameter("id"));
        String brandName = request.getParameter("brand");
        String modelName = request.getParameter("model");
        int ram = Integer.parseInt(request.getParameter("ram"));
        int rom = Integer.parseInt(request.getParameter("rom"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        float price = Float.parseFloat(request.getParameter("price"));
        Phone phone = new Phone();
        phone.setId(id);
        phone.setBrandName(brandName);
        phone.setModelName(modelName);
        phone.setRam(ram);
        phone.setRom(rom);
        phone.setStock(stock);
        phone.setPrice(price);
        if(phone.getBrandName().equals("")){
            out.print("<p>Brand name Should Not be empty</p>");
        }
        else if(phone.getModelName().equals("")){
            out.print("<p>Model Not should not be Blank</p>");
        }else{
            int status = PhoneDaoImpl.getInstance().update(phone);
            if(status>0){
                response.sendRedirect(request.getContextPath()+"/admin/view");
            }
            else{
                out.println("Failed to Update phone");
            }
        } 
    }catch (Exception e) {
            out.println("<p>Ram,Rom,stock,price Should be in Numbers only</p>");
            e.printStackTrace();
        }
    }
}

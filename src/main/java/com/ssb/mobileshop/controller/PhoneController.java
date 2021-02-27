package com.ssb.mobileshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssb.mobileshop.dao.impl.PhoneDaoImpl;
import com.ssb.mobileshop.model.Phone;
import com.ssb.mobileshop.service.PhoneService;

public class PhoneController extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 5669878797988L;

    // path decleration
    private static final String viewPhone = "/user/viewphone";
    private static final String viewBrand = "/user/viewbrand";
    private static final String viewByRam = "/user/ram";
    private static final String viewByPrice = "/user/price";
    private static final String yes = "/user/yes";
    private static final String no = "/user/no";
    private static final String add = "/admin/add";
    private static final String save = "/admin/save";
    private static final String edit = "/admin/edit";
    private static final String delete = "/admin/delete";
    public static int ram = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("=======process req==========="+request.getParameter(""));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String path = request.getServletPath();
        System.out.println(path);
            switch (path) {
                // viewphone api get all phone details from database
                case viewPhone:
                List<Phone> phone = PhoneService.getInstance().viewPhone();
                if (phone != null) {
                System.out.println("================");
                    out.print("<table border='1' width='100%'");
                    out.print(
                            "<tr><th>Brand Name</th><th>Model Name</th><th>Ram Size</th><th>Rom Size</th><th>Price</th></tr>");
                    for (Phone list : phone) {
                        out.print("<tr><td>" + list.getBrandName() + "</td><td>" + list.getModelName() + "</td><td>"
                                + list.getRam() + "</td><td>" + list.getRom() + "</td><td>" + list.getPrice()
                                + "</td></tr>");
                    }
                    out.print("<br>");
                    out.print("<html><body>");
                    out.print("<form action='model'>");
                    out.print("<input type='text' name='modelName' placeholder='Enter Model Name'>");
                    out.print("<input type='submit' value='search'><br><br>");
                    out.print("<a href='"+request.getContextPath()+"/user'>Home</a>");
                    out.print("</form>");
                    out.print("</body></html>");
                }else{
                    out.print("<h2>No Phones Availabe</h2>");
                }
                    break;

                // viewbrand api.This api is used to Get All Available Brands
                case viewBrand:
                    Set<String> brand = PhoneService.getInstance().brandName();
                    out.print("<ul>");
                    for (String brandName : brand) {
                        out.print("<li>" + brandName + "</li><br>");
                    }
                    out.print("</ul");
                    out.print("<br>");
                    out.print("<html><body>");
                    out.print("<form action='brand'>");
                    out.print("<input type='text' name='brandName' placeholder='Enter Brand Name'>");
                    out.print("<input type='submit' value='search'><br><br>");
                    out.print("<a href='"+request.getContextPath()+"/user'>Home</a>");
                    out.print("</form>");
                    out.print("</body></html>");
                    break;
                // ram api.This api used to get the input of RAM size from user
                case viewByRam:
                    out.print("<html><body>");
                    out.print("<form action='viewbyram'>");
                    out.print("<label>Enter Ram Size You Want</label><br><br>");
                    out.print("<input type='text' name='ram' placeholder='Enter Ram Size'><br><br>");
                    out.print("<input type='submit' value='search'><br><br>");
                    out.print("<a href='"+request.getContextPath()+"/user'>Home</a>");
                    out.print("</form>");
                    out.print("</body></html>");
                    break;

                // viewbyram api.This api used to filter mobile by given RAM size by user
                case "/user/viewbyram":
                    ram = Integer.parseInt(request.getParameter("ram"));
                    List<Phone> getDetailsByRam = PhoneService.getInstance().getByRam(ram);
                    if (getDetailsByRam.isEmpty()) {
                        out.print("<h2>Sorry No Phones Available for " + ram + " " + "gb RAM</h2>");
                    } else {
                        out.print("<table border='1' width='100%'");
                        out.print(
                                "<tr><th>Brand Name</th><th>Model Name</th><th>Ram Size</th><th>Rom Size</th><th>Price</th></tr>");
                        for (Phone list : getDetailsByRam) {
                            out.print("<tr><td>" + list.getBrandName() + "</td><td>" + list.getModelName() + "</td><td>"
                                    + list.getRam() + "</td><td>" + list.getRom() + "</td><td>" + list.getPrice()
                                    + "</td></tr>");
                        }
                        out.print("<br>");
                        out.print("<html><body>");
                        out.print("<form action='rambrand'>");
                        out.print("<input type='text' name='brandName' placeholder='Enter Brand Name'>");
                        out.print("<input type='submit' value='search'><br><br>");
                        out.print("<a href='"+request.getContextPath()+"/user'>Home</a>");
                        out.print("</form>");
                        out.print("</body></html>");
                    }
                    break;

                // If user select yes then Phone will be purchased
                case yes:
                    try {
        
                       int status= PhoneDaoImpl.getInstance().updateStock(PhoneService.stock-1,PhoneService.modelName);
                       if(status>0){
                        out.print("<h1>Thank you for purchasing in mobile shop</h1>");
                        out.print("<form action='" + request.getContextPath() + "/user'><br>");
                        out.print("<input type='submit' value='go back'>");
                        out.print("</form>");
                       }
                       else{
                           out.print("<p>Cant able To Purchase</p>");
                       }
                    } catch (Exception e1) {
                        out.print("<p>Unable to Update Stock</p>");
                        e1.printStackTrace();
                    }
                    
                    break;

                // if user select no then it will be redirected to home page
                case no:
                    response.sendRedirect(request.getContextPath() + "/user");
                    break;

                // Filter Phone by price Range
                case viewByPrice:
                    out.print("<form action='viewbyprice'>");
                    out.print("<label>Enter The Price to Filter</label><br><br>");
                    out.print("<input type='text' name='price' placeholder='Enter the price'><br><br>");
                    out.print("<input type='submit' value='search'><br><br>");
                    out.print("<a href='"+request.getContextPath()+"/user'>Home</a>");
                    out.print("</form>");
                    break;

                // add api is used to get the details of phone to add in database
                case add:
                    out.print("<body>");
                    out.print("<form action='save' method='POST'>");
                    out.print("<label>Brand Name</label><br><br>");
                    out.print("<input type='text' name='brand' placeholder='Enter brand name'required><br><br>");
                    out.print("<label>Model Name</label><br><br>");
                    out.print("<input type='text' name='model' placeholder='Enter Model Name' required><br><br>");
                    out.print("<label>RAM</label><br><br>");
                    out.print("<input type='text' name='ram' placeholder='Enter Ram Size'required><br><br>");
                    out.print("<label>ROM</label><br><br>");
                    out.print("<input type='text' name='rom' placeholder='Enter ROM size' required><br><br>");
                    out.print("<label>Stock</label><br><br>");
                    out.print("<input type='text' name='stock' placeholder='Enter Stock' required><br><br>");
                    out.print("<label>Price</label><br><br>");
                    out.print("<input type='text' name='price' placeholder='Enter Price' required><br><br>");
                    out.print("<input type='submit' value='Add Phone'><br><br>");
                    out.print("<a href='view'>View Phone</a>");
                    out.print("</form>");
                    out.print("</body>");
                    break;

                // save api.This api will save the phone in database
                case save:
                    String brandName = request.getParameter("brand");
                    String model = request.getParameter("model");
                    int RAM = Integer.parseInt(request.getParameter("ram"));
                    int rom = Integer.parseInt(request.getParameter("rom"));
                    int phoneStock = Integer.parseInt(request.getParameter("stock"));
                    float price = Float.parseFloat(request.getParameter("price"));

                    Phone savePhone = new Phone();
                    savePhone.setBrandName(brandName);
                    savePhone.setModelName(model);
                    savePhone.setRam(RAM);
                    savePhone.setRom(rom);
                    savePhone.setStock(phoneStock);
                    savePhone.setPrice(price);

                    int status = PhoneService.getInstance().save(savePhone);
                    if (status > 0) {
                        out.print("<p>Phone Added Successfully</p>");
                        request.getRequestDispatcher("/add").include(request, response);
                    } else {
                        out.print("<p>Failed to Add Phone</p>");
                        request.getRequestDispatcher("/add").include(request, response);
                    }
                    break;

                // edit api will prompt the user to edit phone
                case edit:
                    out.println("<h1>Update Phone</h1>");
                    int id = Integer.parseInt(request.getParameter("id"));
                    System.out.println(id);
                    Phone phoneList = PhoneService.getInstance().edit(id);
                    out.print("<form action='update' method='PUT'>");
                    out.print("<table>");
                    out.print("<tr><td></td><td><input type='hidden' name='id' value='" + phoneList.getId()
                            + "'/></td></tr>");
                    out.print("<tr><td>Brand Name:</td><td><input type='text' name='brand' value='"
                            + phoneList.getBrandName() + "'/></td></tr>");
                    out.print("<tr><td>Model Name:</td><td><input type='text' name='model' value='"
                            + phoneList.getModelName() + "'/></td></tr>");
                    out.print("<tr><td>Ram:</td><td><input type='text' name='ram' value='" + phoneList.getRam()
                            + "'/></td></tr>");
                    out.print("<tr><td>Rom:</td><td><input type='text' name='rom' value='" + phoneList.getRom()
                            + "'/></td></tr>");
                    out.print("<tr><td>Stock:</td><td><input type='text' name='stock' value='" + phoneList.getStock()
                            + "'/></td></tr>");
                    out.print("<tr><td>Price:</td><td><input type='text' name='price' value='" + phoneList.getPrice()
                            + "'/></td></tr>");
                    out.print("</td></tr>");
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
                    out.print("</table>");
                    out.print("</form>");
                    break;

                // it will remove phone from database
                case delete:
                    int phoneId = Integer.parseInt(request.getParameter("id"));
                    try {
                        int updateStatus = PhoneDaoImpl.getInstance().delete(phoneId);
                        if (updateStatus > 0) {
                            response.sendRedirect(request.getContextPath() + "/admin/view");
                        } else {
                            out.print("<p>Sorry Failed to Delete Phone</p>");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            } 
        }
    }


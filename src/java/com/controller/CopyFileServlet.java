/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pc3
 */
@WebServlet(name = "CopyFileServlet", urlPatterns = {"/copyFile"})
public class CopyFileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            File srcFolder = new File("C:\\cobaenkrip");
            File destFolder = new File(getServletContext().getRealPath("/") + "files/copied");

            //make sure source exists
            if (!srcFolder.exists()) {

                System.out.println("Directory does not exist.");
                //just exit
                System.exit(0);

            } else {

                try {
                    copyFolder(srcFolder, destFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                    //error, just exit
                    System.exit(0);
                }
            }

            System.out.println("Done");
            
            response.sendRedirect("index.jsp");
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        /*
        CopyFileServlet myClass = new CopyFileServlet();
        String button = request.getParameter("button");

        if ("button1".equals(button)) {
            File srcFolder = new File("C:\\cobaenkrip");
            File destFolder = new File(getServletContext().getRealPath("/") + "files/copied");
            CopyFileServlet.copyFolder(srcFolder, destFolder);
        } else if ("button2".equals(button)) {
            //myClass.method2();
        } else if ("button3".equals(button)) {
            //myClass.method3();
        } else {
            // ???
        }

        request.getRequestDispatcher("/WEB-INF/some-result.jsp").forward(request, response);
        */
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdirs();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            try {
                //if file, then copy it
                //Use bytes stream to support all file types
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);
                
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                //byte[] digest = md.digest();
                
                byte[] buffer = new byte[1024];
                String eknrip = new String(buffer, "UTF-8");
                md.update(eknrip.getBytes());
                //byte[] kunci = "poltekssnrplk".getBytes();
                
                int length;
                //copy the file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    //buffer = (buffer^kunci[length]);
                    buffer = md.digest();
                    String hasil = Base64.getEncoder().encodeToString(buffer);
                    out.write(hasil.getBytes());
                }
                in.close();
                out.close();
                System.out.println("File copied from " + src + " to " + dest);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CopyFileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

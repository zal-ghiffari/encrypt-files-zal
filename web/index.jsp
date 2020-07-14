<%-- 
    Document   : index
    Created on : May 7, 2015, 12:12:56 PM
    Author     : pc3
--%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enkripsi Dekripsi ZAL Page</title>
    </head>
    <body>
        <h1>ENKRIPSI DEKRIPSI FILE | MUHAMMAD NOVRIZAL GHIFFARI</h1>
        <h3>Readme : Silahkan sesuaikan pada baris code "CopyFileServlet.java" dimana letak path file yang ingin di enkripsi/dekripsi.<br>File hasil akan muncul pada path "files/copied"</h3>
        <img src="files/copied/enkripsi.jpg" style="width: 300px; height: 350px"/>
        <br>
        <br>
        <!--<form action = "UploadServlet" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" />
         <br />
         <input type = "submit" value = "enkripsi" />
        </form>
        <br>-->
        <h3>Isi file yang sudah di enkripsi</h3>
        <%
            String jspPath = "C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\copy-files-zal\\build\\web\\files\\copied\\";
            String fileName = "coba.txt";
            String txtFilePath = jspPath + fileName;
            out.println("Terletak di: "+txtFilePath);
        %>
        <br>
        <h5>Isi file:</h5>
        <%
            BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine())!= null){
                sb.append(line+"\n");
            }
            out.println(sb.toString()); 
        %>      
    </body>
</html>

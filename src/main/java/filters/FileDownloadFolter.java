/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filters;

import entitys.FileToBeSigned;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;
import models.FileToBeSignedFacade;

/**
 *
 * @author mrbis
 */
@WebFilter(filterName = "FileDownloadFolter", urlPatterns = {"/files/*"})
public class FileDownloadFolter implements Filter {

    @EJB
    FileToBeSignedFacade fileToBeSignedFacade;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String filrId = httpRequest.getRequestURI().replaceAll("/EnterpriseSignatureSystem/files/", "");
        UUID id = UUID.fromString(filrId);
        FileToBeSigned fileToBeSigned = fileToBeSignedFacade.find(id);
        
        
        //String filePath = "C:\\0SSFolder\\TesrReestr\\44444\\GM-325_manual__M_630_026.pdf";
        File downloadFile = new File(fileToBeSigned.getFullFilePath());
        FileInputStream inStream = new FileInputStream(downloadFile);
        //
 
         
        // gets MIME type of the file
        String mimeType = downloadFile.toURL().openConnection().getContentType();//"application/octet-stream";
        //
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        
        // forces download
        //String headerKey = "Content-Disposition";
        //String headerValue = String.format("attachment; filename=", downloadFile.getName());
        //httpResponse.setHeader(headerKey, headerValue);
        //
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();  
        //
        //chain.doFilter(request, response);
    }
   

}

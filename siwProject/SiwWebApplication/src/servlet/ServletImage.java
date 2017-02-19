package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletImage")
public class ServletImage extends Servlet {

	private static final long serialVersionUID = 5233145810918306670L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String filePath =ServletUpload.uploadPathFolder+"insertion_"+req.getParameter("id_item")+"/"+req.getParameter("nameImage");
	        File downloadFile = new File(filePath);
	        FileInputStream inStream = new FileInputStream(downloadFile);
	        
	        
	        String mimeType = getServletContext().getMimeType(filePath);
	        if (mimeType == null) {        
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
//	        System.out.println("MIME type: " + mimeType);
	         
	        // modifies response
	        resp.setContentType(mimeType);
	        resp.setContentLength((int) downloadFile.length());
	        
	        
	        OutputStream outStream = resp.getOutputStream();
	         
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	         
	        while ((bytesRead = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	         
	        inStream.close();
	        outStream.close();     
		
	}
	
	
}

package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/ServletUpload")
public class ServletUpload extends Servlet {

	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "data";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 16;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 8;
	public static final String uploadPathFolder = System.getProperty("user.home") + "/Siw/images/";
	private int id_insertion;

	public ServletUpload() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			return;
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// constructs the folder where uploaded file will be stored
		String uploadFolder = getServletContext().getRealPath("") + DATA_DIRECTORY;

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);
		try {
			// Parse the request
			List items = upload.parseRequest(req);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					String fileName = new File(item.getName()).getName();

					if (!fileName.equals("")) {

						String filePath = uploadPathFolder + "/insertion_" + id_insertion + "/" + fileName;
						new File(uploadPathFolder + "/insertion_" + id_insertion + "/").mkdirs();
						File uploadedFile = new File(filePath);

						// getFilePath.setFilePath("images/profileImage/"+
						// fileName);
						// getFilePath.setFilePath("/ProgettoSiw/ImageServlet?name="+
						// fileName);
						// saves the file to upload directory
						item.write(uploadedFile);

						// displays done.jsp page after upload finished
						// getServletContext().getRequestDispatcher("/JSP/done.jsp").forward(req,
						// resp);
					} 
				} else {
					id_insertion = Integer.parseInt(item.getString());
				}
;

			}

		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		req.getSession().setAttribute("id_item", id_insertion);
		getServletContext().getRequestDispatcher("/uploadSuccess.jsp").forward(req, resp);
	}

}

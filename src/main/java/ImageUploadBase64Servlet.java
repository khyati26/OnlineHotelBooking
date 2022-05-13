

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageUploadBase64Servlet
 */
public class ImageUploadBase64Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 Part filePart = request.getPart("photo");
//	      InputStream fileContent = filePart.getInputStream();
//	      byte[] byteArray = fileContent.readAllBytes();
//	      
//	      	String base64String = Base64.getEncoder().encodeToString(byteArray);
//
//	      System.out.println("filePart: " + filePart);
//	      System.out.println("fileContent: " + fileContent);
//	      System.out.println("byteArray: " + byteArray);
//	      System.out.println("base64String: " + base64String);
//	      System.out.println("content type: "+filePart.getContentType());
//
//	      request.setAttribute("image", base64String);
//	      RequestDispatcher rd = request.getRequestDispatcher("testshow.jsp");
//			rd.forward(request, response);
	}

}

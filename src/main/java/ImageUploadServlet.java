
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Utility.ConnectDB;
//import com.narola.hotelbooking.Utility.testmodel;

//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileItemFactory;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ImageUploadServlet
 */
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement ps;
		/*
		 * List<testmodel> list = new ArrayList();
		 * 
		 * try { ps = ConnectDB.getConnection().prepareStatement("select * from test");
		 * ResultSet rs=ps.executeQuery(); while(rs.next()) { testmodel t=new
		 * testmodel(); t.setId(rs.getInt(1)); t.setImagepath(rs.getString(2)); //
		 * t.setImage(rs.getBlob(3)); list.add(t);
		 * 
		 * } ps.close(); } catch (DatabaseException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * System.out.println(list.get(1).getImagepath()); request.setAttribute("data",
		 * list); RequestDispatcher rd = request.getRequestDispatcher("testshow.jsp");
		 * rd.forward(request, response);
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("fname"));
		System.out.println(request.getParameter("lname"));

		Part photoPart = request.getPart("photo");
		System.out.println(photoPart.getContentType());
		System.out.println(photoPart.getName());
		System.out.println(photoPart.getSize());
		System.out.println(photoPart.getSubmittedFileName());

		InputStream inputStream = photoPart.getInputStream();
//		byte[] imgAsBytes = inputStream.readAllBytes();
		File newFile = new File("D:\\OnlineHotelBooking\\src\\main\\webapp\\images\\" + photoPart.getSubmittedFileName().replaceAll("\\s+", "").trim());
		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		System.out.println(newFile.getAbsolutePath());
//		fileOutputStream.write(imgAsBytes);
//		fileOutputStream.flush();
//		fileOutputStream.close();

//		for (Part part : request.getParts()) {
//			System.out.println(part.getContentType());
//			System.out.println(part.getName());
//			System.out.println(part.getSize());
//			System.out.println(part.getSubmittedFileName());
//			for (String hname : part.getHeaderNames()) {
//				System.out.println(hname + "=" + part.getHeader(hname));
//			}
//		}
		PreparedStatement ps;
		try {
			ps = ConnectDB.getConnection().prepareStatement("insert into test (imagepath,image) values(?,?)");
			ps.setString(1, newFile.getName().toString());
			ps.setBlob(2, inputStream);
			ps.executeUpdate();
			ps.close();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("imageupload");
	}

}

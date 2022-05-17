
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.hotelbooking.Exception.DatabaseException;
import com.narola.hotelbooking.Utility.ConnectDB;

/**
 * Servlet implementation class BlobImageUpload
 */
public class BlobImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PreparedStatement ps;
		try {
			ps = ConnectDB.getInstance().getConnection().prepareStatement("select * from test where id=18");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Blob blob = rs.getBlob(3);
				byte bytearray[] = blob.getBytes(1, (int) blob.length());
				response.setContentType("image/gif");
				OutputStream os = response.getOutputStream();
				os.write(bytearray);
				os.flush();
				os.close();
			} else {
				System.out.println("no image found..");
			}
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Part filePart = request.getPart("photo");
//		InputStream inputStream = filePart.getInputStream();
//		byte[] imgAsBytes = inputStream.readAllBytes();
//
//		File newFile = new File("D:\\OnlineHotelBooking\\src\\main\\webapp\\images\\"
//				+ filePart.getSubmittedFileName().replaceAll("\\s+", "").trim());
		
//		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//		System.out.println(newFile.getAbsolutePath());
//		fileOutputStream.write(imgAsBytes);
//		fileOutputStream.flush();
//		fileOutputStream.close();
//
//		FileInputStream fis = null;
//
//		PreparedStatement ps;
//		try {
//			fis = new FileInputStream(newFile);
//			ps = ConnectDB.getConnection().prepareStatement("insert into test (imagepath,image) values(?,?)");
//			ps.setString(1, "");
//			ps.setBinaryStream(2, fis, (int) (newFile.length()));
//			ps.executeUpdate();
//			ps.close();
//			response.sendRedirect("blobimageupload");
//		} catch (DatabaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}

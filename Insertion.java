import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.PreparableStatement;

public class Insertion extends HttpServlet { 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("nm");
		long phno=Long.parseLong(req.getParameter("phn"));
		String password=req.getParameter("psw");
		int age =Integer.parseInt(req.getParameter("age"));
		
		Connection con=null;
		PreparableStatement pst=null;
		String inqry="insert into customer values(?,?,?,?,?)";
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/customertable", "root","admin");
			pst=con.prepareStatement(inqry);
			pst.setInt(1,id);
			pst.setString(2,name);
			pst.setLong(3,phno);
			pst.setString(4,password);
			pst.setInt(5,age);
			pst.execute();
			PrintWriter pw=resp.getWriter();
			pw.write("<html><body><h2>Customer detalils are inserted sucessfully to customer table</h2></body></html>")
		} 
		catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null)
				try {
					con.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			if(pst!=null)
				try {
					pst.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		}
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_table {
	public static void main(String[] args) {
		Connection connection=null;
		Statement statement=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/customertable","root","admin");
			statement=connection.createStatement();
			statement.execute("cretate table customer(id int not null,name varchar(34) null,phno bigint(43) null,"
					+ "password varchar(54) null,age int null,primary key(id))");
			System.out.println("table created sucessfully");
		} catch (SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(statement!=null) statement.close();
		}
	}
}

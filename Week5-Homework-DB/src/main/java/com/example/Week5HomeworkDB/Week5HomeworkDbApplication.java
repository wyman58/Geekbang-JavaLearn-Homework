package com.example.Week5HomeworkDB;

import com.example.Week5HomeworkDB.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class Week5HomeworkDbApplication {


	public static void main(String[] args) {

		SpringApplication.run(Week5HomeworkDbApplication.class, args);

		useJDBC();


	}


	private static void useJDBC() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection ("jdbc:h2:mem:demo", "sa","");
			statement = connection.createStatement();
			statement.execute("insert into USER (ID,NAME) VALUES (1, 'John')");
			statement.execute("update USER set NAME = 'Peter'");
			rs = statement.executeQuery("select NAME from USER");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			statement.execute("delete from USER");

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

}

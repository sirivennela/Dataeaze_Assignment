package utils;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ConnectDatabase {
	public static void main(String[] args) {
	      Connection con = null;
	      Statement stmt = null;
	      PreparedStatement ps = null;
	      int result =0;
	      String line = "";  
	      
	      try {
	         //Registering the HSQLDB JDBC driver
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         //Creating the connection with HSQLDB
	         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
	         /*
	         if (con!= null){
	            System.out.println("Connection created successfully");
	            
	         }else{
	            System.out.println("Problem with creating connection");
	         }
	         */
	         stmt = con.createStatement();
	         
	         result = stmt.executeUpdate("CREATE TABLE leedprojects_tbl8 ( ID VARCHAR(10) NOT NULL, Isconfidential VARCHAR(40) NOT NULL, "
	         		+ "ProjectName VARCHAR(50), Street VARCHAR(50), City VARCHAR(25), State VARCHAR(15), Zipcode VARCHAR(15), Country VARCHAR(25), LEEDSystemVersionDisplayName VARCHAR(50), "
	         		+ "PointsAchieved VARCHAR(50), CertLevel VARCHAR(80), CertDate VARCHAR(50), IsCertified VARCHAR(50), OwnerTypes VARCHAR(75), GrossSqFoot VARCHAR(20), TotalPropArea VARCHAR(50), "
	         		+ "ProjectTypes VARCHAR(80), OwnerOrganization VARCHAR(80),RegistrationDate VARCHAR(50));");
	         
	         Scanner sc = new Scanner(System.in);
	         System.out.println("Enter the path of csv file");
	         
	         BufferedReader br = new BufferedReader(new FileReader(sc.nextLine())); 
	         while ((line = br.readLine()) != null)   //returns a Boolean value  
	         {  
	        	 String tmp[]=line.split(",");
	        	 System.out.println(tmp.length);
	        	 String sql =
	                     "INSERT INTO leedprojects_tbl8 (ID,Isconfidential,ProjectName, Street,City,State,Zipcode,Country,LEEDSystemVersionDisplayName,PointsAchieved, CertLevel,CertDate,IsCertified ,OwnerTypes,GrossSqFoot,TotalPropArea,ProjectTypes,OwnerOrganization,RegistrationDate) values ('"
	                                                   + tmp[0]+"','"+tmp[1]+"','"+ tmp[2]+"','"+tmp[3]+"','"+tmp[4]+"','"+tmp[5]+"','"+ tmp[6]+"','"+tmp[7]+"','"+ tmp[8]+"','"+   tmp[9]+"','"+  tmp[10]+"','"+ tmp[11]+"','"+  tmp[12]+"','"+ tmp[13]+"','"+ tmp[14]+"','"+ tmp[15]+"','"+tmp[16]+"','"+ tmp[17]+"','"+ tmp[18]+"')";

	             ps = con.prepareStatement(sql);
	             ps.executeUpdate();
	         }
	         
	         /*
	         ParquetReader<GenericRecord> reader = AvroParquetReader.<GenericRecord>builder(file).build();
	         GenericRecord nextRecord = reader.read();
	         */
	      
	         br.close();
	         con.close();
	         ps.close();
	      }  
	      catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	      System.out.println(result+" rows effected");
	      System.out.println("Table created");
	}

}

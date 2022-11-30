package org.generation.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager {

	private static final String URL = "jdbc:mysql://localhost:3306/nations";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	 
	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			 final String query = " SELECT countries.name, countries.country_id, regions.name, continents.name FROM countries "
					 			+ " JOIN regions ON countries.region_id = regions.region_id "
			 					+ " JOIN continents ON regions.continent_id = continents.continent_id " 
					 			+ " ORDER BY countries.name ASC ";
			 
			try (PreparedStatement pst = con.prepareStatement(query)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						final String countryName = rs.getString(1);
						final int id = rs.getInt(2);
						final String reg_name = rs.getString(3);
						final String cont_name = rs.getString(4);
						System.out.println( "Nome Paese: " + countryName 
											+"\nID paese: " + id  
											+ "\nNome Regione: " + reg_name 
											+ "\nNome Continente: " + cont_name 
											+ "\n----------------------------------------------\n"
											);
					}
				}
			}
			con.close();
		} catch(SQLException SQLerr) {
			SQLerr.printStackTrace();
		}
	}
}






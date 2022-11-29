package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Articolo;


public class ArticoloDao {
	
	public void insert (Articolo a) {
		
		
		Connection conn=null;

        try {
        	
        	//controlliamo il driver connector per la connessione 
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
            	
            	
                String url="jdbc:mysql://localhost:3306/eserciziocompleto";
                String name="virna";
                String password="virna";

                //tentiamo la connessione
                
                conn = DriverManager.getConnection(url, name, password);

                
                //query inserimento 
                String query_in="INSERT INTO articolo(descrizione,quantita)VALUES(?,?)";

                PreparedStatement pstmt = conn.prepareStatement(query_in);//

                pstmt.setString(1,a.getDescrizione());

                pstmt.setInt(2,a.getQuantita());

          

                try {

                    pstmt.execute();

                }
                catch (Exception e) {

                    System.out.println(e);

                }





            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }




        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
			
			
			
		}
		
		
	}



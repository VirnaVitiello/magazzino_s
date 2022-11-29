package srv;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDao;
import model.Articolo;

public class ArticoloSrv extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	public ArticoloSrv() {
		super();
		//TODO Auto-generated constructor stub 
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		ArticoloDao  aDao= new ArticoloDao();
		String descrizione= request.getParameter("descrizione");
		String quantita1= request.getParameter("quantita");
		int quantita= Integer.parseInt(quantita1);
		
		
		Articolo a = new Articolo();
		a.setDescrizione(descrizione);
		a.setQuantita(quantita);
		
		aDao.insert(a);
		select(a,request,response);
		//Writer w;
	//	try {
	//		w = response.getWriter();
		//	w.write("<table>"
	    //    		+"<tr>"
	    //    		+"<td>Articolo</td>"
	      //  		+"<td>Descrizione</td>"
	       // 		+"<td>Quantità</td>"
	       // 		+"</tr>"
	       // 		+"<tr>"
	       // 		+"<td>Cella 4</td>"
	       // 		+"<td>Cella 5</td>"
	       // 		+"<td>Cella 6</td>"
	       // 		+"</tr>"
	      //  		+"</table>");
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
       
        
		
	}
	
	public void select (Articolo a,HttpServletRequest request, HttpServletResponse response) {
		
		
		Connection conn=null;
        PreparedStatement cmd=null;
        try {
        	
        	//controlliamo il driver connector per la connessione 
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
            	
            	
                String url="jdbc:mysql://localhost:3306/eserciziocompleto";
                String name="virna";
                String password="virna";

                //tentiamo la connessione
                
                conn = DriverManager.getConnection(url, name, password);
                
                String query_se= "SELECT * FROM articolo";
                PreparedStatement pstmt = conn.prepareStatement(query_se);//

                try {
                	cmd=conn.prepareStatement(query_se);
                
                		try {
                            Writer w=response.getWriter();
                			ResultSet ris=pstmt.executeQuery();
                			w.write("<table>");
                			while(ris.next())
		                    {
		                    	w.write("<tr><td>"+ris.getString(1)+"</td>"
		                    	+"<td>"+(ris.getString(2)+"</td>"
		                    	+"<td>"+ris.getString(3)+"</td></tr>"));
		                    } 
                			
                			w.write("</table>");
		                }
		        catch (Exception e) {
		            System.out.println(e);
		        }



            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                
            } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	}      
	

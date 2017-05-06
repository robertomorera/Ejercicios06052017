package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class SubirImagen
 */
@WebServlet("/SubirVideo")
public class SubirVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubirVideo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre_fichero_salida = request.getParameter("fichero");
		nombre_fichero_salida = nombre_fichero_salida + ".mp4";
		
		response.setStatus(HttpURLConnection.HTTP_OK);//le digo que ok!
		response.setContentType("video/mp4");//y que le devuelvo un png
		ServletOutputStream sos = response.getOutputStream();
		
		byte buffer_lectura [] = new byte [1024*5];
		
		InputStream is = new FileInputStream(new File (nombre_fichero_salida));
		
		
		int leido = 0;
		while ((leido = is.read(buffer_lectura))!=-1)
			{
				sos.write(buffer_lectura, 0, leido);
			}
		
		is.close();
		
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre_fichero_salida = null;
		OutputStream os  = null;
		byte[] imgbytes = null;
				
			
		
			System.out.println("POST RECIBIDO! ...");	
			
			nombre_fichero_salida = request.getParameter("fichero");
			nombre_fichero_salida = nombre_fichero_salida + ".mp4";
			
			byte [] buffer_read = new byte[1024*5];
			
			try (BufferedInputStream bis = new BufferedInputStream(request.getInputStream());BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(nombre_fichero_salida));) 
			{
			
				int byteleido = 0;
					
			    while ((byteleido = bis.read(buffer_read))!=-1)
			   	{
			    	bos.write(buffer_read, 0, byteleido);
			   	}
				    
			   // System.out.println("Fichero creado!  en " + 
				    
			} 
		catch (Exception e)
			{
				e.printStackTrace();
			}
		
	}

}
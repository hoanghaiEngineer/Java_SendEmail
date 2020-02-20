

import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class JavaSendEmail
 */
@WebServlet("/JavaSendEmail")
public class JavaSendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JavaSendEmail() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		todo();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		todo();
	}
	
	public void todo() {
		String result;
		// Recipient's email ID needs to be mentioned.
		String to = "hoanghai.itcmu@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "hoanghai.itcmu@gmail.com";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties object
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host","smtp.gmail.com"); 
		properties.setProperty("mail.smtp.port", "587");

		// Get the default Session object.
		//Session mailSession = Session.getDefaultInstance(properties);
		
		Session mailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication("hoanghai.itcmu@gmail.com", "Hoanghai1991"); 
				} 
			});

		try{
			// Create a default MimeMessage object.
		    MimeMessage message = new MimeMessage(mailSession);
		    
		    // Set From: header field of the header.
		    message.setFrom(new InternetAddress(from));
		    
		    // Set To: header field of the header.
		    message.addRecipient(Message.RecipientType.TO,
		                               new InternetAddress(to));
		    // Set Subject: header field
		    message.setSubject("This is the Subject Line!");
		    // Now set the actual message
		    message.setText("This is actual message");
		    // Send message
		    Transport.send(message);
		    result = "Sent message successfully....";
		    System.out.println(result);
		  }catch (MessagingException mex) {
		    mex.printStackTrace();
		    result = "Error: unable to send message....";
		    System.out.println(result);
		  }
	}

}

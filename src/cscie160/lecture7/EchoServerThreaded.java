import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServerThreaded implements Runnable
{  
    private Socket incoming;

    public EchoServerThreaded(Socket incoming)
    {
	this.incoming = incoming;
    }

    public void run()
    {
	try {
	    serviceClient();
	} catch (java.io.IOException eio) {
	    System.out.println(eio);
	}
    }

    public void serviceClient() throws IOException
    {
	  Scanner socketScanner = new Scanner(incoming.getInputStream());
	  PrintWriter outputToSocket = new PrintWriter
	    (incoming.getOutputStream(), true /* autoFlush */);
	  
	  outputToSocket.println( "Hello! Enter BYE to exit." );
	  boolean done = false;
	  while (!done) {  
	    String line = socketScanner.nextLine();
            if (line == null) 
	      done = true;
            else   
	      System.out.println(line);

	      if (line.trim().toUpperCase().startsWith("BYE")) {
		outputToSocket.println("Echo: Bye, Bye, Come Again!");
		System.out.println("Echo: Bye, Bye, Come Again!");
		done = true;
	      }
	      else
		outputToSocket.println(line);
	  }
	  incoming.close();
    }

    public static void main(String[] args )
    {  
	try {  
	    System.out.println("Creating ServerSocket on 8189");
	    ServerSocket s = new ServerSocket(8189);
	    while(true) {
		System.out.println("Looking for a client. CQ, CQ, CQ!");
		Socket incoming = s.accept( );
		System.out.println("Server got socket, internal port = " + 
				   incoming.getLocalPort() + 
				   ", external port = " + 
				   incoming.getPort());
		Thread thread = new Thread(new EchoServerThreaded(incoming));
		thread.start();
		System.out.println("Client connected");
	    }
	}
	catch (Exception e)
	    {  
		System.out.println(e);
	    }
    }
}



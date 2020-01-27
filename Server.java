/**
* Sukriti Agarwal
* Ping Chih Wang
* Group 4
* CECS 327
* Due Date: 11/15/2019
* Homework 3 - Socket Programming
* Program to write an Echo Server that receives the data from the
* client and echo it back to the client.
*/

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("\nArguments Error \nType: \"Port Number\"\n");
			return;
		}

		// initiate DatagramSocket to null
		DatagramSocket aSocket = null;

		try { 
			// define port from the first argument
			int port = Integer.parseInt(args[0]);

			// initiate the DatagramSocket on the provided port
			aSocket = new DatagramSocket(port);
			
			System.out.println("Booting Echo Server...");
			System.out.println("\nPort:  " + port);
			
			// initiate a data buffer to hold the data coming from the client
			byte[] buffer = new byte[50];
			
			while (true) { 
				System.out.println("\nWaiting for an echo request...");

				// initiate DatagramPacket to receive packet from client into the data buffer
				DatagramPacket receivePacket  = new DatagramPacket(buffer,  buffer.length);

				// receive the packet from the client on the same socket.
				aSocket.receive(receivePacket);
				
				// display the request information
				System.out.println("\nRequest received!");
				System.out.println("Sending back data: " + new String(receivePacket.getData()));
				System.out.println("IP Address: " + receivePacket.getAddress());

				// initiate DatagramPacket to send the packet back to client on the same address and port
				DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(), receivePacket.getAddress(), receivePacket.getPort());

				// send the packet back to the client on the same socket.
				aSocket.send(sendPacket);

				// clear the buffer
				buffer = new byte[50];
			}
		}
		// catch phrases to display any errors while running the code.
		catch (SocketException e) { 
			System.out.println("Socket:  " + e.getMessage());
		}
		catch (IOException  e) {
			System.out.println("IO:  " + e.getMessage());
		}
		// finally statement to close the socket if the code ran successfully.
		finally {
			if(aSocket != null) { 
				aSocket.close();
			}
		}
	}
}
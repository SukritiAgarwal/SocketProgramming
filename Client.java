/**
* Sukriti Agarwal
* Ping Chih Wang
* Group 4
* CECS 327
* Due Date: 11/15/2019
* Homework 3 - Socket Programming
* Program to write an Echo Client that prompts the user for a line of input, 
* and sends that input to the server and displays whatever the server sends back.
*/

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("\nArguments Error \nType: \"Server Address\" and \"Port Number\"\n");
			return;
		}
		
		// initiate DatagramSocket to null
		DatagramSocket aSocket  = null;

		Scanner input = new Scanner(System.in);

		try {
			// define InetAddress from the first argument
			InetAddress address = InetAddress.getByName(args[0]);
			
			// define port from the second argument
			int port = Integer.parseInt(args[1]);

			System.out.println("Booting Echo Client...");
			System.out.println("\nAddress:  " + address.getHostAddress());
			System.out.println("Port   :  " + port);
			System.out.println("\nEnter message to echo");
			System.out.print("Message: ");

			String message = input.nextLine();
			
			// initiate the DatagramSocket on the provided port
			aSocket = new DatagramSocket();

			// convert the message to bytes of data in an array
			byte[] bytesToSend = message.getBytes();
			
			// initiate DatagramPacket to send the packet to the server on the inet Address  and port
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, address, port);
			
			// send the packet back to the server.
			aSocket.send(sendPacket);

			// Sent message
			System.out.println("\nSend: "  + new String(sendPacket.getData()));			

			// initiate a data buffer to hold the data coming back from the server
			byte[] buffer = new byte[50];

			// initiate DatagramPacket to receive packet coming from the server into the data buffer
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

			// receive the packet from the server on the same socket.
			aSocket.receive(receivePacket);

			//Received message
			System.out.println("Reply: "  + new String(receivePacket.getData()) + "\n");
		}

		catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
		finally {
			if(aSocket != null) {
				aSocket.close();
			}
			input.close();
		}
	}
}
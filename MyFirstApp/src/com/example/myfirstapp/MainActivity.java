package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends Activity {

	private Socket socket;

	private static final int SERVERPORT = 42421;
	private static final String SERVER_IP = "10.0.2.2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Thread(new ClientThread()).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendMessage(View view) {
		if (socket != null) {
			try {
				PrintWriter out;
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);
				out.println("REMOTEDOWN");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class ClientThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				System.out.println("Trying to connect");
				try {
					InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
					socket = new Socket(serverAddr, SERVERPORT);
					try {
						PrintWriter out;
						out = new PrintWriter(new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream())), true);
						out.println("USERNAME:REMOTEAPP");
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

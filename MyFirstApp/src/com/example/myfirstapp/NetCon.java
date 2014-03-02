package com.example.myfirstapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class NetCon extends AsyncTask<Socket, Void, Socket> {

	@Override
	protected Socket doInBackground(Socket... arg0) {
		Socket socket = arg0[0];
		try {
			socket = new Socket("SAM_GOLDMAN", 42421);
			PrintWriter out ;
			try {
				out = new PrintWriter(socket.getOutputStream());
				out.println("REMOTEAPP");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return socket;
	}

}

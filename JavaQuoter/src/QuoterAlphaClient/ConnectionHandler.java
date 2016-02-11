package QuoterAlphaClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import QuoterAlphaServer.Quote;

public class ConnectionHandler {
	private ClientApp app;
	private int port;
	private Socket socket = null;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;

	public ConnectionHandler(ClientApp clientApp, int port) {
		
		this.app = clientApp;
		try {

			this.port = port;

			socket = new Socket("localhost", this.port);

			objectInput = new ObjectInputStream(socket.getInputStream());

			objectOutput = new ObjectOutputStream(socket.getOutputStream());
			
			objectOutput.flush();
			
			app.setListQuotes((List<Quote>) objectInput.readObject());
			for (Quote quote : app.getListQuotes()) {
				System.out.println(quote);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				objectOutput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addQuote(Quote quote) {
		try {
			objectOutput.writeObject(quote);
			objectOutput.flush();
		} catch (IOException e) {
			try {
				objectOutput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void removeQuote(Quote quote) {
		try {
			quote.setForRemoving(true);
			objectOutput.writeObject(quote);
			objectOutput.flush();
		} catch (IOException e) {
			try {
				objectOutput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
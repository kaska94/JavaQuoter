package QuoterAlphaServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Server {

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		ServerThread thread = new ServerThread();
		thread.run();
	}

	public class ServerThread implements Runnable {

		protected int serverPort = 8080;
		protected ServerSocket serverSocket = null;
		protected BufferedReader input = null;
		protected InputStreamReader inputStreamReader = null;
		protected PrintWriter output = null;
		protected Connection conectionToDB = null;
		public ServerThread thread = null;
		protected Statement myStmt = null;
		protected ResultSetMetaData rsmd = null;

		private ResultSet myRs = null;

		private ResultSet mySecondRs = null;

		public void run() {

			openServerSocket();
			openDBSocket();

			while (true) {
				Worker w;

				try {
					w = new Worker(serverSocket.accept(), thread);
					Thread t = new Thread(w);
					t.start();

				} catch (IOException e) {
					System.out.println("Accept failed: 8080");
					System.exit(-1);
				}

			}

		}

		private void openServerSocket() {
			try {
				this.serverSocket = new ServerSocket(this.serverPort);
			} catch (IOException e) {
				throw new RuntimeException("Cannot open port 8080", e);
			}
		}

		public void openDBSocket() {
			try {
				this.conectionToDB = DriverManager.getConnection(
						"jdbc:mysql://	", "quoter", "");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public ResultSet getMyRs() {
			return myRs;
		}

		public void setMyRs(ResultSet myRs) {
			this.myRs = myRs;
		}

		public ResultSet getMySecondRs() {
			return mySecondRs;
		}

		public void setMySecondRs(ResultSet mySecondRs) {
			this.mySecondRs = mySecondRs;
		}

		public int getServerPort() {
			return serverPort;
		}

		public void setServerPort(int serverPort) {
			this.serverPort = serverPort;
		}

		public ServerSocket getServerSocket() {
			return serverSocket;
		}

		public void setServerSocket(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;
		}

		public BufferedReader getInput() {
			return input;
		}

		public void setInput(BufferedReader input) {
			this.input = input;
		}

		public InputStreamReader getInputStreamReader() {
			return inputStreamReader;
		}

		public void setInputStreamReader(InputStreamReader inputStreamReader) {
			this.inputStreamReader = inputStreamReader;
		}

		public PrintWriter getOutput() {
			return output;
		}

		public void setOutput(PrintWriter output) {
			this.output = output;
		}

		public Connection getConectionToDB() {
			return conectionToDB;
		}

		public void setConectionToDB(Connection conectionToDB) {
			this.conectionToDB = conectionToDB;
		}

		public ServerThread getThread() {
			return thread;
		}

		public void setThread(ServerThread thread) {
			this.thread = thread;
		}

		public Statement getMyStmt() {
			return myStmt;
		}

		public void setMyStmt(Statement myStmt) {
			this.myStmt = myStmt;
		}

		public ResultSetMetaData getRsmd() {
			return rsmd;
		}

		public void setRsmd(ResultSetMetaData rsmd) {
			this.rsmd = rsmd;
		}

		public ServerThread() {
			this.thread = this;
		}
	}

}

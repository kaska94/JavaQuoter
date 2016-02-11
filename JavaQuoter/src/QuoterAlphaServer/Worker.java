package QuoterAlphaServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import QuoterAlphaServer.Server.ServerThread;

public class Worker implements Runnable {
	private Socket client;
	private ServerThread server;
	private List<Quote> listQuotes = new ArrayList<Quote>();
	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;

	Worker(Socket client, ServerThread server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void run() {

		try {

			objectOutput = new ObjectOutputStream(client.getOutputStream());
			objectInput = new ObjectInputStream(client.getInputStream());

			Statement myStmt = (Statement) server.thread.conectionToDB
					.createStatement();
			ResultSet myRs = myStmt
					.executeQuery("select * from quoter.authors");

			while (myRs.next()) {
				Quote quote = new Quote();
				quote.setAuthor(myRs.getString(2));
				listQuotes.add(quote);
			}
			myStmt.close();

			Statement myStmt1 = (Statement) server.thread.conectionToDB
					.createStatement();
			ResultSet myRs1 = myStmt1
					.executeQuery("select * from quoter.quote");
			for (Quote q : listQuotes) {
				if (myRs1.next()) {
					q.setQuote(myRs1.getString(2));
					System.out.println(q);
				}
			}
			myStmt1.close();

			objectOutput.writeObject(listQuotes);

			while (true) {
				try {
					Quote newQuote = (Quote) objectInput.readObject();
					if (newQuote.isForRemoving) {
						executeDeleteQuerrys(newQuote);
					} else {
						executeUpdateQuerys(newQuote);
					}

				} catch (SocketException | ClassNotFoundException e) {
				}

			}

		} catch (IOException | SQLException e) {
			/*
			 * try{ if (server.getMyRs() != null) server.getMyRs().close(); if
			 * (server.getMyStmt() != null) server.getMyStmt().close(); if
			 * (server.getConectionToDB() != null)
			 * server.getConectionToDB().close();
			 * 
			 * }catch(Exception e1 ){ e1.printStackTrace(); }
			 */
			e.printStackTrace();
		}
	}

	public void executeDeleteQuerrys(Quote quoteForRemoving) {
		try {
			// Creating statement
			String authorQuery = "DELETE FROM quoter.authors WHERE name = '"
					+ quoteForRemoving.getAuthor() + "';";
			Statement statement = (Statement) server.getConectionToDB()
					.createStatement();

			// Check if it fails
			int affectedRowsAuthor = statement.executeUpdate(authorQuery,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRowsAuthor == 0) {
				throw new SQLException(
						"Adding Author failed, no rows affected.");
			}
		} catch (SQLException e) {
			System.out.println("Error!");
			try {
				objectOutput.close();
				objectInput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("Done");
	}

	public void executeUpdateQuerys(Quote newQuote)
			throws ClassNotFoundException, IOException {
		try {
			// Creating statement
			String authorQuery = "INSERT INTO quoter.authors (name) VALUES ('"
					+ newQuote.author + "');";
			Statement statement = (Statement) server.getConectionToDB()
					.createStatement();

			// Check if it fails
			int affectedRowsAuthor = statement.executeUpdate(authorQuery,
					Statement.RETURN_GENERATED_KEYS);

			if (affectedRowsAuthor == 0) {
				throw new SQLException(
						"Adding Author failed, no rows affected.");
			}
			// If not get last inserted Id (generatedKeys.getLong(1)) and insert
			// second query
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					long authorIdUpdated = generatedKeys.getLong(1);
					String quoteQuery = "INSERT INTO quoter.quote (text, id_author)  VALUES ('"
							+ newQuote.getQuote()
							+ "', "
							+ authorIdUpdated
							+ ");";
					int affectedRowsQuote = statement.executeUpdate(quoteQuery,
							Statement.RETURN_GENERATED_KEYS);

					if (affectedRowsQuote == 0) {
						throw new SQLException(
								"Adding Quote failed, no rows affected.");
					}
				} else {
					throw new SQLException(
							"Creating user failed, no ID obtained.");
				}
			}

			System.out.println("Done");
		} catch (SQLException e) {
			System.out.println("Error!");
			try {
				objectOutput.close();
				objectInput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}

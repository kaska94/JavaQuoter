package QuoterAlphaClient;

import javax.swing.JButton;

import QuoterAlphaServer.Quote;

public class AddQuoteButton extends JButton {
	private EastQuotePanel panel;

	public AddQuoteButton(String name, EastQuotePanel panel) {
		super(name);
		this.panel = panel;
	}

	public void addQuote() {
		Quote newQuote = new Quote(panel.getTextFieldAuthor().getText(), panel
				.getTextAreaQuote().getText());
		try {
			panel.getApp().getListQuotes().add(newQuote);
			panel.getApp().getConnection().addQuote(newQuote);
			ClientApp.getFrame().getWestPanel().addQuoteInPanel(newQuote);
		} catch (Exception e) {
			System.out.println("Something went Wrong in adding the Quote...");
		}
	}
}

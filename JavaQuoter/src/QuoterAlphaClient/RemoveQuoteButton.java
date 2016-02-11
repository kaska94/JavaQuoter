package QuoterAlphaClient;

import java.util.Iterator;

import javax.swing.JButton;

public class RemoveQuoteButton extends JButton {
	private EastQuotePanel panel;

	public RemoveQuoteButton(String name, EastQuotePanel panel) {
		super(name);
		this.panel = panel;
	}

	public void removeQuote() {
		
		Iterator<QuoteTextArea> i = WestQuotePanel.getList().iterator();
		while (i.hasNext()) {
			QuoteTextArea textArea = i.next();
			if (textArea.isSelected()) {
				panel.getApp().getListQuotes().remove(textArea.getQuote());
				panel.getApp().getConnection().removeQuote(textArea.getQuote());
				ClientApp.getFrame().getWestPanel().removeQuoteInPanel(textArea);
				i.remove();
			}
		}
	}
}

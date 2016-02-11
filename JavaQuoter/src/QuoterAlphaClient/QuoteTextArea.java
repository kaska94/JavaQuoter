package QuoterAlphaClient;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import QuoterAlphaServer.Quote;

public class QuoteTextArea extends JTextArea {
	// private QuoteTextArea area;
	private WestQuotePanel panel;
	private Quote quote;

	private boolean isSelected;

	public QuoteTextArea(Quote quote, WestQuotePanel panel) {
		super(10, 10);
		this.quote = quote;
		this.panel = panel;
		isSelected = false;

		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		setLineWrap(true);
		setWrapStyleWord(true);
		setSize(50, 200);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setText(quote.getQuote() + "\n\n" + quote.getAuthor());
		setEditable(false);
		setBackground(new Color(233, 233, 226));

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isSelected()) {
					setBorder(BorderFactory.createLineBorder(Color.BLACK));
					isSelected = true;
				} else {
					setBorder(BorderFactory
							.createEtchedBorder(EtchedBorder.LOWERED));
					isSelected = false;
				}
			}
		});

	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public QuoteTextArea getArea() {
		return this;
	}
}

package QuoterAlphaClient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EastQuotePanel extends JPanel {
	
	
	private AddQuoteButton addButton;
	private RemoveQuoteButton removeButton;
	private JLabel authorLabel;
	private JLabel quote;
	private JTextField textFieldAuthor;
	private JTextArea textAreaQuote;
	private ClientApp app;

	public EastQuotePanel(ClientApp app) {
		this.app = app;

		Dimension size = getPreferredSize();
		size.width = 200;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("Quote"));

		addButton = new AddQuoteButton("Add", this);
		removeButton = new RemoveQuoteButton("Remove", this);

		authorLabel = new JLabel("Author: ");
		quote = new JLabel("Quote: ");

		textFieldAuthor = new JTextField(12);
		textAreaQuote = new JTextArea(12, 12);
		textAreaQuote.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		textAreaQuote.setLineWrap(true);
		textAreaQuote.setWrapStyleWord(true);
		
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		
		// First Column
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.5;
		gc.weighty = 0.5;

		// AUTHOR LABEL
		gc.gridx = 0;
		gc.gridy = 0;
		add(authorLabel, gc);

		// QUOTE LABEL
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		add(quote, gc);

		// Second Column

		// AUTHOR TEXT FIELD
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(textFieldAuthor, gc);

		// TEXT AREA QUOTE
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(textAreaQuote, gc);

		// Final row

		// ADD BUTTON
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		addButton.setEnabled(false);
		add(addButton, gc);

		// REMOVE BUTTON
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		add(removeButton, gc);

		// FUNCTIONALITY
		textFieldAuthor.getDocument().addDocumentListener(
				new TextListener(this));

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = "Add";
				if (s.equals((e.getActionCommand()))) {
					addButton.addQuote();
				}
			}
		});
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = "Remove";
				if (s.equals((e.getActionCommand()))) {
					removeButton.removeQuote();
				}
			}
		});

	}

	public ClientApp getApp() {
		return app;
	}

	public void setApp(ClientApp app) {
		this.app = app;
	}

	public AddQuoteButton getAddButton() {
		return addButton;
	}

	public void setAddButton(AddQuoteButton addButton) {
		this.addButton = addButton;
	}

	public RemoveQuoteButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(RemoveQuoteButton removeButton) {
		this.removeButton = removeButton;
	}

	public JLabel getAuthorLabel() {
		return authorLabel;
	}

	public void setAuthorLabel(JLabel authorLabel) {
		this.authorLabel = authorLabel;
	}

	public JLabel getQuote() {
		return quote;
	}

	public void setQuote(JLabel quote) {
		this.quote = quote;
	}

	public JTextField getTextFieldAuthor() {
		return textFieldAuthor;
	}

	public void setTextFieldAuthor(JTextField textFieldAuthor) {
		this.textFieldAuthor = textFieldAuthor;
	}

	public JTextArea getTextAreaQuote() {
		return textAreaQuote;
	}

	public void setTextAreaQuote(JTextArea textAreaQuote) {
		this.textAreaQuote = textAreaQuote;
	}
}

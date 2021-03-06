package QuoterAlphaClient;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import QuoterAlphaClient.EastQuotePanel;

public class MainFreme extends JFrame {

	private EastQuotePanel eastPanel;
	private WestQuotePanel westPanel;

	private ClientApp app;

	public MainFreme(String title, ClientApp app) {
		super(title);
		this.app = app;

		setLayout(new BorderLayout());

		eastPanel = new EastQuotePanel(app);
		westPanel = new WestQuotePanel(app);
		Container c = getContentPane();

		c.add(westPanel, BorderLayout.CENTER);
		c.add(eastPanel, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(100, 50));
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(westPanel);
	}

	public EastQuotePanel getEastPanel() {
		return eastPanel;
	}

	public void setEastPanel(EastQuotePanel eastPanel) {
		this.eastPanel = eastPanel;
	}

	public WestQuotePanel getWestPanel() {
		return westPanel;
	}

	public void setWestPanel(WestQuotePanel westPanel) {
		this.westPanel = westPanel;
	}
}

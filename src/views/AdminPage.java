package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame {
	JPanel northPanel, centerPanel, southPanel, insertPanel, updatePanel, deletePanel;
	JLabel listLbl, idLbl, titleLbl, artistLbl, albumLbl;
	JLabel idULbl, titleULbl, artistULbl, albumULbl;
	JTextField idTxt, titleTxt, artistTxt, albumTxt;
	JTextField idUTxt, titleUTxt, artistUTxt, albumUTxt;
	JButton insertBtn, updateBtn, deleteBtn;
	JTable table;
	DefaultTableModel data;
	JScrollPane scroll;
	JTabbedPane tabs;

	public AdminPage() {
		initialize();
		setVisible(true);
		setSize(new Dimension(1000, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addListener();
	}

	private void addListener() {
		
	}

	private void initialize() {
		northPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(2, 1));
		southPanel = new JPanel();
		insertPanel = new JPanel(new GridLayout(3, 2));
		updatePanel = new JPanel(new GridLayout(4, 2));
		deletePanel = new JPanel(new GridLayout(1, 2));
		
		listLbl = new JLabel("Song List");

		// Delete
		idLbl = new JLabel("Song ID");

		// Insert
		titleLbl = new JLabel("Song Title");
		artistLbl = new JLabel("Song Artist");
		albumLbl = new JLabel("Song Album");

		// Update
		idULbl = new JLabel("Song ID");
		titleULbl = new JLabel("Song Title");
		artistULbl = new JLabel("Song Artist");
		albumULbl = new JLabel("Song Album");

		// Delete
		idTxt = new JTextField();

		// Insert
		titleTxt = new JTextField();
		albumTxt = new JTextField();
		artistTxt = new JTextField();

		// Update
		idUTxt = new JTextField();
		titleUTxt = new JTextField();
		albumUTxt = new JTextField();
		artistUTxt = new JTextField();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");

		tabs = new JTabbedPane();

		Vector<Object> header = new Vector<>();
		header.add("ID");
		header.add("Title");
		header.add("Artist");
		header.add("Album");

		data = new DefaultTableModel(header, 0);
		table = new JTable(data) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		addListener();

		scroll = new JScrollPane(table);

		northPanel.add(listLbl);

		deletePanel.add(idLbl);
		deletePanel.add(idTxt);

		insertPanel.add(titleLbl);
		insertPanel.add(titleTxt);
		insertPanel.add(artistLbl);
		insertPanel.add(artistTxt);
		insertPanel.add(albumLbl);
		insertPanel.add(albumTxt);

		updatePanel.add(idULbl);
		updatePanel.add(idUTxt);
		updatePanel.add(titleULbl);
		updatePanel.add(titleUTxt);
		updatePanel.add(artistULbl);
		updatePanel.add(artistUTxt);
		updatePanel.add(albumULbl);
		updatePanel.add(albumUTxt);

		tabs.add("Insert Song", insertPanel);
		tabs.add("Update Song", updatePanel);
		tabs.add("Delete Song", deletePanel);

		centerPanel.add(scroll);
		centerPanel.add(tabs);

		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);

		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

}

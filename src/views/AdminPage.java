package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import facades.AuthFacade;
import facades.ProductFacade;
import models.products.Product;

public class AdminPage extends JFrame {
	private JPanel allPanel, northPanel, centerPanel, southPanel, insertPanel, updatePanel, deletePanel;
	private JLabel listLbl, idLbl, nameLbl, stockLbl, priceLbl;
	private JLabel idULbl, nameULbl, stockULbl, priceULbl;
	private JTextField idTxt, nameTxt, stockTxt, priceTxt;
	private JTextField idUTxt, nameUTxt, stockUTxt, priceUTxt;
	private JButton insertBtn, updateBtn, deleteBtn;
	private JTable table;
	private DefaultTableModel data;
	private JScrollPane scroll;
	private JTabbedPane tabs;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	private Vector<Product> products = new Vector<>();

	public AdminPage() {
		initialize();
		setVisible(true);
		setSize(new Dimension(1000, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addListener();
		loadData();
	}

	private void addListener() {
		tabs.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				tabs = (JTabbedPane) e.getSource();
				int selectedTab = tabs.getSelectedIndex();

				if (selectedTab == 0) {
					insertBtn.setEnabled(true);
					deleteBtn.setEnabled(false);
					updateBtn.setEnabled(false);
				} else if (selectedTab == 1) {
					deleteBtn.setEnabled(false);
					insertBtn.setEnabled(false);
					updateBtn.setEnabled(true);
				} else {
					deleteBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					updateBtn.setEnabled(false);
				}
			}
		});
		
		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTxt.getText();
				String price = priceTxt.getText();
				String stock = stockTxt.getText();

				boolean inserted = ProductFacade.getInstance().insertProduct(name, price, stock);
				
				if(inserted == false) {
					JOptionPane.showMessageDialog(null, ProductFacade.getInstance().getErrorMsg());
				}
				else {
					loadData();;
					
					idTxt.setText("");
					nameTxt.setText("");
					priceTxt.setText("");
					stockTxt.setText("");
				}
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = idUTxt.getText();
				String name = nameUTxt.getText();
				String price = priceUTxt.getText();
				String stock = stockUTxt.getText();

				boolean updated = ProductFacade.getInstance().updateProduct(id, name, price, stock);
				if(!updated) {
					JOptionPane.showMessageDialog(null, ProductFacade.getInstance().getErrorMsg());
				}else {
					loadData();
					
					idTxt.setText("");
					nameTxt.setText("");
					priceTxt.setText("");
					stockTxt.setText("");
				}
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTxt.getText();

				boolean deleted = ProductFacade.getInstance().deleteSong(id);
				
				if(!deleted) {
					JOptionPane.showMessageDialog(null, ProductFacade.getInstance().getErrorMsg());
				}else {
					loadData();
					
					idTxt.setText("");
					nameTxt.setText("");
					priceTxt.setText("");
					stockTxt.setText("");
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int selectedTab = tabs.getSelectedIndex();
				if (selectedTab == 0) {
					nameTxt.setText(data.getValueAt(row, 1).toString());
					priceTxt.setText(data.getValueAt(row, 2).toString());
					stockTxt.setText(data.getValueAt(row, 3).toString());

					idTxt.setText("");
					idUTxt.setText("");
					nameUTxt.setText("");
					priceUTxt.setText("");
					stockUTxt.setText("");
				} else if (selectedTab == 1) {
					idUTxt.setText(data.getValueAt(row, 0).toString());
					nameUTxt.setText(data.getValueAt(row, 1).toString());
					priceUTxt.setText(data.getValueAt(row, 2).toString());
					stockUTxt.setText(data.getValueAt(row, 3).toString());

					idTxt.setText("");
					nameTxt.setText("");
					priceTxt.setText("");
					stockTxt.setText("");
				} else {
					idTxt.setText(data.getValueAt(row, 0).toString());

					nameTxt.setText("");
					priceTxt.setText("");
					stockTxt.setText("");
					idUTxt.setText("");
					nameUTxt.setText("");
					priceUTxt.setText("");
					stockUTxt.setText("");
				}
			}
		});

		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthFacade.getInstance().logout();
				new LoginPage();
				dispose();
			}
		});
	}

	private void initialize() {
		allPanel = new JPanel(new BorderLayout());
		allPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		northPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(2, 1));
		southPanel = new JPanel();
		insertPanel = new JPanel(new GridLayout(3, 2));
		updatePanel = new JPanel(new GridLayout(4, 2));
		deletePanel = new JPanel(new GridLayout(1, 2));
		
		listLbl = new JLabel("Product List");

		// Delete
		idLbl = new JLabel("Product ID");

		// Insert
		nameLbl = new JLabel("Product Name");
		stockLbl = new JLabel("Product Stock");
		priceLbl = new JLabel("Product Price");

		// Update
		idULbl = new JLabel("Product ID");
		nameULbl = new JLabel("Product Name");
		stockULbl = new JLabel("Product Stock");
		priceULbl = new JLabel("Product Price");

		// Delete
		idTxt = new JTextField();

		// Insert
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();

		// Update
		idUTxt = new JTextField();
		nameUTxt = new JTextField();
		priceUTxt = new JTextField();
		stockUTxt = new JTextField();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");
		
		insertBtn.setEnabled(true);
		deleteBtn.setEnabled(false);
		updateBtn.setEnabled(false);

		tabs = new JTabbedPane();

		Vector<Object> header = new Vector<>();
		header.add("ID");
		header.add("Name");
		header.add("Price");
		header.add("Stock");

		data = new DefaultTableModel(header, 0);
		table = new JTable(data) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		scroll = new JScrollPane(table);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Hello admin!");
		menuItem = new JMenuItem("Logout");
		
		menu.add(menuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		northPanel.add(listLbl);

		deletePanel.add(idLbl);
		deletePanel.add(idTxt);

		insertPanel.add(nameLbl);
		insertPanel.add(nameTxt);
		insertPanel.add(priceLbl);
		insertPanel.add(priceTxt);
		insertPanel.add(stockLbl);
		insertPanel.add(stockTxt);

		updatePanel.add(idULbl);
		updatePanel.add(idUTxt);
		updatePanel.add(nameULbl);
		updatePanel.add(nameUTxt);
		updatePanel.add(stockULbl);
		updatePanel.add(stockUTxt);
		updatePanel.add(priceULbl);
		updatePanel.add(priceUTxt);

		tabs.add("Insert Product", insertPanel);
		tabs.add("Update Product", updatePanel);
		tabs.add("Delete Product", deletePanel);

		centerPanel.add(scroll);
		centerPanel.add(tabs);

		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);

		allPanel.add(northPanel, BorderLayout.NORTH);
		allPanel.add(centerPanel, BorderLayout.CENTER);
		allPanel.add(southPanel, BorderLayout.SOUTH);
		
		add(allPanel);
	}

	public void setData() {
		data.setRowCount(0);
		
		for (Product p : products) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(p.getId());
			rowData.add(p.getName());
			rowData.add(p.getPrice());
			rowData.add(p.getStock());
			data.addRow(rowData);
		}
	}

	public void loadData() {
		products = ProductFacade.getInstance().getAllProducts();
				
		setData();
	}
}

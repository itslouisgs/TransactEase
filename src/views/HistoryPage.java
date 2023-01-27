package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import models.order.Order;
import models.order.OrderDetail;
import models.products.Product;
import utils.AuthService;

public class HistoryPage extends JFrame {
	private JPanel panel;
	private JLabel lblTitle;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem1, menuItem2;
	private JTable table;
	private DefaultTableModel data;
	private JScrollPane scroll;
	
	private Vector<Order> orders = new Vector<>();

	public HistoryPage() {
		initialize();
		setVisible(true);
		setSize(new Dimension(600, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addListener();
		loadData();
	}

	private void initialize() {
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		lblTitle = new JLabel("Your History");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		menuBar = new JMenuBar();
		menu = new JMenu("Hello user!");
		menuItem1 = new JMenuItem("Order");
		menuItem2 = new JMenuItem("Logout");
		
		menu.add(menuItem1);
		menu.add(menuItem2);
		
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		
		Vector<Object> header = new Vector<>();
		header.add("No.");
		header.add("Product Name");
		header.add("Product Price");
		header.add("Quantity");
		header.add("Status");

		data = new DefaultTableModel(header, 0);
		table = new JTable(data) {
			public boolean isCellEditable(int row, int column) 		{
				return true;
			};
			
			public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
			
			public Component prepareRenderer(
	                TableCellRenderer renderer, int row, int column)
	            {
	                Component c = super.prepareRenderer(renderer, row, column);
	                JComponent jc = (JComponent)c;

	                if(Integer.parseInt(data.getValueAt(row, 4).toString()) == 1) {
	                	jc.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
	                }
	                
	                return c;
	            }
		};
		table.setShowHorizontalLines(false);
		table.removeColumn(table.getColumnModel().getColumn(4));

		scroll = new JScrollPane(table);
		
		panel.add(lblTitle, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		add(panel);
	}

	private void loadData() {
		orders = new Order().getAllByUser();
		setData();
	}
	
	public void setData() {
		data.setRowCount(0);
		
		System.out.println("orders size: " + orders.size());
		for (Order o : orders) {
			int ctr = 1;
			System.out.println("masuk " + o.getOrders().size());
			for (OrderDetail od : o.getOrders()) {
				Vector<Object> rowData = new Vector<>();
				rowData.add(ctr);
				rowData.add(od.getProduct().getName());
				rowData.add(od.getProduct().getPrice());
				rowData.add(od.getQuantity());
				
				if(ctr == o.getOrders().size()) {
					System.out.println(o.getOrders().size());
					rowData.add(1);
				}else {
					rowData.add(0);
				}
				data.addRow(rowData);
				ctr++;
			}
		}
	}

	private void addListener() {
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new OrderPage();
				dispose();
			}
		});
		
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AuthService.getInstance().logout();
				new LoginPage();
				dispose();
			}
		});
	}

}

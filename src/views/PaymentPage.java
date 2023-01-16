package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import models.order.Order;
import models.order.OrderDetail;
import template.payments.CashPayments;
import template.payments.CreditCardPayments;
import template.payments.EMoneyPayments;
import template.payments.Payments;

public class PaymentPage extends JFrame {
	private JPanel allPanel, centerPanel, listPanel, choosePanel, detailPanel, paymentPanel, totalPanel;
	private JLabel lblTitle, lblSubtotal, lblTax, lblTotal, lblSubtotalVal, lblTaxVal, lblTotalVal;
	private JButton btnPay;
	private JScrollPane scroll;
	private JComboBox jcb;
	private Order current;
	private int chosen = 0;

	public PaymentPage(Order current) {
		this.current = current;
		
		initialize();
		setVisible(true);
		setSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addListener();
	}

	private void initialize() {
		allPanel = new JPanel(new BorderLayout());
		allPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		centerPanel = new JPanel(new BorderLayout());
		listPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        listPanel.add(new JPanel(), gbc);
        paymentPanel = new JPanel(new GridLayout(2, 1));
        totalPanel = new JPanel(new GridLayout(3,2));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        detailPanel = new JPanel(new BorderLayout());
        
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        choosePanel = new JPanel(fl);
        choosePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        scroll = new JScrollPane(listPanel);
		
        lblTitle = new JLabel("Payment");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		lblSubtotal = new JLabel("Subtotal");
		lblTax = new JLabel("Tax");
		lblTotal = new JLabel("Total");
		lblSubtotalVal = new JLabel("0");
		lblTaxVal = new JLabel("0");
		lblTotalVal = new JLabel("0");
		
		btnPay = new JButton("Pay");
		
		String[] payments = {"--Choose--", "Cash", "Credit Card", "E-Money"};
		jcb = new JComboBox<>(payments);
		
		choosePanel.add(new JLabel("Payment Method"));
		choosePanel.add(jcb);
		
		totalPanel.add(lblSubtotal);
		totalPanel.add(lblSubtotalVal);
		totalPanel.add(lblTax);
		totalPanel.add(lblTaxVal);
		totalPanel.add(lblTotal);
		totalPanel.add(lblTotalVal);
		
		paymentPanel.add(choosePanel);
		
		detailPanel.add(scroll, BorderLayout.CENTER);
		detailPanel.add(paymentPanel, BorderLayout.SOUTH);
		
		centerPanel.add(detailPanel, BorderLayout.CENTER);
		centerPanel.add(totalPanel, BorderLayout.SOUTH);
		
		allPanel.add(lblTitle, BorderLayout.NORTH);
		allPanel.add(centerPanel, BorderLayout.CENTER);
		allPanel.add(btnPay, BorderLayout.SOUTH);
		
		add(allPanel);
		loadCart();
	}
	
	public void loadCart() {
		JPanel header = new JPanel(new GridLayout(1, 3));
		header.add(new JLabel("Name"));
		header.add(new JLabel("Price"));
		header.add(new JLabel("Qty"));
		header.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        listPanel.add(header, gbc, 0);
        
		for (OrderDetail orderDetail : current.getOrders()) {
			JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.add(new JLabel(orderDetail.getProduct().getName()));
            panel.add(new JLabel("Rp. " + Integer.toString(orderDetail.getProduct().getPrice())));
            panel.add(new JLabel(Integer.toString(orderDetail.getQuantity())));
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.gridwidth = GridBagConstraints.RELATIVE;
            gbc2.weightx = 1;
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            listPanel.add(panel, gbc);

		}
		
		lblSubtotalVal.setText(String.format("Rp. %.2f", current.calculateSubotal()));
		lblTaxVal.setText(String.format("Rp. %.2f", current.calculateTax()));
		lblTotalVal.setText(String.format("Rp. %.2f", current.calculateTotal()));

        revalidate();
        repaint();
	}

	private void addListener() {
		btnPay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jcb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();

			    Object item = e.getItem();
			    
			    if (e.getStateChange() == ItemEvent.SELECTED) {
			        if(!item.equals("--Choose--")) {
			        	Payments p = null;
			        	if(item.equals("Cash")) {
			        		p = new CashPayments();
			        	} else if(item.equals("Credit Card")) {
			        		p = new CreditCardPayments();
			        	} else {
			        		p = new EMoneyPayments();
			        	}
			        	current.setPayments(p);
			        	
			        	if(chosen == 1) {
			        		paymentPanel.remove(1);
			        	}
			        	paymentPanel.add(p.payments());
			        	chosen = 1;
			        	revalidate();
			        	repaint();
			        }
			    }
			}
		});
	}

}

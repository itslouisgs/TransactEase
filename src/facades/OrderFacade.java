package facades;

import java.util.Vector;

import models.order.Order;
import models.order.OrderDetail;

public class OrderFacade {
	private static OrderFacade instance;
	
	public static OrderFacade getInstance() {
		if(instance == null) {
			instance = new OrderFacade();
		}
		
		return instance;
	}
	
	public Vector<OrderDetail> getAllByOrder(String orderId) {
		return new OrderDetail().getAllByOrder(orderId);
	}
	
	public Vector<Order> getAllOrdersByUser() {
		return new Order().getAllByUser();
	}

}

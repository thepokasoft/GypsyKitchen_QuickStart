package in.gk.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class OrderItemList {
	
		@NotNull
		private List<OrderItem> items = new ArrayList<OrderItem>();

		public List<OrderItem> getOlis() {
			return items;
		}

		public void setOlis(List<OrderItem> olis) {
			this.items = olis;
		}
		
		

}

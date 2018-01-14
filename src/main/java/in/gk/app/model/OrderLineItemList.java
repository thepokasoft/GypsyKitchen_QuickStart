package in.gk.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderLineItemList {
	
		@NotNull
		private List<OrderLineItem> olis = new ArrayList<OrderLineItem>();

		public List<OrderLineItem> getOlis() {
			return olis;
		}

		public void setOlis(List<OrderLineItem> olis) {
			this.olis = olis;
		}
		
		

}

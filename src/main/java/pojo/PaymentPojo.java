package pojo;

import java.util.Date;
import java.util.Objects;

public class PaymentPojo {
	
	private int paymentId;
	private int offerId;
	private int userId;
	private int amountDue;
	private boolean paymentComplete;
//	private Date paymentDate;
	
	public PaymentPojo() {
		super();
	}
	
	public PaymentPojo(int paymentId, int offerId, int userId, int amountDue, boolean paymentComplete) {
		super();
		this.paymentId = paymentId;
		this.offerId = offerId;
		this.userId = userId;
		this.amountDue = amountDue;
		this.paymentComplete = paymentComplete;
//		this.paymentDate = paymentDate;
	}
	
}

package service;

import java.util.List;

import exception.ApplicationException;
import pojo.OfferPojo;
import pojo.PaymentPojo;

public interface PaymentService {

	public List<PaymentPojo> employeeViewAllPayment() throws ApplicationException;
	public PaymentPojo customerViewRemain(PaymentPojo paymentPojo) throws ApplicationException;
	public PaymentPojo weeklyPay(PaymentPojo paymentPojo) throws ApplicationException;
	void exitApplication();

}
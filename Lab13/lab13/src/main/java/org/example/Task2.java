package org.example;

public class Task2 {
    public static void main(String[] args) {
        PaymentFactory creditCardFactory = new CreditCardPaymentFactory();
        Payment creditCardPayment = creditCardFactory.createPayment();
        creditCardPayment.processPayment();
        PaymentFactory paypalFactory = new PayPalPaymentFactory();
        Payment paypalPayment = paypalFactory.createPayment();
        paypalPayment.processPayment();
    }
}
interface Payment{
    void processPayment();
}
class CreditCardPayment implements Payment{
    @Override
    public void processPayment(){
        System.out.println("Processing credit card payment...");
    }
}
class PayPalPayment implements Payment{
    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment...");
    }
}
interface PaymentFactory{
    Payment createPayment();
}
class CreditCardPaymentFactory implements PaymentFactory{
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
class PayPalPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new PayPalPayment();
    }
}
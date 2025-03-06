package People;

public interface Payable {
    double calculatePayment();
    boolean processPayment(double amount);
    String getPaymentDetails();
}

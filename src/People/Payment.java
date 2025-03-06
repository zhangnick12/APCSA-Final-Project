package People;

public class Payment implements Payable {
    private String paymentId;
    private double amount;
    private String paymentMethod;
    private boolean isProcessed;
    private String transactionDetails;

    public Payment(String paymentId, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isProcessed = false;
        this.transactionDetails = "";
    }

    @Override
    public double calculatePayment() {
        return amount;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount >= this.amount) {
            this.isProcessed = true;
            this.transactionDetails = "Payment of $" + String.format("%.2f", amount) +
                    " processed by " + paymentMethod +
                    " on " + java.time.LocalDate.now();
            return true;
        }
        return false;
    }

    @Override
    public String getPaymentDetails() {
        String details = "Payment ID: " + paymentId + "\n" +
                "Amount: $" + String.format("%.2f", amount) + "\n" +
                "Method: " + paymentMethod + "\n" +
                "Status: " + (isProcessed ? "Processed" : "Pending") + "\n";

        if (isProcessed && !transactionDetails.isEmpty()) {
            details += "Transaction: " + transactionDetails;
        }

        return details;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setTransactionDetails(String details) {
        this.transactionDetails = details;
    }
}

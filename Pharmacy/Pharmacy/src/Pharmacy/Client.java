package Pharmacy;

public class Client extends Human  {

    static int ID = 0;//Client counter
    private String Email;
    private String Password;
    private Order Order; //Order has list of products
    private PaymentCard PaymentCard;
    public static int getID() {
        return ID;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setOrder(Order order) {
        Order = order;
    }
    public Order getOrder() {
        return Order;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getPassword() {
        return Password;
    }
    public void setPaymentCard(PaymentCard paymentCard) {
        PaymentCard = paymentCard;
    }
    public PaymentCard getPaymentCard() {
        return PaymentCard;
    }


}

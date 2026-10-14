public class ShoppingBill {

    static double calculateElectronicsGST(double a, double b) {
        double amount = a + b;
        return amount + (amount * 0.18);
    }

    static double calculateFashionGST(double c, double d) {
        double amount = c + d;
        return amount + (amount * 0.13);
    }

    static double calculateFoodGST(double e) {
        return e + (e * 0.07);
    }

    static double totalBill(double a, double b, double c,
                            double d, double e) {
        return calculateElectronicsGST(a, b)
                + calculateFashionGST(c, d)
                + calculateFoodGST(e);
    }

    static double calculateDiscountedBill(
            double a, double b, double c, double d, double e) {

        double total = totalBill(a, b, c, d, e);
        double discount = total * 0.20;

        return total - discount;
    }

    public static void main(String[] args) {
        double a = 100;
        double b = 100;
        double c = 100;
        double d = 100;
        double e = 100;

        double total = totalBill(a, b, c, d, e);
        double discount = total * 0.20;
        double finalAmount =
                calculateDiscountedBill(a, b, c, d, e);

        System.out.printf("Total after GST = Rs. %.2f%n", total);
        System.out.printf("Discount = Rs. %.2f%n", discount);
        System.out.printf("Final payable amount = Rs. %.2f%n", finalAmount);
    }
}
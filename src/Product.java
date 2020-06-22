package application;

public class Product {

    private int quantity;
    private double length;
        public Product() {
            quantity = 0;
            length = 0;
        }
        public Product(double len, int q) {
            quantity = q;
            length = len;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public double getLength() {
            return length;
        }
        public void setLength(double len) {
            this.length = len;
        }
}

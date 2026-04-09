package codefinity.Task;

import codefinity.model.Customer;
import codefinity.model.Product;

import java.util.HashMap;
import java.util.Map;

public class OnlineStoreDatabaseImpl implements OnlineStoreDatabase {

    private Map<Integer, Product> products;
    private Map<Integer, Customer> customers;

    public OnlineStoreDatabaseImpl() {
        this.products = new HashMap<>();
        this.customers = new HashMap<>();
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }
    public Map<Integer, Customer> getCustomers() {
        return customers;
    }
    //NOTE: product vars:
    //    private int id
    //    private String name;
    //    private double price;
    //    private int quantity;
    //CX VARS:
    //    private int id;
    //    private String name;
    //    private String email;
    //    private String address;
    @Override
    public void addProduct(Product product) {
        Integer id = product.getId(); //converts int to Integer, probably dont need this step but just in case
        products.put(id, product);
    }

    @Override
    public void updateProduct(int productId, double newPrice, int newQuantity) {
        if(!products.containsKey(productId)) {
            System.out.println("product ID not found, nothing to be updated!");
            return;
        }
        Product product = products.get(productId); // getting product from the list
        product.setPrice(newPrice);                // updating price and qty
        product.setQuantity(newQuantity);
    }

    @Override
    public void removeProduct(int productId) {
        if(!products.containsKey(productId)) {
            System.out.println("Key not found!");
            return;
        }
        products.remove(productId);


    }

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);

    }

    @Override
    public void updateCustomer(int customerId, String newAddress) {
        if(!customers.containsKey(customerId)) {
            System.out.println("Customer not found! try again.");
            return;
        }

        Customer cx = customers.get(customerId);
        cx.setAddress(newAddress);
    }

    @Override
    public void removeCustomer(int customerId) {
        if(!customers.containsKey(customerId)) {
            System.out.println("Customer not found!");
            return;
        }
        customers.remove(customerId);
    }

    @Override
    public void placeOrder(int customerId, int productId, int quantity) {
        if (!customers.containsKey(customerId) || !products.containsKey(productId)) {
            System.out.println("Failed to place the order. Check the data.");
            return;
        }

        Product product = products.get(productId);
        if (product.getQuantity() - quantity >= 0) {
            product.setQuantity(product.getQuantity() - quantity);
        } else {
            System.out.println("Not enough in stock for this quantity.");
        }
        System.out.println("Order placed successfully!");

    }

    @Override
    public void displayAllProducts() {
        System.out.println("List of products:");
        for (Product product : products.values()) {
            System.out.println(product.getId() + ": " //prints in format: ID: name - $price
                    + product.getName() + " - $"
                    + product.getPrice());
        }
    }

    @Override
    public void displayAllCustomers() { //id name address
        System.out.println("List of customers:");
        for(Customer customer : customers.values()) { // ID: name - address
            System.out.println(customer.getId() + ": "
            + customer.getName() + " - "
            + customer.getAddress());
        }

    }
}

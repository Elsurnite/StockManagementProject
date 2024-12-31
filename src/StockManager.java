package src;

import java.util.List;

public class StockManager {
    private List<Product> products;

    public StockManager() {
        // Dosyadan ürünleri yükle
        products = FileHelper.readFromFile();
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public void removeProduct(String id) {
        products.removeIf(product -> product.getId().equals(id));
        saveProducts();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product searchProduct(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void saveProducts() {
        FileHelper.writeToFile(products);
    }
}

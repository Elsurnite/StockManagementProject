package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private static final String FILE_NAME = "products.txt";

    public static void writeToFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Dosyaya yazılamadı: " + e.getMessage());
        }
    }

    public static List<Product> readFromFile() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0];
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    products.add(new Product(id, name, quantity));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı, yeni bir liste oluşturuluyor.");
        } catch (IOException e) {
            System.err.println("Dosyadan okunamadı: " + e.getMessage());
        }

        return products;
    }
}

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UI {
    private StockManager stockManager;

    public UI() {
        // Türkçe karakterlerin doğru görüntülenmesi için yazı tipi ayarı
        UIManager.put("Button.font", new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 14));
        UIManager.put("Label.font", new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.font", new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 14));

        stockManager = new StockManager();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Stok Yönetim Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel oluştur ve düzenle
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 satır, 1 sütun, 10px boşluk

        // Butonları oluştur
        JButton addButton = new JButton("Ürün Ekle");
        JButton listButton = new JButton("Ürünleri Listele");
        JButton removeButton = new JButton("Ürün Sil");
        JButton searchButton = new JButton("Ürün Ara");
        JButton exitButton = new JButton("Çıkış");

        // Butonları panele ekle
        panel.add(addButton);
        panel.add(listButton);
        panel.add(removeButton);
        panel.add(searchButton);
        panel.add(exitButton);

        // Pencereye paneli ekle
        frame.add(panel);
        frame.setVisible(true);

        // Butonlara işlev ekle
        setupButtonActions(addButton, listButton, removeButton, searchButton, exitButton);
    }

    private void setupButtonActions(JButton addButton, JButton listButton, JButton removeButton, JButton searchButton, JButton exitButton) {
        // Ürün ekleme
        addButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Ürün ID:");
            String name = JOptionPane.showInputDialog("Ürün Adı:");
            String quantityStr = JOptionPane.showInputDialog("Ürün Miktarı:");
            try {
                int quantity = Integer.parseInt(quantityStr);
                stockManager.addProduct(new Product(id, name, quantity));
                JOptionPane.showMessageDialog(null, "Ürün başarıyla eklendi!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lütfen geçerli bir miktar girin!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ürünleri listeleme
        listButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Stoktaki Ürünler:\n");
            for (Product product : stockManager.getAllProducts()) {
                sb.append(product).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "Stokta ürün yok!");
        });

        // Ürün silme
        removeButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Silinecek Ürün ID:");
            Product product = stockManager.searchProduct(id);
            if (product != null) {
                stockManager.removeProduct(id);
                JOptionPane.showMessageDialog(null, "Ürün başarıyla silindi!");
            } else {
                JOptionPane.showMessageDialog(null, "Ürün bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ürün arama
        searchButton.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Aranacak Ürün ID:");
            Product product = stockManager.searchProduct(id);
            if (product != null) {
                JOptionPane.showMessageDialog(null, "Bulunan Ürün:\n" + product);
            } else {
                JOptionPane.showMessageDialog(null, "Ürün bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Çıkış
        exitButton.addActionListener(e -> System.exit(0));
    }
}

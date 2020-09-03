package com.internet.shop;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

public class Main {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product iphone = new Product("IPhone", 1500.0);
        Product samsung = new Product("Samsung", 1500.0);
        Product appleWatch = new Product("Apple Watch", 1000.0);
        Product asus = new Product("Asus", 5000.0);
        productService.create(iphone);
        productService.create(samsung);
        productService.create(appleWatch);
        productService.create(asus);
        Storage.products.forEach(System.out::println);
        Product iphoneX = new Product("IPhone X", 2000.0);
        iphoneX.setId(iphone.getId());
        productService.update(iphoneX);
        Storage.products.forEach(System.out::println);
        productService.delete(appleWatch.getId());
        Storage.products.forEach(System.out::println);
    }
}

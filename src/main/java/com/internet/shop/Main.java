package com.internet.shop;

import com.internet.shop.db.Storage;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;

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
        System.out.println("Products created");
        Storage.products.forEach(System.out::println);
        Product iphoneX = new Product("IPhone X", 2000.0);
        iphoneX.setId(iphone.getId());
        productService.update(iphoneX);
        System.out.println("IPhone id updated");
        Storage.products.forEach(System.out::println);
        productService.delete(appleWatch.getId());
        System.out.println("Apple Watch deleted");
        Storage.products.forEach(System.out::println);

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User andrew = new User("Andrew", "mart", "mart1234");
        User dana = new User("Dana", "fancy", "fancy22");
        userService.create(andrew);
        userService.create(dana);
        System.out.println("Users created");
        Storage.users.forEach(System.out::println);
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        ShoppingCart firstCart = new ShoppingCart(andrew.getId());
        ShoppingCart secondCart = new ShoppingCart(dana.getId());
        shoppingCartService.create(firstCart);
        shoppingCartService.create(secondCart);
        shoppingCartService.addProduct(firstCart, iphone);
        shoppingCartService.addProduct(firstCart, appleWatch);
        shoppingCartService.addProduct(secondCart, samsung);
        shoppingCartService.addProduct(secondCart, asus);
        System.out.println("Carts created");
        Storage.shoppingCarts.forEach(System.out::println);
        shoppingCartService.deleteProduct(firstCart, appleWatch);
        shoppingCartService.deleteProduct(secondCart, asus);
        System.out.println("Products deleted from carts");
        Storage.shoppingCarts.forEach(System.out::println);
        shoppingCartService.clear(secondCart);
        shoppingCartService.delete(secondCart.getId());
        System.out.println("Second cart cleared and deleted");
        Storage.shoppingCarts.forEach(System.out::println);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(firstCart);
        System.out.println("Completed orders");
        Storage.orders.forEach(System.out::println);
    }
}

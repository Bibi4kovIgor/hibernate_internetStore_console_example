package edu.lemon.data;

import edu.lemon.entity.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Set;

public interface Data {

    static List<Categories> categoriesData(){
        return List.of(
                Categories.builder()
                        .name("Electronics")
                        .description("Super electronics devices")
                        .build(),

                Categories.builder()
                        .name("Computers")
                        .description("Best computers for your life")
                        .build(),

                Categories.builder()
                        .name("Art")
                        .description("Art for Heart")
                        .build(),

                Categories.builder()
                        .name("Jets")
                        .description("Boeing and Antonov Jets")
                        .build(),

                Categories.builder()
                        .name("Cars")
                        .description("Sport ad classic cars")
                        .build(),

                Categories.builder()
                        .name("Books")
                        .description("Premium books")
                        .build()
        );
    }
    static List<Products> productsData(List<Categories> categories) {
        return List.of(
                Products.builder()
                        .name("Laptop")
                        .categories(Set.of(
                                categories.stream()
                                    .filter(category -> category.getName().equals("Electronics"))
                                    .findFirst()
                                    .orElseThrow(IllegalArgumentException::new),
                                categories.stream()
                                    .filter(category -> category.getName().equals("Computers"))
                                    .findFirst()
                                    .orElseThrow(IllegalArgumentException::new)))
                        .price(new BigDecimal("125000.11"))
                        .quantity(25)
                        .build(),

                Products.builder()
                        .name("Maserati")
                        .quantity(10)
                        .price(new BigDecimal("2500000"))
                        .categories(Set.of(categories.stream()
                                .filter(category -> category.getName().equals("Cars"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new)))
                        .build(),

                Products.builder()
                        .name("BMW")
                        .quantity(100)
                        .price(new BigDecimal("1500000"))
                        .categories(Set.of(categories.stream()
                                .filter(category -> category.getName().equals("Cars"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new)))
                        .build(),

                Products.builder()
                        .name("Private Jet")
                        .quantity(5)
                        .price(new BigDecimal("12500000"))
                        .categories(Set.of(categories.stream()
                                .filter(category -> category.getName().equals("Jets"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new)))
                        .build(),

                Products.builder()
                        .name("Martin Fowler. Refactoring")
                        .quantity(10)
                        .price(new BigDecimal("1250"))
                        .categories(Set.of(categories.stream()
                                .filter(category -> category.getName().equals("Books"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new)))
                        .build(),

                Products.builder()
                        .name("creativity kit")
                        .quantity(10)
                        .price(new BigDecimal("4500"))
                        .categories(Set.of(categories.stream()
                                .filter(category -> category.getName().equals("Art"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new)))
                        .build()
        );
    }

    static List<Documents> documentsData(){
        return List.of(
                Documents.builder()
                        .taxId(145268456L)
                        .passport("MG7785412")
                        .additionalInfo("Test case #1")
                        .build(),

                Documents.builder()
                        .taxId(787458784L)
                        .passport("MK11233445")
                        .additionalInfo("Test case #2")
                        .build(),

                Documents.builder()
                        .taxId(34214251435L)
                        .passport("DD445785425")
                        .additionalInfo("Test case #3")
                        .build(),

                Documents.builder()
                        .taxId(1234545662L)
                        .passport("QQ722415")
                        .additionalInfo("Test case #4")
                        .build()
        );
    }



    static List<Customers> customersData(List<Documents> documents){
        return List.of(
                Customers.builder()
                        .document(documents.stream()
                                .filter(document -> document.getTaxId().equals(787458784L))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .name("Ihor")
                        .build(),

                Customers.builder()
                        .document(documents.stream()
                                .filter(document -> document.getTaxId().equals(145268456L))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .name("John")
                        .build(),

                Customers.builder()
                        .document(documents.stream()
                                .filter(document -> document.getTaxId().equals(34214251435L))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .name("Steve")
                        .build()
        );
    }

    static List<PaymentMethods> paymentMethodsData(List<Customers> customers){
        return List.of(
                PaymentMethods.builder()
                        .credentials(Base64.getEncoder().encodeToString("some_credentials_1".getBytes()))
                        .name("Visa Privat")
                        .customer(customers.stream().filter(customer -> customer.getName().equals("Ihor"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                PaymentMethods.builder()
                        .credentials(Base64.getEncoder().encodeToString("some_credentials_2".getBytes()))
                        .name("MasterCard Universal")
                        .customer(customers.stream().filter(customer -> customer.getName().equals("Ihor"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                PaymentMethods.builder()
                        .credentials(Base64.getEncoder().encodeToString("some_credentials_2".getBytes()))
                        .name("MasterCard Gold")
                        .customer(customers.stream().filter(customer -> customer.getName().equals("John"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                PaymentMethods.builder()
                        .credentials(Base64.getEncoder().encodeToString("some_credentials_3".getBytes()))
                        .name("Maestro")
                        .customer(customers.stream().filter(customer -> customer.getName().equals("John"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                PaymentMethods.builder()
                        .credentials(Base64.getEncoder().encodeToString("some_credentials_4".getBytes()))
                        .name("PayPal")
                        .customer(customers.stream().filter(customer -> customer.getName().equals("Steve"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build()
        );
    }

    static List<Orders> ordersData(List<Customers> customers, List<Products> products) {
        return List.of(
                Orders.builder()
                        .customer(customers.stream().filter(customer -> customer.getName().equals("Ihor"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .orderDate(Instant.now())
                        .quantity(10)
                        .product(products.stream()
                                .filter(product -> product.getName().equals("Laptop"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                Orders.builder()
                        .customer(customers.stream().filter(customer -> customer.getName().equals("John"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .orderDate(Instant.now())
                        .quantity(1)
                        .product(products.stream()
                                .filter(product -> product.getName().equals("BMW"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                Orders.builder()
                        .customer(customers.stream().filter(customer -> customer.getName().equals("Steve"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .orderDate(Instant.now())
                        .quantity(1)
                        .product(products.stream()
                                .filter(product -> product.getName().equals("Private Jet"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build(),

                Orders.builder()
                        .customer(customers.stream().filter(customer -> customer.getName().equals("Ihor"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .orderDate(Instant.now())
                        .quantity(3)
                        .product(products.stream()
                                .filter(product -> product.getName().equals("Martin Fowler. Refactoring"))
                                .findFirst()
                                .orElseThrow(IllegalArgumentException::new))
                        .build()
        );
    }
}

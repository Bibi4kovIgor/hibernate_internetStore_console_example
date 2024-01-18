package edu.lemon;

import edu.lemon.data.Data;
import edu.lemon.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Transaction transaction = null;

        try(StandardServiceRegistry standardServiceRegistry =
                    new StandardServiceRegistryBuilder()
                            .configure()
                            .build();

            SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession()
        ){
            transaction = session.getTransaction();
            transaction.begin();

            Data.categoriesData().forEach(session::persist);
            Data.documentsData().forEach(session::persist);

            List<Categories> categories = session.createQuery("from Categories", Categories.class).list();
            Data.productsData(categories).forEach(session::persist);

            List<Documents> documents = session.createQuery("from Documents", Documents.class).list();
            Data.customersData(documents).forEach(session::persist);

            List<Customers> customers = session.createQuery("from Customers", Customers.class).list();
            Data.paymentMethodsData(customers).forEach(session::persist);

            List<Products> products = session.createQuery("from Products", Products.class).list();
            Data.ordersData(customers, products).forEach(session::persist);

            transaction.commit();

//            transaction.begin();
//
//            for (Orders orders : Data.ordersData()) {
//                session.persist(orders);
//            }
//
//            transaction.commit();

            transaction.begin();
            session.createQuery("from Customers", Customers.class)
                    .list().forEach(System.out::println);
/*
            session.createMutationQuery("UPDATE customers SET name='Test2' WHERE name='Test';").executeUpdate();
            session.createMutationQuery("INSERT INTO customers(name) VALUES('John');").executeUpdate();
*/
            session.createQuery("from Orders", Orders.class)
                    .list().forEach(System.out::println);

            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }

            System.err.println(e.getMessage());
        }
    }
}
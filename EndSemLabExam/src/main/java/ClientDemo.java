


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {

    public static void main(String[] args) {
        // Create Hibernate SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Insert records
        insertRecords(factory);

        // Apply criteria queries
        applyCriteriaQueries(factory);

        // Close factory
        factory.close();
    }

    public static void insertRecords(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create and save Customer objects
        Customer c1 = new Customer();
        c1.setName("Alice");
        c1.setEmail("alice@example.com");
        c1.setAge(25);
        c1.setLocation("New York");

        Customer c2 = new Customer();
        c2.setName("Bob");
        c2.setEmail("bob@example.com");
        c2.setAge(30);
        c2.setLocation("Los Angeles");

        Customer c3 = new Customer();
        c3.setName("Charlie");
        c3.setEmail("charlie@example.com");
        c3.setAge(35);
        c3.setLocation("Chicago");

        session.save(c1);
        session.save(c2);
        session.save(c3);

        transaction.commit();
        session.close();

        System.out.println("Records inserted successfully.");
    }

    public static void applyCriteriaQueries(SessionFactory factory) {
        Session session = factory.openSession();

        System.out.println("Customers with age greater than 25:");
        List<Customer> list1 = session.createCriteria(Customer.class)
                .add(Restrictions.gt("age", 25))
                .list();
        list1.forEach(customer -> System.out.println(customer.getName()));

        System.out.println("Customers with name like 'A%':");
        List<Customer> list2 = session.createCriteria(Customer.class)
                .add(Restrictions.like("name", "A%"))
                .list();
        list2.forEach(customer -> System.out.println(customer.getName()));

        System.out.println("Customers between ages 20 and 30:");
        List<Customer> list3 = session.createCriteria(Customer.class)
                .add(Restrictions.between("age", 20, 30))
                .list();
        list3.forEach(customer -> System.out.println(customer.getName()));

        session.close();
    }
}

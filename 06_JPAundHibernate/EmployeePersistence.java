package ch.bbw.pr.employee.persistence;

import ch.bbw.pr.employee.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * EmployeePersistence
 *
 * @author Peter Rutschmann
 * @version 17.09.2021
 */
public class EmployeePersistence {
   private static SessionFactory factory;
   private static Session sessionObj;

   public EmployeePersistence(){
      //Setup SessionFactory
      Configuration config = new Configuration().configure();
      config.addAnnotatedClass(ch.bbw.pr.employee.model.Employee.class);
      //config.addResource("Employee.hbm.xml");
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
      factory = config.buildSessionFactory(serviceRegistry);
   }

   public List<Employee> getAllEmployees(){
      List<Employee> employees = null;
      try{
         //Transaction
         sessionObj = factory.openSession();
         Transaction tx = sessionObj.beginTransaction();
         employees = (List<Employee>) sessionObj.createQuery("from Employee").getResultList();
         tx.commit();

      } catch(HibernateException e) {
         if(null != sessionObj.getTransaction()) {
            System.err.println("Transaction \"From Employee\" is Being Rolled Back.\n");
            sessionObj.getTransaction().rollback();
            e.printStackTrace();
         }
      } catch(Exception e) {
         if(null != sessionObj.getTransaction()) {
            System.err.println("Transaction \"From Employee\" is Being Rolled Back.\n");
            sessionObj.getTransaction().rollback();
            e.printStackTrace();
         }
      } finally {
         if(sessionObj != null) {
            sessionObj.close();
         }
      }
      return employees;
   }
}

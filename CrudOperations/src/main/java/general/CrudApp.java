package general;

import dao.UserEntity;
import org.hibernate.Session;
import utils.HibernateSessionFactory;

public class CrudApp {
    public static void main(String[] args) {
        System.out.println("Crud Operations");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("broooo");
        userEntity.setPassword("1234");
        userEntity.setPhone("92541256");
        userEntity.setFirstName("Pol");
        userEntity.setLastName("lololo");
        userEntity.setRole("user");

        session.save(userEntity);
        session.getTransaction().commit();

        session.close();


    }
}

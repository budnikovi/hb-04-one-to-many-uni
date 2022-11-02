package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try {

            // create the objects

//            Instructor tempInstructor =
//                    new Instructor("Chad", "Darby", "darby@gmail.com");
//
//            InstructorDetail tempInstructorDetail =
//                    new InstructorDetail("http://www.youtube.com",
//                            "google.com");

            Instructor tempInstructor =
                    new Instructor("Madhu", "Patel", "madhu@gmail.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://www.youtube.com",
                            "guitar");
            Instructor tempInstructor1 =
                    new Instructor("John", "Dave", "johny@gmail.com");

            InstructorDetail tempInstructorDetail1 =
                    new InstructorDetail("http://www.youtube.com",
                            "programming");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            tempInstructor1.setInstructorDetail(tempInstructorDetail1);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //

            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);
            session.save(tempInstructor1);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}

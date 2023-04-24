package com.demoss;

import com.entitiess.Instructor;
import com.entitiess.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToOneDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {


            // create the objects
            Instructor instructor = new Instructor("Maria", "Miron", "mary@gmail.com");

            InstructorDetail instructorDetail = new InstructorDetail("www.youtube/games.com", "guitar");

            // relate the objects

            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();
            // save the instructor
            // also save the instructor detail because of the cascade
          // session.save(instructor);

           //get the instructor
          // Instructor tempInstructor = session.get(Instructor.class,2);

            //delete the instructor
            //session.delete(tempInstructor);

            // Bi-directional
            // get the instructor details
           InstructorDetail instructorDetail1 = session.get(InstructorDetail.class,4);
            // print instructor details
           // System.out.println(instructorDetail1);

            // print the instructor
          //  System.out.println(instructorDetail1.getInstructor());
            //Bi-directional delete

            //break bi-directional link
            instructorDetail1.getInstructor().setInstructorDetail(null);


            session.delete(instructorDetail1);

            session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //handle connection leak
            session.close();
        }


    }



}

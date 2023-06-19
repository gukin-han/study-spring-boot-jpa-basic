package org.example;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 데이터베이스 커넥션
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");

            em.detach(member);
            System.out.println("==== after detaching member ====");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
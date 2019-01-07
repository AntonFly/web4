package com.airhacks;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class DotsService {

    @PersistenceContext(unitName = "web4")
    EntityManager em;

    public void saveShot(Dots dot){
        em.persist(dot);
    }

     public  List<Dots> getAllShots(String login){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+" "+login+" "+em);
        return  em.createQuery("from Dots d where d.login ="+login).getResultList();
    }

    public void drop(String login){
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+" "+login+" "+em);
        em.createQuery("delete from Dots d where d.login like \'"+login+"\'").executeUpdate();

    }
}

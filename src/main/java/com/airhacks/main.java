package com.airhacks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) throws JsonProcessingException {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("web4");
        EntityManager entityManager=managerFactory.createEntityManager();
        System.out.println(entityManager);
        LoginPage lp= new LoginPage();
        System.out.println(lp);
        Users user = entityManager.find(Users.class,"username");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);
        System.out.println(jsonString);
    }
}

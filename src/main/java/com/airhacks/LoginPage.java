package com.airhacks;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Local
@Stateless
public class LoginPage {

  @PersistenceContext(unitName = "web4")
   EntityManager em;

  public Users getUser(String login){
    return em.find(Users.class,login);
  }
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void addUser(Users user)
  {
    this.em.persist(user);
  }

  public void detatch(Users users){
    this.em.detach(users);
  }

}

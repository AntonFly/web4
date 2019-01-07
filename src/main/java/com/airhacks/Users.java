package com.airhacks;

import javax.persistence.*;

@Entity(name = "users")
@NamedQueries({
  @NamedQuery(name = "Users.getAll", query = "SELECT u from users u"),
  @NamedQuery(name = "Users.getByLogin", query = "select u from users u" +
    " where u.username= :login")
})


public class Users {
  @Id
  private String username;
  @Column
  private String password;
  @Column
  private String firstname;
  @Column
  private String lastname;

  public Users() {
  }

  public String getLogin() {
    return username;
  }

  public void setLogin(String login) {
    this.username = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

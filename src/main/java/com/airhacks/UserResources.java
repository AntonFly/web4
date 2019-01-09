package com.airhacks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.sun.corba.se.impl.orbutil.ObjectWriter;
import com.sun.deploy.net.HttpResponse;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.OutputStream;

@Stateful
@Path("/user")
//@PersistenceContext(unitName = "web4")
public class UserResources {

  @PersistenceContext(unitName = "web4")
  EntityManager em;

  @Context
  UriInfo uriInfo;

  @Inject
  LoginPage loginPage;

  @GET
  public  String hello(){
      return "<H2 style=\"color : red\">Hello EJB</H2>"+loginPage.getUser("username");

  }

  @POST
  @Path("/signIn")
  public Response fingByLogin(@FormParam("username") final String login, @FormParam("password") String password ) throws JsonProcessingException {
    Users user =this.loginPage.getUser(login);
    if (user == null)
//      return Response.ok().status(400).build();
        return Response.ok("{\"msg\":\"Неверный пароль или логин\"}").build();
    HashFunction hf = Hashing.md5();
    HashCode hc = hf.newHasher()
      .putString(password, Charsets.UTF_8)
      .hash();
    if( user.getPassword().equals(hc.toString())){
        ObjectMapper mapper = new ObjectMapper();
        loginPage.detatch(user);
        user.setPassword(null);
        String jsonString = mapper.writeValueAsString(user);
      return Response.ok(jsonString).build();
    }
    else
    {
        return Response.ok("{\"msg\":\"Неверный пароль или логин\"}").build();
//Response.ResponseBuilder response = Response.ok();
//      response.status(400);
//      return response.build();
    }

  }

  @POST
  @Path("/signUp")
  public Response signUp(@FormParam("username") final String username,
                         @FormParam("password") String password,
                         @FormParam("firstname") String firstname,
                         @FormParam("lastname") String lastname){
      try {
      System.out.println("sfdsfAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdsfsdfsdfsdffdsfsdv!!!!!!!"+username+" "+password+" "+firstname+" ");
      Users user =new Users();
      user.setUsername(username);
      HashFunction hf = Hashing.md5();
      HashCode hc = hf.newHasher()
        .putString(password, Charsets.UTF_8)
        .hash();
      user.setPassword(hc.toString());
      user.setFirstname(firstname);
      user.setLastname(lastname);
//      em.persist(user);
      this.loginPage.addUser(user);
      return   Response.ok().status(200).build();
      }catch (Exception e){
          e.printStackTrace();
          Response.ResponseBuilder response = Response.ok();
          response.status(401);
          return response.build();
      }
  }

    @GET
    @Path("/try{login}")
    public  Response tryLogin(@PathParam(value = "login") String login){
        Users user =this.loginPage.getUser(login);
        System.out.println(login);
        if (user == null)
            return Response.ok("{\"msg\":\"true\"}").build();
        return Response.ok("{\"msg\":\"false\"}").build();
    }

}

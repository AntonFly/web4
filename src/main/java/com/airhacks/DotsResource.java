package com.airhacks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless

@Path("/dot")
public class DotsResource {

    public DotsResource() {}

    @Inject
    private DotsService service;



    @GET
    public  String hello(){
        return "<H2 style=\"color : red\">Hello Dot</H2>";
    }

    @POST
    @Path("/check")
    public Response check(@FormParam("x") float x,
                      @FormParam("y") float y ,
                      @FormParam("r") float r,
                          @FormParam("login") String login
                     ){
            Dots dot = new Dots(x, y, r);
            if    ((y >= 0 && x >= 0 && x <= r  && y <= r/2) ||     //rectangle
                        (x >= 0 && y <= 0 && y >= 2*x - r ) ||      //triangle
                        (y >= 0 && x <= 0 && x * x + y * y < r * r ))
            dot.setHit(true);
            else
                dot.setHit(false);
            dot.setLogin(login);
            service.saveShot(dot);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(dot);

            } catch (JsonProcessingException e) {
                return Response.ok(e.getStackTrace()).build();
            }

            return Response.ok(jsonString).build();


//            resp.sendRedirect(UserResources.APP_NAME + "main.html");
    }

    @GET
    @Path(value = "/get{login}")
    public Response  getShots(@PathParam(value = "login") String login)  {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString((List<Dots>) service.getAllShots(login));
        } catch (JsonProcessingException e) {
            return Response.ok(e.getStackTrace()).build();
        }
        return Response.ok(jsonString).build();

    }

    @GET
    @Path(value = "/drop{login}")
    public Response drop(@PathParam("login") String login){
            service.drop(login);
        return Response.ok("dropped").build();
    }
}

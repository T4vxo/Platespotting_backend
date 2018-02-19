/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nu.te4.services;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import nu.te4.beans.PlateBean;


@Path("/")
public class PlateService {
    
    @EJB
    PlateBean plateBean;
    
    @GET
    @Path("user/{user_id}/plate/max")
    public Response userMaxPlate(@PathParam("user_id") int user_id){ 
        return Response.ok(plateBean.getHighestPlate(user_id)).build();
    }
    
    @POST
    @Path("user/{user_id}/plate/{plate_id}")
    public Response addPlate(@PathParam("plate_id") int plate_id, @PathParam("user_id") int user_id){  
        return Response.ok(plateBean.addPlate(user_id, plate_id)).build();
    }
    
    @DELETE
    @Path("user/{user_id}/plate/{plate_id}")
     public Response deletePlate(@PathParam("plate_id") int plate_id, @PathParam("user_id") int user_id){
        return Response.ok(plateBean.removePlate(user_id, plate_id)).build();
    }
    @DELETE
    @Path("user/{user_id}/plate/max")
     public Response deleteMax(@PathParam("user_id") int user_id){
        return Response.ok(plateBean.removeMaxPlate(user_id)).build();
    }
}

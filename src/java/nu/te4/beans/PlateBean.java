/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.ejb.Stateless;
import nu.te4.support.ConnectionFactory;

@Stateless
public class PlateBean {
    
    public String database_server;

    public PlateBean() {
       database_server = "plateapp_server";
    }

    public void setDatabase_server(String database_server) {
        this.database_server = database_server;
    }
    
    public int getHighestPlate(int user_id){
        try {
            Connection connection = ConnectionFactory.make(database_server);
            PreparedStatement stmt = connection.prepareStatement("SELECT MAX(plate_nr) as max FROM plate WHERE user_id = ?");
            stmt.setInt(1, user_id);
            ResultSet data = stmt.executeQuery();
            data.next();
            int max = data.getInt("max");
            connection.close();
            return max; 
        } catch (Exception ex) {
            System.out.println("Error (getHighestPlate): "+ex.getMessage());
        }
        return -1; //error
    }

    public int addPlate(int user_id, int plate_id) {
        try {
            Connection connection = ConnectionFactory.make(database_server);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO plate VALUES (?, ?);");
            stmt.setInt(1, plate_id);  
            stmt.setInt(2, user_id);  
            stmt.executeUpdate();
            connection.close();
            return 201;
        } catch (Exception e) {
            System.out.println("Error (addPlate): "+e.getMessage());
        }
        return 500;
        
    }
    
    public int removePlate(int user_id, int plate_id){
        if (plate_id == -1){
            plate_id = getHighestPlate(user_id);            
            if(plate_id == -1){
                return 400; 
            }
        }
        try {
            Connection connection = ConnectionFactory.make(database_server);
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM plate WHERE plate_nr = ? AND user_id = ?");
            stmt.setInt(1, plate_id);  
            stmt.setInt(2, user_id);  
            stmt.executeUpdate();
            connection.close();
            return 202;
        } catch (Exception e) {
            return 500;
        }
            
    }
    
    public int removeMaxPlate(int user_id){
        int plate_id = getHighestPlate(user_id);
        if(plate_id != -1){
            return removePlate(user_id,plate_id);
        }
        return 500;
    }
    
}

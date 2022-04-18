/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ram
 */
public class UserDAOImpl  implements UserDAO{
    
    private Connection conn;
    
    public UserDAOImpl(Connection conn) {
        super();
        this.conn = conn;
    }
    
    public boolean userRegister(User us) {
        boolean f=false;
        
        try{
            String sql="insert into user(name,email,password,contact) value(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, us.getName());
            ps.setString(2, us.getEmail());
             ps.setString(3, us.getPassword());
              ps.setString(4, us.getContact());
              
              
           int i = ps.executeUpdate();  
              if(i==1)
              {
                  f=true;
              }
             
        }catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    public User login(String email,String password){

        User us= null;
        try{
            String sql="select * from user where email=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                us=new User();
                us.setId(rs.getInt(1));
                us.setName(rs.getString(2));
                us.setEmail(rs.getString(3));
                us.setPassword(rs.getString(4));
                us.setContact(rs.getString(5));
                us.setAddress(rs.getString(6));
                us.setLandmark(rs.getString(7));
                us.setCity(rs.getString(8));
                us.setState(rs.getString(9));
                us.setPincode(rs.getString(10));
            }
            
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return us;
     
    }
     
}

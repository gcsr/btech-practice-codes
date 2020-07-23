package com.util;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author sabari
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginProcessor {
    public static boolean getUserDetails(String userid, String password) {
        PreparedStatement pst = null;
        Connection conn = null;
        String sql = "";
        boolean available = false;
        try {
            conn = (Connection) DbConnector.getConnection();
            sql = "select * from ownr where user_id= '" + userid + "' and password= '" + password+"'";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                available = true;
            } else {
                available = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return available;
    }
    public static boolean getUserDetailsClient(String userid, String password) {
        PreparedStatement pst = null;
        Connection conn = null;
        String sql = "";
        boolean available = false;
        try {
            conn = (Connection) DbConnector.getConnection();
            sql = "select * from users where user_id= '" + userid + "' and password= '" + password+"'";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                available = true;
            } else {
                available = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return available;
    }
    public static void main(String arg[]){
        LoginProcessor.getUserDetailsClient("admin","admin");
    }
}

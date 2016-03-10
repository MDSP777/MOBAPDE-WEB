package model;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by MSI LEOPARD on 3/7/2016.
 */
public class MySQLSSHConnector {
    private int localPort;
    private String remoteHost;
    private String host;
    private int remotePort;
    private String user;
    private String password;
    private String dbuserName;
    private String dbpassword;
    private String dbName;
    private String url = "jdbc:mysql://localhost:"+localPort+"/" + dbName;
    private String driverName="com.mysql.jdbc.Driver";
    Connection conn = null;
    Session session = null;
    private static MySQLSSHConnector instance = null;

    private MySQLSSHConnector(int localPort, String remoteHost, String host, int remotePort, String user,
                              String password, String dbuserName, String dbpassword, String dbName, String url,
                              String driverName) {
        this.localPort = localPort;
        this.remoteHost = remoteHost;
        this.host = host;
        this.remotePort = remotePort;
        this.user = user;
        this.password = password;
        this.dbuserName = dbuserName;
        this.dbpassword = dbpassword;
        this.dbName = dbName;
        this.url = url;
        this.driverName = driverName;
    }

    public Connection getConnection() {
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            int assigned_port = session.setPortForwardingL(localPort, remoteHost, remotePort);
            
            Class.forName(driverName).newInstance();
            return DriverManager.getConnection(url, dbuserName, dbpassword);

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MySQLSSHConnector getInstance(){
        if(instance!=null){
            return instance;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            instance = new MySQLSSHConnector(
                    5656,
                    "ec2-54-254-231-42.ap-southeast-1.compute.amazonaws.com",
                    "ec2-54-254-231-42.ap-southeast-1.compute.amazonaws.com",
                    3306,
                    "ec2-user",
                    "gioantonvelez",
                    "pedromax",
                    "marcsanpedro",
                    "/pedrodb",
                    "jdbc:mysql://localhost:5656",
                    "com.mysql.jdbc.Driver");

            return instance;
        }
    }

}

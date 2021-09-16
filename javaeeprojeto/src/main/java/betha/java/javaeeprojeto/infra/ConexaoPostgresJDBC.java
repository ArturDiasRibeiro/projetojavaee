
package betha.java.javaeeprojeto.infra;

//Coded by: Artur Dias

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoPostgresJDBC implements ConexaoJDBC{
        private Connection connection = null;
        
    public ConexaoPostgresJDBC() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        
        Properties properties = new Properties();
        properties.put("user", "postgres");
        properties.put("password", "123");
        
        this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/javaee?ApplicationName=javaeeprojeto", properties);
        this.connection.setAutoCommit(false);
                
    }

    @Override
    public Connection getConnection() {
        return this.connection;    
    }

    @Override
    public void close() {
        if(this.connection!=null){
            try{
                this.connection.close();
                
            }catch(SQLException ex){
                Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void commit() throws SQLException {
        this.connection.commit();
        this.close();
    }

    @Override
    public void rollback() {
        
        if(this.connection!=null){
            try{
                this.connection.close();
                
            }catch(SQLException ex){
                Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



}

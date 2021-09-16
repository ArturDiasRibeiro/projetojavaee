
package betha.java.javaeeprojeto.infra;

//Coded by: Artur Dias

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoJDBC {

    public Connection getConnection();
    
    public void close();
    
    public void commit() throws SQLException;
    
    public void rollback();
}

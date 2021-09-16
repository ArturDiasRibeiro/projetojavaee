package betha.java.javaeeprojeto.domain;

//Coded by: Artur Dias
import betha.java.javaeeprojeto.infra.ConexaoJDBC;
import betha.java.javaeeprojeto.infra.ConexaoPostgresJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamadoDAO {

    private final ConexaoJDBC conexao;

    public ChamadoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoPostgresJDBC();
    }

    public Integer inserir(Chamado chamado) throws SQLException, ClassNotFoundException {
        Integer id = null;
        String sqlQuery = "INSERT INTO chamado (assunto, status, mensagem) VALUES (?, ?, ?) RETURNING id";

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, chamado.getAssunto());
            stmt.setString(1, chamado.getStatus().toString());
            stmt.setString(1, chamado.getMensagem());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return id;
    }

    public Integer alterar(Chamado chamado) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE chamado SET assunto = ?, status = ?, mensagem = ? WHERE id = ?";
        Integer linhasAfetadas = 0;

        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, chamado.getAssunto());
            stmt.setString(2, chamado.getStatus().toString());
            stmt.setString(3, chamado.getMensagem());
            stmt.setInt(4, chamado.getId());

            linhasAfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return linhasAfetadas;
    }

}

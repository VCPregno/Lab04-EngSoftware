import java.sql.*;

public class Login {
    public static boolean authenticate(String nome, String senha) {
        String query = "SELECT * FROM alunos WHERE nome = ? AND senha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o aluno for encontrado

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean autenticar(String usuario, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
    }
}
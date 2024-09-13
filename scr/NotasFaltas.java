import java.sql.*;

public class NotasFaltas {
    public static void mostrarNotasFaltas(int alunoId) {
        String query = "SELECT m.nome, nf.nota, nf.faltas " +
                       "FROM notas_faltas nf " +
                       "JOIN materias m ON nf.materia_id = m.materia_id " +
                       "WHERE nf.aluno_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String materia = rs.getString("nome");
                double nota = rs.getDouble("nota");
                int faltas = rs.getInt("faltas");
                System.out.println("Matéria: " + materia + ", Nota: " + nota + ", Faltas: " + faltas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
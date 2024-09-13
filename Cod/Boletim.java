import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/imt_ti";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

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
}

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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (Login.authenticate(nome, senha)) {
            System.out.println("Login bem-sucedido!");
            // Aqui você pode obter o alunoId do banco de dados ou passar como argumento
            int alunoId = 1; // Substitua pelo id real após login
            NotasFaltas.mostrarNotasFaltas(alunoId);
        } else {
            System.out.println("Nome ou senha inválidos.");
        }

        scanner.close();
    }
}
import java.util.Scanner;

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
import javax.swing.*;
import java.awt.event.*;

public class TelaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JLabel rotuloMensagem;

    public TelaLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        getContentPane().add(painel);
        posicionarComponentes(painel);

        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (Login.autenticar(usuario, senha)) {
                    new TelaPrincipal().setVisible(true);
                    dispose(); // Fecha a janela de login
                } else {
                    rotuloMensagem.setText("Credenciais inválidas!");
                }
            }
        });
    }

    private void posicionarComponentes(JPanel painel) {
        painel.setLayout(null);

        JLabel rotuloUsuario = new JLabel("Usuário:");
        rotuloUsuario.setBounds(10, 20, 80, 25);
        painel.add(rotuloUsuario);

        campoUsuario = new JTextField(20);
        campoUsuario.setBounds(100, 20, 165, 25);
        painel.add(campoUsuario);

        JLabel rotuloSenha = new JLabel("Senha:");
        rotuloSenha.setBounds(10, 50, 80, 25);
        painel.add(rotuloSenha);

        campoSenha = new JPasswordField(20);
        campoSenha.setBounds(100, 50, 165, 25);
        painel.add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(10, 80, 80, 25);
        painel.add(botaoEntrar);

        rotuloMensagem = new JLabel("");
        rotuloMensagem.setBounds(10, 110, 250, 25);
        painel.add(rotuloMensagem);
    }
}

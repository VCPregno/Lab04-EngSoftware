import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class TelaPrincipal extends JFrame {
    private JTable tabela;
    private JButton botaoSair;

    public TelaPrincipal() {
        setTitle("Notas e Faltas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        getContentPane().add(painel);

        tabela = new JTable();
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        botaoSair = new JButton("Sair");
        painel.add(botaoSair, BorderLayout.SOUTH);

        carregarDados();
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLogin().setVisible(true);
                dispose();
            }
        });
    }

    private void carregarDados() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM notas_faltas")) {

            tabela.setModel(construirModeloTabela(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DefaultTableModel construirModeloTabela(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int quantidadeColunas = metaData.getColumnCount();
        Vector<String> nomesColunas = new Vector<>();
        for (int i = 1; i <= quantidadeColunas; i++) {
            nomesColunas.add(metaData.getColumnName(i));
        }
        Vector<Vector<Object>> dados = new Vector<>();
        while (rs.next()) {
            Vector<Object> linha = new Vector<>();
            for (int i = 1; i <= quantidadeColunas; i++) {
                linha.add(rs.getObject(i));
            }
            dados.add(linha);
        }
        return new DefaultTableModel(dados, nomesColunas);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}

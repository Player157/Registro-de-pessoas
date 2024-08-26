import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class tela_formulario {
    private JTextField nome;
    private JTextField profissa;
    private JRadioButton masculino;
    private JRadioButton Naobinario;
    private JRadioButton Feminino;
    private JTextField CPF;
    private JTextField nacimento;
    private JComboBox<String> comboBox1;
    private JRadioButton prefiroNaoDeclararRadioButton;
    public JPanel painel_form;
    private JButton enviarButton;
    private JLabel result1;
    private JLabel result2;
    private JLabel result3;
    private JLabel result4;
    private JLabel result5;
    private JLabel vagas;

    public tela_formulario() {
        // Adiciona opções ao ComboBox para estado civil
        comboBox1.addItem("Solteiro(a)");
        comboBox1.addItem("Casado(a)");
        comboBox1.addItem("Divorciado(a)");
        comboBox1.addItem("Viúvo(a)");

        // Agrupa os botões de rádio para o sexo
        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(masculino);
        sexoGroup.add(Feminino);
        sexoGroup.add(Naobinario);
        sexoGroup.add(prefiroNaoDeclararRadioButton);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O nome não pode ser deixado em branco.");
                    return;
                }

                if (!isCPFValid(CPF.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "CPF inválido. Por favor, insira um CPF no formato 000.000.000-00.");
                    return;
                }

                LocalDate dataNascimento;
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataNascimento = LocalDate.parse(nacimento.getText().trim(), formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Use o formato dd/MM/yyyy.");
                    return;
                }

                int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

                String profissao = profissa.getText().trim();
                if (profissao.isEmpty()) {
                    profissao = "Desempregado(a)";
                }

                String sexo = "Não declarado";
                if (masculino.isSelected()) {
                    sexo = "Masculino";
                } else if (Feminino.isSelected()) {
                    sexo = "Feminino";
                } else if (Naobinario.isSelected()) {
                    sexo = "Não binário";
                } else if (prefiroNaoDeclararRadioButton.isSelected()) {
                    sexo = "Prefiro não declarar";
                }

                String estadoCivil = (String) comboBox1.getSelectedItem();

                result1.setText("Nome: " + nome.getText().trim());
                result2.setText("Idade: " + idade + " anos");
                result3.setText("Sexo: " + sexo);
                result4.setText("Estado Civil: " + estadoCivil);
                result5.setText("Profissão: " + profissao);

                // Verifica se a profissão é "Engenheiro" ou "Analista de Sistemas"
                if (profissao.equalsIgnoreCase("Engenheiro") || profissao.equalsIgnoreCase("Analista de Sistemas")) {
                    vagas.setText("Vagas disponíveis na área.");
                } else {
                    vagas.setText("");
                }
            }
        });
    }

    private boolean isCPFValid(String cpf) {
        String cpfPattern = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return Pattern.matches(cpfPattern, cpf);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Pessoa");
        frame.setContentPane(new tela_formulario().painel_form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

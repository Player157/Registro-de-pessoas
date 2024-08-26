import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CALCULADORA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela_formulario tela = new tela_formulario();
        frame.setContentPane(new tela_formulario(). painel_form);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
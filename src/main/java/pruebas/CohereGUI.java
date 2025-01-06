package pruebas;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CohereGUI {
    private JFrame frame;
    private JTextField inputTextField;
    private JButton processButton;

    public CohereGUI() {
        // Crear la ventana principal
        frame = new JFrame("Cohere Emotion Handler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        // Campo de texto para ingresar el texto
        JLabel inputLabel = new JLabel("Ingresa tu texto:");
        inputLabel.setBounds(20, 20, 150, 20);
        frame.add(inputLabel);

        inputTextField = new JTextField();
        inputTextField.setBounds(20, 50, 340, 30);
        frame.add(inputTextField);

        // Botón para procesar
        processButton = new JButton("Procesar");
        processButton.setBounds(140, 100, 100, 30);
        frame.add(processButton);

        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método para agregar un ActionListener al botón
    public void addProcessButtonListener(ActionListener listener) {
        processButton.addActionListener(listener);
    }

    // Método para obtener el texto ingresado por el usuario
    public String getInputText() {
        return inputTextField.getText();
    }

    // Método para mostrar el resultado en un cuadro de diálogo
    public void showResult(String result) {
        JOptionPane.showMessageDialog(frame, result, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }
}

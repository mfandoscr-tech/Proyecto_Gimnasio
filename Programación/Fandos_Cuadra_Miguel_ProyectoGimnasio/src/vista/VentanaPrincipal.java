package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private JPanel panel;
    private JButton btnUsuarios;
    private JButton btnSalas;
    private JButton btnMaquinaria;
    private JButton btnActividades;

    public VentanaPrincipal() {
        setTitle("Gestión Gimnasio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();

        btnUsuarios = new JButton("Usuarios");
        btnSalas = new JButton("Salas");
        btnMaquinaria = new JButton("Maquinaria");
        btnActividades = new JButton("Actividades");

        panel.add(btnUsuarios);
        panel.add(btnSalas);
        panel.add(btnMaquinaria);
        panel.add(btnActividades);

        add(panel, BorderLayout.CENTER);

        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaUsuarios();
            }
        });

        setVisible(true);
    }
}
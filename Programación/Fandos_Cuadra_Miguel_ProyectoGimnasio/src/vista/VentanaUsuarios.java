package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import modelo.Usuario;

public class VentanaUsuarios extends JFrame {

    private JPanel panel;

    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblEmail;
    private JLabel lblTelefono;

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JTextField txtTelefono;

    private JButton btnGuardar;

    public VentanaUsuarios() {
        setTitle("Gestión de Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        lblNombre = new JLabel("Nombre:");
        lblApellidos = new JLabel("Apellidos:");
        lblEmail = new JLabel("Email:");
        lblTelefono = new JLabel("Teléfono:");

        txtNombre = new JTextField();
        txtApellidos = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();

        btnGuardar = new JButton("Guardar usuario");

        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblApellidos);
        panel.add(txtApellidos);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblTelefono);
        panel.add(txtTelefono);
        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });

        setVisible(true);
    }

    private void guardarUsuario() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        } else {
            Usuario usuario = new Usuario(nombre, apellidos, email, telefono);
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            boolean insertado = usuarioDAO.insertarUsuario(usuario);

            if (insertado) {
                JOptionPane.showMessageDialog(this, "Usuario guardado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el usuario.");
            }
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
    }
}
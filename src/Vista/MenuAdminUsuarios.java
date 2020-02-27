package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Controlador.Controlador;
import Modelo.Modelo;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class MenuAdminUsuarios extends JFrame {
	private Controlador controlador;
	private Modelo modelo;

	private JButton btnLogOut;
	private JButton btnGestinDeMsica;
	private JButton btnEliminar;

	private JLabel btnExit;
	private JLabel btnMinimize;
	private JLabel lblListaDeUsuarios;
	private JPanel panelUsuarios;
	private JLabel lblAdministrador;
	private JLabel logo;
	private JLabel lblBackground;

	private JTextField idEliminar = new JTextField();

	private JPanel panel;
	private JScrollPane scrollPaneUsuario;

	private JTable tablausers;

	private int xx, xy;

	public MenuAdminUsuarios() {

		setType(Type.POPUP);
		setTitle("Mugga (Admin)");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuAdminUsuarios.class.getResource("/imagen/iconLogo.png")));
		getContentPane().setForeground(UIManager.getColor("Menu.selectionBackground"));
		this.getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);

		setUndecorated(true);

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setLayout(null);

		Border border = BorderFactory.createLineBorder(Color.WHITE);

		panel = new JPanel();
		panel.setBounds(43, 180, 258, 300);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.LogOutAdmin();
			}
		});
		btnLogOut.setBorder(border);
		btnLogOut.setBounds(78, 72, 104, 42);
		panel.add(btnLogOut);

		btnGestinDeMsica = new JButton("Gesti\u00F3n de Musica");
		btnGestinDeMsica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.GMusica();
			}
		});
		btnGestinDeMsica.setBorder(border);
		btnGestinDeMsica.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGestinDeMsica.setForeground(Color.WHITE);
		btnGestinDeMsica.setBounds(10, 180, 238, 96);
		panel.add(btnGestinDeMsica);

		lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdministrador.setForeground(Color.WHITE);
		lblAdministrador.setBounds(69, 10, 128, 52);
		panel.add(lblAdministrador);

		panelUsuarios = new JPanel();
		panelUsuarios.setBounds(340, 48, 660, 487);
		getContentPane().add(panelUsuarios);
		panelUsuarios.setLayout(null);

		lblListaDeUsuarios = new JLabel("Lista de Usuarios");
		lblListaDeUsuarios.setBounds(238, 10, 210, 31);
		lblListaDeUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblListaDeUsuarios.setForeground(Color.WHITE);
		panelUsuarios.add(lblListaDeUsuarios);

		scrollPaneUsuario = new JScrollPane();
		scrollPaneUsuario.setBounds(0, 42, 660, 370);
		panelUsuarios.add(scrollPaneUsuario);

		tablausers = new JTable();
		scrollPaneUsuario.setViewportView(tablausers);
		tablausers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablausers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablausers.getSelectedRow() == -1) {

				} else {
					idEliminar.setText((String) tablausers.getValueAt(tablausers.getSelectedRow(), 0));
					validateDelete();
				}
			}
		});
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.eliminarUsuario();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBounds(238, 432, 199, 45);
		panelUsuarios.add(btnEliminar);
		btnEliminar.setBorder(border);
		btnEliminar.setEnabled(false);

		// Listener para obtener datos de la tabla usuario.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tablausers.setModel(modelo.getTablaUser());
				tablausers.clearSelection();
				btnEliminar.setEnabled(false);
			}
		});

		logo = new JLabel("");
		logo.setBounds(30, 30, 125, 125);
		getContentPane().add(logo);
		logo.setIcon(new ImageIcon(Login.class.getResource("/imagen/logo2.png")));

		/*
		 * Botones exit y minimizar con sus respectivos listeners para que cumplan su
		 * función y cambie el icono del cursor al estar encima.
		 */

		btnExit = new JLabel("x");
		btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setFont(new Font("SignPainter", Font.BOLD, 50));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(980, 10, 35, 35);
		getContentPane().add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			public void mouseEntered(MouseEvent e) {
				btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		btnMinimize = new JLabel("-");
		btnMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		btnMinimize.setFont(new Font("SignPainter", Font.BOLD, 60));
		btnMinimize.setForeground(Color.WHITE);
		btnMinimize.setBounds(950, 12, 35, 35);
		getContentPane().add(btnMinimize);
		btnMinimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}

			public void mouseEntered(MouseEvent e) {
				btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		// Listeners para poder mover la pantalla con el cursor.

		getContentPane().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});

		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				MenuAdminUsuarios.this.setLocation(x - xx, y - xy);
			}
		});

		panelUsuarios.setOpaque(true);
		panelUsuarios.setBackground(new Color(0, 0, 0, 0));
		panel.setOpaque(true);
		panel.setBackground(new Color(0, 0, 0, 0));

		lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1036, 602);
		lblBackground.setIcon(new ImageIcon(Login.class.getResource("/imagen/bg.jpg")));
		getContentPane().add(lblBackground);

		Border borderPane = BorderFactory.createLineBorder(Color.WHITE, 3);
		this.getRootPane().setBorder(borderPane);

		setBounds(100, 100, 1036, 602);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Método para habilitar el botón si se ha seleccionado alguna fila de nuestra
	 * tabla.
	 */

	private void validateDelete() {
		if (tablausers.getSelectedRow() != -1) {
			btnEliminar.setEnabled(true);
		} else {
			btnEliminar.setEnabled(false);
		}
	}

	// Getters y Setters

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public JTextField getIdEliminar() {
		return idEliminar;
	}

}
package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Controlador.Controlador;
import Modelo.Modelo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private Controlador controlador;
	private Modelo modelo;

	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblLogin;
	private JLabel lblBackground;
	private JLabel logo;
	private JLabel btnExit;
	private JLabel btnMinimize;
	private JLabel lblWarning;
	private JLabel lblWarningUser;
	private JButton btnLogin;
	private JButton btnRegister;

	private int counter;
	private int trys = 2;
	private int xx, xy;

	public Login() {

		counter = 0;

		setType(Type.POPUP);
		setTitle("Mugga (Login)");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagen/iconLogo.png")));
		getContentPane().setForeground(UIManager.getColor("Menu.selectionBackground"));
		this.getContentPane().setBackground(Color.BLACK);

		setUndecorated(true);

		/*
		 * Pantalla con bordes redondeados.
		 * 
		 * Necesario: Import 'RoundRectangle2D' (java.awt.geom)
		 * 
		 * setShape(new RoundRectangle2D.Double(0, 0, 1036, 602, 30, 30));
		 */

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setLayout(null);

		lblLogin = new JLabel("MUGGA");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Stencil Std", Font.PLAIN, 70));
		lblLogin.setBounds(395, 43, 263, 142);
		getContentPane().add(lblLogin);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(333, 255, 84, 25);
		getContentPane().add(lblUsuario);

		lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(333, 290, 103, 25);
		getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(448, 290, 150, 25);
		getContentPane().add(txtPassword);

		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btnLogin.isEnabled()) {
					counter = controlador.login(counter);
					ClearFields();
				}
			}
		});

		logo = new JLabel("");
		logo.setBounds(30, 30, 125, 125);
		getContentPane().add(logo);
		logo.setIcon(new ImageIcon(Login.class.getResource("/imagen/logo.png")));

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsuario.setBounds(448, 255, 150, 25);
		getContentPane().add(txtUsuario);

		txtUsuario.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Validate();
				if (e.getKeyCode() == KeyEvent.VK_ENTER && btnLogin.isEnabled()) {
					counter = controlador.login(counter);
					ClearFields();
				}
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counter = controlador.login(counter);
				ClearFields();
			}
		});

		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (btnLogin.isEnabled()) {
					btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
		});

		btnLogin.setBounds(458, 340, 130, 40);
		getContentPane().add(btnLogin);

		Border border = BorderFactory.createLineBorder(Color.WHITE);
		btnLogin.setBorder(border);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setForeground(Color.WHITE);

		btnRegister = new JButton("Registro");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.registro();
			}
		});
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setBounds(490, 390, 69, 30);
		getContentPane().add(btnRegister);
		btnRegister.setBorder(border);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegister.setForeground(Color.WHITE);

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

		// Listeners para poder mover la pantalla con el raton.

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
				Login.this.setLocation(x - xx, y - xy);
			}
		});

		lblWarningUser = new JLabel();
		lblWarningUser.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWarningUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarningUser.setBounds(333, 200, 300, 30);
		lblWarningUser.setForeground(new Color(0, 153, 0));
		lblWarningUser.setVisible(false);
		getContentPane().add(lblWarningUser);

		lblWarning = new JLabel();
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setBounds(423, 319, 200, 16);
		lblWarning.setForeground(Color.RED);
		lblWarning.setVisible(false);
		getContentPane().add(lblWarning);

		lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1036, 602);
		lblBackground.setIcon(new ImageIcon(Login.class.getResource("/imagen/background.jpeg")));
		getContentPane().add(lblBackground);

		Border borderPane = BorderFactory.createLineBorder(Color.WHITE, 3);
		this.getRootPane().setBorder(borderPane);
		setBounds(100, 100, 1036, 602);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Método de validación para comprobar si estan los campos vacios y habilitar el
	 * boton, limpiar campos para limpiar campos al presionar el boton o cambiar de
	 * ventana y avisos de errores al introducir credenciales erroneas.
	 */

	private void Validate() {
		if (!txtUsuario.getText().trim().equals("") && !String.valueOf(txtPassword.getPassword()).trim().equals("")) {
			btnLogin.setEnabled(true);
		} else
			btnLogin.setEnabled(false);
	}

	public void ClearFields() {
		txtUsuario.setText("");
		txtPassword.setText("");
		btnLogin.setEnabled(false);
		txtUsuario.grabFocus();
	}

	public void disableWarning() {
		lblWarning.setVisible(false);
		lblWarningUser.setVisible(false);
	}

	public void Warning() {
		lblWarning.setText("Datos incorrectos. Intentos: " + (trys - counter));
		lblWarning.setVisible(true);
	}

	public void WarningUser() {
		lblWarningUser.setText("Usuario creado correctamente.");
		lblWarningUser.setVisible(true);
	}

	// Getters y Setters

	public String getUser() {
		return txtUsuario.getText();
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

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

}
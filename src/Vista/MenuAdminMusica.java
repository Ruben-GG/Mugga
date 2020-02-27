package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Controlador.Controlador;
import Modelo.Modelo;
import javax.swing.JTable;
import java.awt.Cursor;

public class MenuAdminMusica extends JFrame {
	private Controlador controlador;
	private Modelo modelo;

	private JButton btnGestinDeUsuarios;
	private JButton btnLogOut;
	private JButton btnAnadir;
	private JButton btnModificar;
	private JButton btnLimpiar;
	private JButton btnEliminar;

	private JLabel btnExit;
	private JLabel btnMinimize;
	private JLabel lblListaDeMusica;
	private JLabel lblAdministrador;
	private JLabel lblAddMusica;
	private JLabel logo;
	private JLabel lblBackground;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblArtist;
	private JLabel lblDuration;
	private JLabel lblIdgender;
	private JLabel lblRefmp;
	private JLabel lblIdimg;
	private JSeparator separadorAdd;

	private JPanel panel;
	private JPanel panelListaMusica;
	private JPanel panelAddMusica;
	private JTable musicaAdmin;
	private JScrollPane scrollPane;

	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtArtist;
	private JTextField txtDuration;
	private JTextField txtIDGender;
	private JTextField txtRefMp3;
	private JTextField txtRefImg;
	private JTextField idModificar = new JTextField();

	private int xx, xy;

	public MenuAdminMusica() {

		setType(Type.POPUP);
		setTitle("Mugga (Admin)");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuAdminMusica.class.getResource("/imagen/iconLogo.png")));
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
				controlador.LogOut();
			}
		});
		btnLogOut.setBorder(border);
		btnLogOut.setBounds(78, 72, 104, 42);
		panel.add(btnLogOut);

		btnGestinDeUsuarios = new JButton("Gesti\u00F3n de Usuarios");
		btnGestinDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.GUsuarios();
			}
		});
		btnGestinDeUsuarios.setBorder(border);
		btnGestinDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGestinDeUsuarios.setForeground(Color.WHITE);
		btnGestinDeUsuarios.setBounds(10, 180, 238, 96);
		panel.add(btnGestinDeUsuarios);

		lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdministrador.setForeground(Color.WHITE);
		lblAdministrador.setBounds(69, 10, 128, 52);
		panel.add(lblAdministrador);

		// Listener para obtener datos de la tabla musica.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				musicaAdmin.setModel(modelo.getTablaMusic());

			}
		});

		panelListaMusica = new JPanel();
		panelListaMusica.setBounds(343, 48, 657, 312);
		getContentPane().add(panelListaMusica);
		panelListaMusica.setLayout(null);

		lblListaDeMusica = new JLabel("Lista de M\u00FAsica");
		lblListaDeMusica.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblListaDeMusica.setForeground(Color.WHITE);
		lblListaDeMusica.setBounds(251, 7, 289, 50);
		panelListaMusica.add(lblListaDeMusica);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 657, 257);
		panelListaMusica.add(scrollPane);

		musicaAdmin = new JTable();
		musicaAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(musicaAdmin);

		// Listener para añadir los datos de la base de datos en filas de nuestra tabla.
		musicaAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (musicaAdmin.getSelectedRow() == -1) {

				} else {

					txtID.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 0));
					idModificar.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 0));
					txtName.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 1));
					txtArtist.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 2));
					txtDuration.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 3));
					txtIDGender.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 4));
					txtRefMp3.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 5));
					txtRefImg.setText((String) musicaAdmin.getValueAt(musicaAdmin.getSelectedRow(), 6));
					validateFields();
					validateDelete();
				}
			}
		});

		panelAddMusica = new JPanel();
		panelAddMusica.setBounds(343, 370, 657, 153);
		getContentPane().add(panelAddMusica);
		panelAddMusica.setLayout(null);

		lblAddMusica = new JLabel("Modificaci\u00F3n de Musica");
		lblAddMusica.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAddMusica.setForeground(Color.WHITE);
		lblAddMusica.setBounds(215, 10, 279, 36);
		panelAddMusica.add(lblAddMusica);

		separadorAdd = new JSeparator();
		separadorAdd.setBounds(0, 48, 657, 9);
		panelAddMusica.add(separadorAdd);

		txtID = new JTextField();
		txtID.setBounds(10, 84, 83, 19);
		panelAddMusica.add(txtID);
		txtID.setColumns(10);

		txtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		txtName = new JTextField();
		txtName.setBounds(100, 84, 83, 19);
		panelAddMusica.add(txtName);
		txtName.setColumns(10);

		txtName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		txtArtist = new JTextField();
		txtArtist.setColumns(10);
		txtArtist.setBounds(190, 84, 83, 19);
		panelAddMusica.add(txtArtist);

		txtArtist.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		txtDuration.setBounds(280, 84, 83, 19);
		panelAddMusica.add(txtDuration);

		txtDuration.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		txtIDGender = new JTextField();
		txtIDGender.setColumns(10);
		txtIDGender.setBounds(370, 84, 83, 19);
		panelAddMusica.add(txtIDGender);

		txtIDGender.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		txtRefMp3 = new JTextField();
		txtRefMp3.setColumns(10);
		txtRefMp3.setBounds(460, 84, 83, 19);
		panelAddMusica.add(txtRefMp3);

		txtRefMp3.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		txtRefImg = new JTextField();
		txtRefImg.setColumns(10);
		txtRefImg.setBounds(550, 84, 83, 19);
		panelAddMusica.add(txtRefImg);

		txtRefImg.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				validateFields();
			}
		});

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.anadirmusica();
				clearFields();

			}
		});
		btnAnadir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnadir.setForeground(Color.WHITE);
		btnAnadir.setBounds(510, 113, 120, 35);
		btnAnadir.setBorder(border);
		panelAddMusica.add(btnAnadir);
		btnAnadir.setEnabled(false);

		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(13, 67, 83, 19);
		panelAddMusica.add(lblId);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(103, 67, 83, 19);
		panelAddMusica.add(lblName);

		lblArtist = new JLabel("Artist:");
		lblArtist.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblArtist.setForeground(Color.WHITE);
		lblArtist.setBounds(193, 67, 83, 19);
		panelAddMusica.add(lblArtist);

		lblRefmp = new JLabel("Ref_Mp3:");
		lblRefmp.setBounds(463, 67, 83, 19);
		panelAddMusica.add(lblRefmp);
		lblRefmp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRefmp.setForeground(Color.WHITE);

		lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDuration.setForeground(Color.WHITE);
		lblDuration.setBounds(283, 67, 83, 19);
		panelAddMusica.add(lblDuration);

		lblIdgender = new JLabel("Id_Gender:");
		lblIdgender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdgender.setForeground(Color.WHITE);
		lblIdgender.setBounds(373, 67, 83, 19);
		panelAddMusica.add(lblIdgender);

		lblIdimg = new JLabel("Id_Img:");
		lblIdimg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdimg.setForeground(Color.WHITE);
		lblIdimg.setBounds(553, 67, 83, 19);
		panelAddMusica.add(lblIdimg);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.modificarMusica();
				clearFields();
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBounds(190, 113, 120, 35);
		panelAddMusica.add(btnModificar);
		btnModificar.setBorder(border);
		btnModificar.setEnabled(false);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.eliminarMusica();
				clearFields();
			}
		});
		btnEliminar.setBounds(350, 113, 120, 35);
		panelAddMusica.add(btnEliminar);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBorder(border);
		btnEliminar.setEnabled(false);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnLimpiar.setBounds(30, 113, 120, 35);
		panelAddMusica.add(btnLimpiar);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setBorder(border);
		btnLimpiar.setEnabled(false);

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
				MenuAdminMusica.this.setLocation(x - xx, y - xy);
			}
		});

		// Metodos para hacer los paneles transparentes.

		panelListaMusica.setOpaque(true);
		panelListaMusica.setBackground(new Color(0, 0, 0, 0));
		panelAddMusica.setOpaque(true);
		panelAddMusica.setBackground(new Color(0, 0, 0, 0));
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
	 * Métodos de validación para habilitar botones segun que campos tengan texto o
	 * si esta seleccionada alguna fila de nuestra tabla.
	 */

	private void validateDelete() {
		if (musicaAdmin.getSelectedRow() != -1) {
			btnEliminar.setEnabled(true);
		} else {
			btnEliminar.setEnabled(false);
		}
	}

	private void validateFields() {
		if (!txtID.getText().trim().equals("") && !txtName.getText().trim().equals("")
				&& !txtArtist.getText().trim().equals("") && !txtDuration.getText().trim().equals("")
				&& !txtIDGender.getText().trim().equals("") && !txtRefMp3.getText().trim().equals("")
				&& !txtRefImg.getText().trim().equals("") && musicaAdmin.getSelectedRow() != -1) {

			btnModificar.setEnabled(true);
			btnLimpiar.setEnabled(true);
		} else if (!txtID.getText().trim().equals("") && !txtName.getText().trim().equals("")
				&& !txtArtist.getText().trim().equals("") && !txtDuration.getText().trim().equals("")
				&& !txtIDGender.getText().trim().equals("") && !txtRefMp3.getText().trim().equals("")
				&& !txtRefImg.getText().trim().equals("")) {

			btnAnadir.setEnabled(true);
		} else if (!txtID.getText().trim().equals("") || !txtName.getText().trim().equals("")
				|| !txtArtist.getText().trim().equals("") || !txtDuration.getText().trim().equals("")
				|| !txtIDGender.getText().trim().equals("") || !txtRefMp3.getText().trim().equals("")
				|| !txtRefImg.getText().trim().equals("")) {

			btnLimpiar.setEnabled(true);
		} else {
			btnAnadir.setEnabled(false);
			btnModificar.setEnabled(false);
		}
	}

	/*
	 * Metodo para limpiar los textfield.
	 */
	public void clearFields() {
		txtID.setText("");
		txtName.setText("");
		txtArtist.setText("");
		txtDuration.setText("");
		txtIDGender.setText("");
		txtRefMp3.setText("");
		txtRefImg.setText("");
		btnAnadir.setEnabled(false);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnLimpiar.setEnabled(false);
		txtID.grabFocus();
		musicaAdmin.clearSelection();
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

	public JTextField getTxtID() {
		return txtID;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtArtist() {
		return txtArtist;
	}

	public JTextField getTxtDuration() {
		return txtDuration;
	}

	public JTextField getTxtIDGender() {
		return txtIDGender;
	}

	public JTextField getTxtRefMp3() {
		return txtRefMp3;
	}

	public JTextField getTxtRefImg() {
		return txtRefImg;
	}

	public void setBtnGestinDeUsuarios(JButton btnGestinDeUsuarios) {
		this.btnGestinDeUsuarios = btnGestinDeUsuarios;
	}

	public void setBtnLogOut(JButton btnLogOut) {
		this.btnLogOut = btnLogOut;
	}

	public void setLblListaDeMusica(JLabel lblListaDeMusica) {
		this.lblListaDeMusica = lblListaDeMusica;
	}

	public void setPanelListaMusica(JPanel panelListaMusica) {
		this.panelListaMusica = panelListaMusica;
	}

	public void setPanelAddMusica(JPanel panelAddMusica) {
		this.panelAddMusica = panelAddMusica;
	}

	public void setSeparadorAdd(JSeparator separadorAdd) {
		this.separadorAdd = separadorAdd;
	}

	public void setLblAdministrador(JLabel lblAdministrador) {
		this.lblAdministrador = lblAdministrador;
	}

	public void setLblAddMusica(JLabel lblAddMusica) {
		this.lblAddMusica = lblAddMusica;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void setMusicaAdmin(JTable musicaAdmin) {
		this.musicaAdmin = musicaAdmin;
	}

	public void setBtnReproducir(JButton btnReproducir) {
		this.btnLimpiar = btnReproducir;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public void setTxtArtist(JTextField txtArtist) {
		this.txtArtist = txtArtist;
	}

	public void setTxtDuration(JTextField txtDuration) {
		this.txtDuration = txtDuration;
	}

	public void setTxtIDGender(JTextField txtIDGender) {
		this.txtIDGender = txtIDGender;
	}

	public void setTxtRefMp3(JTextField txtRefMp3) {
		this.txtRefMp3 = txtRefMp3;
	}

	public void setTxtRefImg(JTextField txtRefImg) {
		this.txtRefImg = txtRefImg;
	}

	public void setBtnAnadir(JButton btnAnadir) {
		this.btnAnadir = btnAnadir;
	}

	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public JTextField getIdModificar() {
		return idModificar;
	}

}
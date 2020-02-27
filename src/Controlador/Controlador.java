package Controlador;

import Modelo.Modelo;
import Vista.Login;
import Vista.MenuAdminMusica;
import Vista.MenuAdminUsuarios;
import Vista.MenuPlaylist;
import Vista.MenuPrincipal;
import Vista.Registro;

public class Controlador {
	private Modelo modelo;
	private Login vistaLogin;
	private Registro vistaRegistro;
	private MenuAdminUsuarios vistaAdminUsuario;
	private MenuAdminMusica vistaAdminMusica;
	private MenuPrincipal vistaPrincipal;
	private MenuPlaylist vistaPlaylist;

	public Controlador() {
		modelo = new Modelo();
	}

	/*
	 * AdminMusica -> Login
	 * 
	 * Metodo para volver al login desde la vista de administrador de musica
	 */
	public void LogOut() {
		this.vistaAdminMusica.dispose();
		vistaAdminMusica.clearFields();
		// Cerramos tambien la vista de usuarios
		if (vistaAdminUsuario != null)
			this.vistaAdminUsuario.dispose();
		if (vistaLogin != null) {
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		} else {
			this.vistaLogin = new Login();
			this.vistaLogin.setControlador(this);
			this.vistaLogin.setModelo(modelo);
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		}
	}

	/*
	 * AdminUsuario -> Login
	 * 
	 * Metodo para volver al login desde la vista de administrador de usuarios
	 */
	public void LogOutAdmin() {
		this.vistaAdminUsuario.dispose();
		// Cerramos la vista de admin musica si esta abierta
		if (vistaAdminMusica != null)
			this.vistaAdminMusica.dispose();
		if (vistaLogin != null) {
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		} else {
			this.vistaLogin = new Login();
			this.vistaLogin.setControlador(this);
			this.vistaLogin.setModelo(modelo);
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		}
	}

	/*
	 * AdminUsuario -> AdminMusica
	 * 
	 * Metodo para cambiar a la vista a admin Musica
	 */
	public void GMusica() {
		this.vistaAdminUsuario.setVisible(false);
		if (vistaAdminMusica != null) {
			this.vistaAdminMusica.setVisible(true);
			vistaAdminMusica.clearFields();
		} else {
			this.vistaAdminMusica = new MenuAdminMusica();
			this.vistaAdminMusica.setControlador(this);
			this.vistaAdminMusica.setModelo(modelo);
			this.vistaAdminMusica.setVisible(true);
			vistaAdminMusica.clearFields();
		}
	}

	/*
	 * AdminMusica -> AdminUsuario
	 * 
	 * Metodo para cambiar a la vista a admin Usuarios
	 */
	public void GUsuarios() {
		this.vistaAdminMusica.dispose();
		vistaAdminMusica.clearFields();
		if (vistaAdminUsuario != null) {
			this.vistaAdminUsuario.setVisible(true);
		} else {
			this.vistaAdminUsuario = new MenuAdminUsuarios();
			this.vistaAdminUsuario.setControlador(this);
			this.vistaAdminUsuario.setModelo(modelo);
			this.vistaAdminUsuario.setVisible(true);
		}
	}

	/**
	 * Metodo para hacer login en la aplicacion con tres intentos para acceder, al
	 * tercer intento se cierra la aplicación.
	 * 
	 * @param counter
	 * @return 0 credenciales erroneas e incremento de contador ,1 rol user, 2 rol
	 *         admin.
	 */
	public int login(int counter) {
		String usr = vistaLogin.getUser();
		String pwd = vistaLogin.getPassword();
		int aux;

		aux = modelo.consultaLogin(usr, pwd);
		// Comprobacion de roles
		if (aux == 1) {
			this.vistaLogin.dispose();
			vistaLogin.ClearFields();
			if (vistaPrincipal != null) {
				this.vistaPrincipal.setVisible(true);
			} else {
				this.vistaPrincipal = new MenuPrincipal();
				this.vistaPrincipal.setControlador(this);
				this.vistaPrincipal.setModelo(modelo);
				this.vistaPrincipal.setVisible(true);
			}
		} else if (aux == 2) {
			this.vistaLogin.dispose();
			vistaLogin.ClearFields();
			if (vistaAdminMusica != null) {
				this.vistaAdminMusica.setVisible(true);
			} else {
				this.vistaAdminMusica = new MenuAdminMusica();
				this.vistaAdminMusica.setControlador(this);
				this.vistaAdminMusica.setModelo(modelo);
				this.vistaAdminMusica.setVisible(true);
			}
		} else {
			counter++;
			vistaLogin.Warning();
		}
		// Cierre de la aplicacion al tercer intento fallido de login
		if (counter == 3) {
			this.vistaLogin.dispose();
			System.exit(-1);
		}

		return counter;
	}

	/*
	 * Login -> Registro
	 * 
	 * Metodo para cambiar de vista a registro.
	 */
	public void registro() {
		this.vistaLogin.setVisible(false);
		vistaLogin.ClearFields();
		vistaLogin.disableWarning();
		if (vistaRegistro != null) {
			this.vistaRegistro.setVisible(true);
		} else {
			this.vistaRegistro = new Registro();
			this.vistaRegistro.setControlador(this);
			this.vistaRegistro.setModelo(modelo);
			this.vistaRegistro.setVisible(true);
		}
	}

	// Registro e inserción de datos en la base de datos.
	public void register() {
		boolean aux;

		String usr = vistaRegistro.getUser();
		String pwd = vistaRegistro.getPassword();
		String name = vistaRegistro.getName();
		String surname = vistaRegistro.getSurname();
		String email = vistaRegistro.getEmail();

		aux = modelo.consultaRegister(usr);

		if (aux) {
			vistaRegistro.WarningUser();
		} else {
			modelo.Register(usr, pwd, name, surname, email);
			volverLogin();
			vistaLogin.ClearFields();
			vistaLogin.WarningUser();
		}
	}

	/*
	 * Registro -> Vuelta al login
	 * 
	 * Metodo para volver al login desde el registro.
	 */
	public void volverLogin() {
		this.vistaRegistro.dispose();
		vistaRegistro.ClearFields();
		vistaRegistro.disableWarning();
		if (vistaLogin != null) {
			this.vistaLogin.setVisible(true);
		} else {
			this.vistaLogin = new Login();
			this.vistaLogin.setControlador(this);
			this.vistaLogin.setModelo(modelo);
			this.vistaLogin.setVisible(true);
		}
	}

	/*
	 * Principal -> Playlist
	 * 
	 * Metodo para ir del menu al la creacion de playlist.
	 */
	public void crearPlaylist() {
		this.vistaPrincipal.dispose();
		vistaPrincipal.ClearFields();
		if (vistaPlaylist != null) {
			this.vistaPlaylist.setVisible(true);
		} else {
			this.vistaPlaylist = new MenuPlaylist();
			this.vistaPlaylist.setControlador(this);
			this.vistaPlaylist.setModelo(modelo);
			this.vistaPlaylist.setVisible(true);
		}
	}

	/*
	 * Principal -> Login
	 * 
	 * Metodo para volver al login desde el menu principal.
	 */
	public void LogOutUsuario() {
		this.vistaPrincipal.dispose();
		vistaPrincipal.ClearFields();
		if (vistaPlaylist != null)
			this.vistaPlaylist.dispose();
		if (vistaLogin != null) {
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		} else {
			this.vistaLogin = new Login();
			this.vistaLogin.setControlador(this);
			this.vistaLogin.setModelo(modelo);
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		}
	}

	/*
	 * Playlist -> Principal
	 * 
	 * Metodo para volver al menu desde la creacion de playlist.
	 */
	public void volverPrincipal() {
		this.vistaPlaylist.dispose();
		vistaPlaylist.ClearFields();
		if (vistaPrincipal != null) {
			this.vistaPrincipal.setVisible(true);
		} else {
			this.vistaPrincipal = new MenuPrincipal();
			this.vistaPrincipal.setControlador(this);
			this.vistaPrincipal.setModelo(modelo);
			this.vistaPrincipal.setVisible(true);
		}
	}

	/*
	 * Playlist -> Login
	 * 
	 * Metodo para ir desde la creacion de playlist a login.
	 */
	public void LogOutPlaylist() {
		this.vistaPlaylist.dispose();
		vistaPlaylist.ClearFields();
		if (vistaPrincipal != null)
			this.vistaPrincipal.dispose();
		if (vistaLogin != null) {
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		} else {
			this.vistaLogin = new Login();
			this.vistaLogin.setControlador(this);
			this.vistaLogin.setModelo(modelo);
			this.vistaLogin.setVisible(true);
			vistaLogin.ClearFields();
			vistaLogin.disableWarning();
		}
	}

	/*
	 * Metodo usado para añadir musica a la tabla, pasando la informacion al modelo.
	 */
	public void anadirmusica() {
		int id = Integer.parseInt(vistaAdminMusica.getTxtID().getText());
		String name = vistaAdminMusica.getTxtName().getText();
		String artist = vistaAdminMusica.getTxtArtist().getText();
		String duration = vistaAdminMusica.getTxtDuration().getText();
		int id_gender = Integer.parseInt(vistaAdminMusica.getTxtIDGender().getText());
		String ref_mp3 = vistaAdminMusica.getTxtRefMp3().getText();
		String ref_img = vistaAdminMusica.getTxtRefImg().getText();

		modelo.AñadirMusica(id, name, artist, duration, id_gender, ref_mp3, ref_img);
		// recargamos la vista
		vistaAdminMusica.setVisible(false);
		vistaAdminMusica.setVisible(true);
	}

	/*
	 * Metodo usado para eliminar datos de la tabla, pasando el id al modelo.
	 */
	public void eliminarMusica() {
		int id = Integer.parseInt(vistaAdminMusica.getTxtID().getText());
		modelo.EliminarMusica(id);
		vistaAdminMusica.setVisible(false);
		vistaAdminMusica.setVisible(true);
	}

	/*
	 * Metodo usado para modificar datos de la tabla, pasando la informacion al
	 * modelo
	 */
	public void modificarMusica() {
		int id = Integer.parseInt(vistaAdminMusica.getTxtID().getText());
		String name = vistaAdminMusica.getTxtName().getText();
		String artist = vistaAdminMusica.getTxtArtist().getText();
		String duration = vistaAdminMusica.getTxtDuration().getText();
		int id_gender = Integer.parseInt(vistaAdminMusica.getTxtIDGender().getText());
		String ref_mp3 = vistaAdminMusica.getTxtRefMp3().getText();
		String ref_img = vistaAdminMusica.getTxtRefImg().getText();
		int idModificar = Integer.parseInt(vistaAdminMusica.getIdModificar().getText());
		modelo.ModificarMusica(idModificar, id, name, artist, duration, id_gender, ref_mp3, ref_img);
		// recargamos la vista
		vistaAdminMusica.setVisible(false);
		vistaAdminMusica.setVisible(true);
	}

	/*
	 * Metodo usado para eliminar datos de la tabla usuario, pasando el id al
	 * modelo.
	 */
	public void eliminarUsuario() {
		String user = vistaAdminUsuario.getIdEliminar().getText();
		modelo.EliminarUsuario(user);
		// recargamos la vista
		vistaAdminUsuario.setVisible(false);
		vistaAdminUsuario.setVisible(true);

	}

	// Setters

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setVistaLogin(Login vistaLogin) {
		this.vistaLogin = vistaLogin;
	}

}
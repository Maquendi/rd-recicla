package com.rd_recicla.repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rd_recicla.models.Usuario;
import com.rd_recicla.utils.Conexion;


public class UsuarioRepository {
	
private Conexion conn;
	
	public UsuarioRepository() {
		conn = new Conexion();
	}
	
	
	public List<Usuario> findAll() throws Exception{
	
		List<Usuario> lista = new ArrayList<>();
		String query = "SELECT * FROM usuario";
		PreparedStatement pst = null;
		Usuario user = null;
		
		try{
			Connection conexion = conn.connect();
			pst = (PreparedStatement) conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()){
				user = new Usuario();
				user.setId(rs.getLong("id"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setContrasena(rs.getString("contrasena"));
				lista.add(user);
			
			  }
			
			  }catch(Exception e){
			      throw e;
		    }finally{
			    conn.disconect();
		}

		return lista;		
		
	}
	
	
	
	public Long registerUser(Usuario usuario) throws SQLException {
		
		
		String query = "insert into usuario(nombre,apellido,email,contrasena) VALUES(?,?,?,?)";
		
		PreparedStatement pst = null;
		Connection conexion = conn.connect();
		Long savedId = 0L;
		
		try {
			pst = (PreparedStatement) conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1,usuario.getNombre());
			pst.setString(2,usuario.getApellido());
			pst.setString(3,usuario.getEmail());
			pst.setString(4,usuario.getContrasena());
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next()) {
				savedId = rs.getLong(1);
			}
			
			
			
		}catch(SQLException e) {
			throw e;
		}finally {
			conn.disconect();
		}
		
		
		return savedId;
		
	}
	
	
	
	public void deleteUsuario(Usuario usuario) throws SQLException {
		
		

		String query = "delete from usuario where id = ?";
		
		PreparedStatement pst = null;
		Connection conexion = conn.connect();
		
		
		try {
			pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setLong(1,usuario.getId());
			pst.executeUpdate();
						
			
		}catch(SQLException e) {
			throw e;
		}finally {
			conn.disconect();
		}

	}
	
	
	
	public Usuario findById(Long id) throws SQLException {
		
String query = "SELECT * FROM usuario WHERE id = ?";
		
		PreparedStatement pst = null;
		Connection conexion = conn.connect();
		Usuario user = null;
		
		try {
			pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setLong(1,id);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new Usuario();
				user.setId(rs.getLong("id"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));
				user.setEmail(rs.getString("email"));
				user.setContrasena(rs.getString("contrasena"));
			}
		
			
		}catch(SQLException e) {
			throw e;
		}finally {
			conn.disconect();
		}
		
		
		return user;
		
	}
	
	
	
	
	
	public Usuario findAuthor(Long id_incidente) throws SQLException {
		   
		   String query = "SELECT u.id,u.nombre,u.apellido,u.email,u.contrasena from usuario u INNER JOIN denuncia d on d.usuario = u.id WHERE d.id_denuncia =?";
			
			PreparedStatement pst = null;
			Connection conexion = conn.connect();
			Usuario user = null;
			
			try {
				pst = (PreparedStatement) conexion.prepareStatement(query);
				pst.setLong(1,id_incidente);
				
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					user = new Usuario();
					user.setId(rs.getLong("id"));
					user.setNombre(rs.getString("nombre"));
					user.setApellido(rs.getString("apellido"));
					user.setEmail(rs.getString("email"));
					user.setContrasena(rs.getString("contrasena"));
				}
			
			}catch(SQLException e) {
				throw e;
			}finally {
				conn.disconect();
			}
			
			return user;
	   }
	
	
	
	
	
	
	
	
	public String updateUser(Usuario user) throws SQLException {
		
		String query = "update usuario set nombre = ?,apellido = ?, email=?,contrasena=?"
				+ " where id=?";
				
				PreparedStatement pst = null;
				Connection conexion = conn.connect();
				String mensaje = "";
				
				if(user.getId() == null) {
					return "Which User ?";
				}
				
				try {
					pst = (PreparedStatement) conexion.prepareStatement(query);
					pst.setString(1,user.getNombre());
					pst.setString(2,user.getApellido());
					pst.setString(3,user.getEmail());
					pst.setString(4,user.getContrasena());
					pst.setLong(5,user.getId());
					
					if(pst.executeUpdate() > 0) {
						mensaje = "Success";
					}else {
						mensaje = "No User With Id " + user.getId() + " found !!.";
					}
					
					
					
				
				}catch(SQLException e) {
					mensaje = "Error !! Operation Failed.";
					throw e;
					
				}finally {
					conn.disconect();
				}
				
				return mensaje;
			}
			
	
	

}

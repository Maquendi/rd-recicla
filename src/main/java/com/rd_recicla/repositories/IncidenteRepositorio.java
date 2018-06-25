package com.rd_recicla.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rd_recicla.models.Incidente;
import com.rd_recicla.models.Usuario;
import com.rd_recicla.utils.Conexion;

public class IncidenteRepositorio {
	
	
	
private Conexion conn;
	
	public IncidenteRepositorio() {
		conn = new Conexion();
	}
	
	
	public List<Incidente> findAll() throws Exception{
	
		List<Incidente> lista = new ArrayList<>();
		String query = "SELECT * FROM usuario u JOIN denuncia d  ON d.usuario = u.id";
		PreparedStatement pst = null;
		Incidente incidencia = null;
		Usuario usuario = null;
		
		try{
			Connection conexion = conn.connect();
			pst = (PreparedStatement) conexion.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()){
				
				incidencia = new Incidente();
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setContrasena(rs.getString("contrasena"));
				
				incidencia.setId(rs.getLong("id_denuncia"));
				incidencia.setTitulo(rs.getString("titulo"));
				incidencia.setDetalle(rs.getString("detalle"));
				incidencia.setEstado(rs.getString("estado"));
				incidencia.setLongitud(rs.getString("longitud"));
                incidencia.setLatitud(rs.getString("latitud"));
                incidencia.setUsuario(usuario);
                lista.add(incidencia);
			
			  }
			
			  }catch(Exception e){
			      throw e;
		    }finally{
			    conn.disconect();
		}

		return lista;		
		
	}
	
	
	
	public Long guardar(Incidente incidencia) throws SQLException {
		
		
		String query = "insert into denuncia(titulo,detalle,estado,longitud,latitud,usuario) VALUES(?,?,?,?,?,?)";
		
		PreparedStatement pst = null;
		Connection conexion = conn.connect();
		Long savedId = 0L;
		
		try {
			pst = (PreparedStatement) conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1,incidencia.getTitulo());
			pst.setString(2,incidencia.getDetalle());
			pst.setString(3,incidencia.getEstado());
			pst.setString(4,incidencia.getLongitud());
			pst.setString(5,incidencia.getLatitud());
			pst.setLong(6,incidencia.getUsuario().getId());
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
	
	
	
	public String deleteIncidencia(Long id) throws SQLException {
		
		

		String query = "delete from denuncia where id_denuncia = ?";
		
		PreparedStatement pst = null;
		Connection conexion = conn.connect();
		String mensaje = "";
		
		try {
			pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setLong(1,id);
			
			if(pst.executeUpdate()>0) {
				mensaje = "Exito!!";
			}else {
				mensaje = "Registro No Encontrado";
			}
		}catch(SQLException e) {
			mensaje = "Error!! Operacion Abortada";
			throw e;
		}finally {
			conn.disconect();
		}
     return mensaje;
	}
	
	
	
	public Incidente findById(Long id) throws SQLException {
		
        String query = "SELECT * FROM usuario u JOIN denuncia d  ON d.usuario = u.id WHERE d.id_denuncia = ?";
		
		PreparedStatement pst = null;
		Connection conexion = conn.connect();
		Incidente incidencia = null;
		Usuario usuario = null;
		
		try {
			pst = (PreparedStatement) conexion.prepareStatement(query);
			pst.setLong(1,id);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				incidencia = new Incidente();
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEmail(rs.getString("email"));
				usuario.setContrasena(rs.getString("contrasena"));
				
				incidencia.setId(rs.getLong("id_denuncia"));
				incidencia.setTitulo(rs.getString("titulo"));
				incidencia.setDetalle(rs.getString("detalle"));
				incidencia.setEstado(rs.getString("estado"));
				incidencia.setLongitud(rs.getString("longitud"));
                incidencia.setLatitud(rs.getString("latitud"));
                incidencia.setUsuario(usuario);
			}
		
		}catch(SQLException e) {
			throw e;
		}finally {
			conn.disconect();
		}
		
		return incidencia;
	}
	
	
	
	public String udpateIncidente(Incidente incidencia) throws SQLException {
		
		String query = "update denuncia set titulo=?,detalle=?,estado=?,longitud=?,latitud=? where id_denuncia=?";
				
				PreparedStatement pst = null;
				Connection conexion = conn.connect();
				String mensaje = "";
				
				if(incidencia.getId() == null) {
					return "Which One ?";
				}
				
				try {
					pst = (PreparedStatement) conexion.prepareStatement(query);
					pst.setString(1,incidencia.getTitulo());
					pst.setString(2,incidencia.getDetalle());
					pst.setString(3,incidencia.getEstado());
					pst.setString(4,incidencia.getLongitud());
					pst.setString(5,incidencia.getLatitud());
					pst.setLong(6,incidencia.getId());
					
					if(pst.executeUpdate() > 0) {
						mensaje = "Success";
					}else {
						mensaje = "No Case With Id " + incidencia.getId() + " exists.";
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

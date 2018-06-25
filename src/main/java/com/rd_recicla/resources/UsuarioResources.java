package com.rd_recicla.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.rd_recicla.messages.ErrorMessage;
import com.rd_recicla.messages.IMessage;
import com.rd_recicla.messages.SuccessMessage;
import com.rd_recicla.models.Usuario;
import com.rd_recicla.repositories.UsuarioRepository;

@Path("usuarios")
public class UsuarioResources {

	
	private UsuarioRepository userRepository = new UsuarioRepository();
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> findAll(@QueryParam("author") Long AuthorId) {

		List<Usuario> users = null;
		
		try {
			
	       users = userRepository.findAll();
		   
	       if(users != null) {
	    	  return users;
	       }
	       
		}catch(Exception e) {
			return new ArrayList<Usuario>();
		}
		return users;
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public IMessage register(Usuario usuario) {
		
		IMessage message = null;
		
	try {
			
			message = new SuccessMessage(String.valueOf(userRepository.registerUser(usuario)));
		}catch(Exception e) {
			message = new ErrorMessage("Error!! Operation Failed.");
		}
		
		return message;
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IMessage update(Usuario usuario) {
		IMessage mensaje = null;
		try {
			
			mensaje = new SuccessMessage(userRepository.updateUser(usuario));
			
		}catch(Exception e) {
			mensaje = new ErrorMessage("Error !! Operation Failed !");
		}
		return mensaje;
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Usuario findById(@PathParam("id") Long id) {
		
		Usuario user = null;
		try {
			user = this.userRepository.findById(id);
			if(user != null) {
				return user;
			}else {
				return new Usuario();
			}
			
		}catch(Exception e) {
			return user;
		}
		
	 }
	
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/author/{id}")
	public Usuario findAuthor(@PathParam("id") Long authorId) {
		Usuario user = null;
		try {
			user = userRepository.findAuthor(authorId);
			
			if(user != null) {
				return user;
			}
			
		}catch(Exception e) {
			user = null;
		}
		return user;
	}
	
	
	
}

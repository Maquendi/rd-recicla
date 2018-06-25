package com.rd_recicla.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rd_recicla.messages.ErrorMessage;
import com.rd_recicla.messages.IMessage;
import com.rd_recicla.messages.SuccessMessage;
import com.rd_recicla.models.Incidente;
import com.rd_recicla.repositories.IncidenteRepositorio;

@Path("informes")
public class InformeResources {
	
	
	private IncidenteRepositorio indenciaRepositorio = new IncidenteRepositorio();
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Incidente findById(@PathParam("id") Long id) {
		Incidente obj = null;
		try {
		     	obj = this.indenciaRepositorio.findById(id);
		     	
		     	if(obj != null) {
		     		return obj;
		     	}else {
		     		obj = new Incidente();
		     		obj.setEstado("El Dato Solicitado No Existe");
		     		return obj;
		     	}
			
		}catch(Exception e) {
			return obj;
		}
	}
	
	
	
	
	@DELETE
	@Path("/{id}")
	public IMessage delete(@PathParam("id") Long id) {
		
		IMessage message = null;
		
		try {
			
			message = new SuccessMessage(indenciaRepositorio.deleteIncidencia(id));
		}catch(Exception e) {
			message = new ErrorMessage("Ha Ocurrido Un Error !!");
		}
		return message;
	}
	
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IMessage update(Incidente incidencia) {
		
		IMessage message = null;
		try {
			
			message = new SuccessMessage(indenciaRepositorio.udpateIncidente(incidencia));
			
		}catch(Exception e) {
			message = new ErrorMessage("Error !, Operation Failed");
		}
		
		return message;
	}
	
	
	
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Incidente> findAll() {
		
		List<Incidente> lista = null;
		try {
			
			lista = indenciaRepositorio.findAll();
			return lista;

		}catch(Exception e) {
			return new ArrayList<Incidente>();
		}

	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public IMessage persist(Incidente incidente) {
		
		IMessage message = null;
		
		try {
			
			Long id = indenciaRepositorio.guardar(incidente);
			
			if(id > 0) {
				message = new SuccessMessage("Success " + id);
			}else {
				message = new ErrorMessage("Operation Failed");
			}
			
		}catch(Exception e) {
			message = new ErrorMessage("Error !! Operation Failed.");
		}
		
		return message;
	}
	
	
	
	
	
	

}

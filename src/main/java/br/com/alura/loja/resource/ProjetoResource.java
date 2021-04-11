package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.ProjetoDAO;

/*
 * É semelhante a um controlador que responde pelo endpoint da URI.
 */
@Path("projetos")
public class ProjetoResource {

	/*
	 * Define o formato de retorno desse método e
	 * para qual verbo HTTP responderá.
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		return new ProjetoDAO().busca(1L).toXML();
	}
}

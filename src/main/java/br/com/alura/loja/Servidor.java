package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static void main(String[] args) throws IOException {

		/*
		 * Cria uma URI para host ou domínio onde o servidor vai responder
		 * às requisições.
		 */
		URI uri = URI.create("http://localhost:8080/");
		/*
		 * Definir um conjunto de configurações que serão
		 * passadas para o servidor.
		 */
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		/*
		 *Cria um servidor HTTP usando uma fábrica de servidor HTTP.
		 */
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		/*
		 * Códigos para validação apenas.
		 */
		System.out.println("Servidor rodando...");
		System.in.read();
		server.stop();
	}

}

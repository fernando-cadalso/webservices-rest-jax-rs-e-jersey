package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

	private String nome;
	private long id;
	private int anoDeInicio;

	public Projeto(long id, String nome, int anoDeInicio) {
		this.nome = nome;
		this.setId(id);
		this.anoDeInicio = anoDeInicio;
	}

	public Projeto() {
	}
	/*
	 * Retorna uma string no formato XML
	 * de um projeto.
	 */
	public String toXML() {
		return new XStream().toXML(this);
	}
	/*
	 * Retorna uma string no formato JSON, ou
	 * um objeto JSON.
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}
	public String getNome() {
		return nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}

}

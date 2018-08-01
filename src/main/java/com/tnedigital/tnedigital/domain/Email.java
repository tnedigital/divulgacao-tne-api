package com.tnedigital.tnedigital.domain;

import java.io.File;

public class Email {

	private File contatos;
	private String titulo;
	private String conteudo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public File getContatos() {
		return contatos;
	}

	public void setContatos(File contatos) {
		this.contatos = contatos;
	}

}

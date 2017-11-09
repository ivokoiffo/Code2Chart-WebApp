package com.grupo401.proyecto;

public class FormData {
	
	private String githubUrl;
	private String name;
	private String author;
	
	public FormData(){		
	}

	public FormData(String git,String name,String author){
		this.githubUrl = git;
        this.name = name;
        this.author = author;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}

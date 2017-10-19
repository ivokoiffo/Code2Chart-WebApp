package com.grupo401.proyecto;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class FormData {
	
	private String githubUrl;
	private Object localPath;
	private String name;
	private String description;
	private String author;
	
	public FormData(){
		
	}

	public FormData(String git,Object local,String name,String author,String desc){
		this.githubUrl = git;
        this.localPath = local;
        this.name = name;
        this.description = author;
        this.author = desc;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public Object getLocalPath() {
		return localPath;
	}

	public void setLocalPath(JSONPObject localPath) {
		this.localPath = localPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}

package com.evrythng.thng.resource.model.store;

public class Campaign {
	private String url;
	private Template template;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(final Template templateChosen) {
		this.template = templateChosen;
	}
}

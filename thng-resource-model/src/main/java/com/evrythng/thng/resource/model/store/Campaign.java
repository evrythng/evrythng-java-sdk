package com.evrythng.thng.resource.model.store;

public class Campaign {
	private String url;
	private TemplateChosen templateChosen;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public TemplateChosen getTemplateChosen() {
		return templateChosen;
	}

	public void setTemplateChosen(final TemplateChosen templateChosen) {
		this.templateChosen = templateChosen;
	}
}

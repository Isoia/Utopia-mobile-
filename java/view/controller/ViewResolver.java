package view.controller;

public class ViewResolver {
	public String prefix;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getView(String viewName) {
		return prefix + viewName;
	}
}

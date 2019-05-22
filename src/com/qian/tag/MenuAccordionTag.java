package com.qian.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qian.entity.Module;

/**
 * 首页左侧导航栏生成
 * 
 * 
 */
public class MenuAccordionTag extends SimpleTagSupport {

	private Module child;
	private String urlPrefix;

	@Override
	public void doTag() throws JspException, IOException {

		HttpServletRequest request = (HttpServletRequest) ((PageContext) this.getJspContext()).getRequest();

		String path = (String) request.getContextPath();

		getJspContext().getOut().write("<div title=\"" + child.getName() + "\"  icon=\"icon-sys\" style=\"overflow:auto;\">");
		loop(child, path);
		getJspContext().getOut().write("</ul></div>");
	}

	private void loop(Module module, String path) throws IOException, JspException {
		if (module.getChildren() != null && module.getChildren().size() > 0) {
			getJspContext().getOut().write("<ul>");
			for (Module m : module.getChildren()) {
				getJspContext().getOut().write(
						"<li><div><a target=\"mainFrame\" ghref=\"" + path + m.getUrl() + "\"  ><span class=\"icon icon-nav\" ></span>" + m.getName()
								+ "</a></div></li>");
				getJspContext().getOut().write("</li>\n");
			}
		}
	}

	public void setChild(Module child) {
		this.child = child;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
}

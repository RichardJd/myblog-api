package br.com.rjsystems.myblog.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("myblog")
public class MyBlogProperty {
	
	private String permitedOrigin = "http://localhost:8080";
	
	private final Security security = new Security();

	public String getPermitedOrigin() {
		return permitedOrigin;
	}

	public void setPermitedOrigin(String permitedOrigin) {
		this.permitedOrigin = permitedOrigin;
	}

	public Security getSecurity() {
		return security;
	}

	public static class Security {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
}

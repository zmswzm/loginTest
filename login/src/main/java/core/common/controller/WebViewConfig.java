package core.common.controller;

import java.util.Properties;

public class WebViewConfig {
	private String requestUrlBasePath;
	
	private String mappingBasePath;
	
	private String mappingFileName;
	
	public WebViewConfig() {
	}
	
	public WebViewConfig(String requestUrlBasePath, String mappingBasePath, String mappingFileName, Properties urlMappingTable) {
		this.requestUrlBasePath = requestUrlBasePath;
		this.mappingBasePath = mappingBasePath;
		this.mappingFileName = mappingFileName;
		this.urlMappingTable = urlMappingTable;
	}
	
	public String getMappingBasePath() {
		return mappingBasePath;
	}

	public void setMappingBasePath(String mappingBasePath) {
		this.mappingBasePath = mappingBasePath;
	}

	public String getMappingFileName() {
		return mappingFileName;
	}

	public void setMappingFileName(String mappingFileName) {
		this.mappingFileName = mappingFileName;
	}

	public Properties getUrlMappingTable() {
		return urlMappingTable;
	}

	public void setUrlMappingTable(Properties urlMappingTable) {
		this.urlMappingTable = urlMappingTable;
	}
	
	private Properties urlMappingTable;

	public String getRequestUrlBasePath() {
		return requestUrlBasePath;
	}

	public void setRequestUrlBasePath(String requestUrlBasePath) {
		this.requestUrlBasePath = requestUrlBasePath;
	}

}

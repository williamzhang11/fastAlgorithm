package com.xiu.fastAlgorithm.autoRefresh;

public class SystemConfig {

    private Long configId;

    private String configProp;//key

    private String configValue;//value

    private String description;

    private Integer versionId;

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public String getConfigProp() {
		return configProp;
	}

	public void setConfigProp(String configProp) {
		this.configProp = configProp;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
    
    
}

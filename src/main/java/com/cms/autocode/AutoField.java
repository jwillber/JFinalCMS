package com.cms.autocode;

public class AutoField {

	/** 标题 */
	private String title;
	/** 类型 */
	private String type;
	/** 名称 */
	private String name;
	/** 是否作为搜索 */
	private Boolean useSearch;
	/** 是否作为列表标题 */
	private Boolean useTitle;
	/** 是否作为列表值 */
	private Boolean useList;
	/** 是否必填 */
	private Boolean isRequired;
	/** 是否数字 */
    private Boolean isDigits;
    /** 表达式 */
    private String pattern;
    
	public Boolean getUseList() {
		return useList;
	}
	public void setUseList(Boolean useList) {
		this.useList = useList;
	}
	public Boolean getUseSearch() {
		return useSearch;
	}
	public void setUseSearch(Boolean useSearch) {
		this.useSearch = useSearch;
	}
	public Boolean getUseTitle() {
		return useTitle;
	}
	public void setUseTitle(Boolean useTitle) {
		this.useTitle = useTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public Boolean getIsRequired() {
        return isRequired;
    }
    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }
    public Boolean getIsDigits() {
        return isDigits;
    }
    public void setIsDigits(Boolean isDigits) {
        this.isDigits = isDigits;
    }
    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}

package com.cms.autocode;

import java.util.List;

public class AutoClass {

	/** 包名 */
	private String packageName;
	/** 类名 */
	private String className;
	/** 功能 */
	private String useName;
	/** 路径 */
	private String pathName;
	/** 对象名 */
	private String objectName;
	/** 是否使用添加 */
	private String useAdd;
	/** 是否使用删除 */
	private String useDelete;
	/** 字段 */
	private List<AutoField> fields;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getUseAdd() {
		return useAdd;
	}
	public void setUseAdd(String useAdd) {
		this.useAdd = useAdd;
	}
	public String getUseDelete() {
		return useDelete;
	}
	public void setUseDelete(String useDelete) {
		this.useDelete = useDelete;
	}
	public List<AutoField> getFields() {
		return fields;
	}
	public void setFields(List<AutoField> fields) {
		this.fields = fields;
	}
}

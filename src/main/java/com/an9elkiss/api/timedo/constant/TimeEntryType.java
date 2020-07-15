package com.an9elkiss.api.timedo.constant;

public enum TimeEntryType {

	SPORTS_EXERCISE(1,"运动", "mdi-directions-run"),
	READING(2,"阅读", "mdi-graduation-cap"),
	IT_LEARNING(3,"IT技术", "mdi-desktop-mac"),
	ENTERTAINMENT(4,"休息娱乐", "mdi-playstation"),
	FINANCE(5,"金融理财", "mdi-money-box"),
	CHILD_EDUCATION(6,"育儿", "mdi-face");

	private Integer typeId;
	private String typeName;
	private String iconName;
	

	private TimeEntryType(Integer typeId, String typeName, String iconName) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.iconName = iconName;
	}


	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

}

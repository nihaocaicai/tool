package com.tool.api.entity;

public class User {
    private String user_id;
    private String user_name;
    private String user_avatar;
    private Integer user_gender;
    private String user_city;
    private String user_target;
    private String user_motto;
    
    

    public User(String user_id, String user_name, String user_avatar, Integer user_gender, String user_city,
			String user_target, String user_motto) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_avatar = user_avatar;
		this.user_gender = user_gender;
		this.user_city = user_city;
		this.user_target = user_target;
		this.user_motto = user_motto;
	}

	public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public Integer getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(Integer user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_target() {
        return user_target;
    }

    public void setUser_target(String user_target) {
        this.user_target = user_target;
    }

    public String getUser_motto() {
        return user_motto;
    }

    public void setUser_motto(String user_motto) {
        this.user_motto = user_motto;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", user_gender=" + user_gender +
                ", user_city='" + user_city + '\'' +
                ", user_target='" + user_target + '\'' +
                ", user_motto='" + user_motto + '\'' +
                '}';
    }
}

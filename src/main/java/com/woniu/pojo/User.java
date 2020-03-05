package com.woniu.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	private Integer user_id;
	private String user_name;
	private String user_pwd;
	private String user_account;
	private String user_email;
	private Date user_date;
}

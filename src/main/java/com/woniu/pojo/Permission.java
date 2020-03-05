package com.woniu.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
	private Integer pid;
	private String name;
	private Integer parentid;
	private Boolean open;
	private List<Permission> children = new ArrayList<Permission>();
	private String url;
}

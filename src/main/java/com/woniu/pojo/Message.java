package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {
	private Boolean flag;
	private PageInfo<T> pageInfo;
	private T t;
	private Object obj;
}

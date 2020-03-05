package com.woniu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {
	private Integer totalPage;
	private Integer PageSize;
	private Integer currentPage;
	private Integer totalCount;
	private List<T> list;
	private String searchVal;
}

package com.ebao.gs.integration.mapping.configuration.bean;

import java.util.ArrayList;
import java.util.List;

public class MapAndReducerBean {

	private List<MapperDefinition> mapperList = new ArrayList<MapperDefinition>();

	private MergeBean merge;

	private List<ReducerDefinition> reducerList = new ArrayList<ReducerDefinition>();

	public void addMapper(MapperDefinition mapper) {
		this.mapperList.add(mapper);
	}

	public void setMerger(MergeBean merger) {
		this.merge = merger;
	}

	public void addReducer(ReducerDefinition reducer) {
		this.reducerList.add(reducer);
	}

	public List<MapperDefinition> getMapperList() {
		return mapperList;
	}

	public List<ReducerDefinition> getReducerList() {
		return reducerList;
	}

	public MergeBean getMerge() {
		return merge;
	}

}

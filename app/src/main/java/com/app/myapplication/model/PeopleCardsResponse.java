package com.app.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PeopleCardsResponse{

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("info")
	private Info info;

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setInfo(Info info){
		this.info = info;
	}

	public Info getInfo(){
		return info;
	}
}
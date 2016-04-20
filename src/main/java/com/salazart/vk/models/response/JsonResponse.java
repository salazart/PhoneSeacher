package com.salazart.vk.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponse {
	
	@JsonProperty("error")
 	private Error error;
	
	public boolean isErrorResponse(){
 		return error != null;
 	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}

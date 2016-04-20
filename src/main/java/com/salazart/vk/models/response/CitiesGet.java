package com.salazart.vk.models.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salazart.vk.models.VkCity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitiesGet  extends JsonResponse{
    @JsonProperty("response")
	List<VkCity> cities = new ArrayList<VkCity>();

    public List<VkCity> getCities() {
        return cities;
    }

    public void setCities(List<VkCity> cities) {
        this.cities = cities;
    }

}

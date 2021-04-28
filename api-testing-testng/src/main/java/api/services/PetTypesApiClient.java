package api.services;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import api.services.data.PetType;
import api.services.data.PetTypeItem;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class PetTypesApiClient extends ApiClient {
    public PetTypesApiClient(String baseUrl, String basePath) {
        super(baseUrl, basePath);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    //Verify status code for the pet type rest controller
    public Integer getPetStatus() throws InvalidResponseException {

        ApiResponse response = caller.executeRequest(getRequest(), Method.GET, PetType.class);
        return response.getHttpStatusCode();
    }
/*
    //Header verification

    public Headers getPetHeader(String s) throws InvalidResponseException {

        ApiResponse response = caller.executeRequest(getRequest(), Method.GET, PetType.class);
        return response..getHttpHeaders();

    }*/

    //GET all Pet Type details
    public PetTypeItem[] getPetTypes() throws InvalidResponseException {
        ApiResponse<PetTypeItem[]> response = caller.executeRequest(getRequest(), Method.GET, PetTypeItem[].class);
        return response.getContent();
    }

    //Add New Pet Type Details
    public PetTypeItem createdPetType(PetTypeItem response) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(response).withHeader("Content-Type", "application/json");
        ApiResponse<PetTypeItem> response1 = caller.executeRequest(request, Method.POST, PetTypeItem.class);
        return response1.getContent();
    }

}

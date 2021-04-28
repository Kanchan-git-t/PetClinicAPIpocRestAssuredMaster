package api.services;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import api.services.data.Specialties;
import api.services.data.VeterinariansItem;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class VetApiClient extends ApiClient {
    public VetApiClient(String baseUrl, String basePath) {
        super(baseUrl, basePath);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    //Verify status code
    public Integer getVetStatus() throws InvalidResponseException {

        ApiResponse response = caller.executeRequest(getRequest(), Method.GET, Specialties.class);
        return response.getHttpStatusCode();
    }

    //GET Vets details
    public VeterinariansItem[] getVets() throws InvalidResponseException {
        ApiResponse<VeterinariansItem[]> response = caller.executeRequest(getRequest(), Method.GET, VeterinariansItem[].class);
        return response.getContent();
    }

    //Add New Vet Details
    public VeterinariansItem createdVet(VeterinariansItem response) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(response).withHeader("Content-Type", "application/json");
        ApiResponse<VeterinariansItem> response1 = caller.executeRequest(request, Method.POST, VeterinariansItem.class);
        return response1.getContent();
    }
}

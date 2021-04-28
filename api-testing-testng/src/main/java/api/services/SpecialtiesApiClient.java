package api.services;

import api.common.ApiClient;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import api.services.data.Specialties;
import api.services.data.SpecialtiesItem;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class SpecialtiesApiClient extends ApiClient {


    public SpecialtiesApiClient(String baseUrl, String basePath) {
        super(baseUrl, basePath);
        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }
    //Verify status code
    public Integer getSpecialtiesStatus() throws InvalidResponseException {

        ApiResponse response = caller.executeRequest(getRequest(), Method.GET, Specialties.class);
        return response.getHttpStatusCode();
    }
    //GET Specialties details
    public SpecialtiesItem[] getSpecialties() throws InvalidResponseException {
        ApiResponse<SpecialtiesItem[]> response = caller.executeRequest(getRequest(), Method.GET, SpecialtiesItem[].class);
        return response.getContent();
    }

}

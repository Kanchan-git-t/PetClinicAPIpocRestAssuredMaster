import api.common.exception.InvalidResponseException;
import api.services.PetTypesApiClient;
import api.services.SpecialtiesApiClient;
import api.services.VetApiClient;
import api.services.data.PetTypeItem;
import api.services.data.SpecialtiesItem;
import api.services.data.VeterinariansItem;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PetClinicApiTest {
    static String apiUrl;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test()
    @Description("Test Description:Pet Type Status Code Verification.  API response 200 and fields values as id and names(pet types).")
    public void getPetType_StatusCodeVerification() throws InvalidResponseException {
        PetTypesApiClient client = new PetTypesApiClient(apiUrl, "/api/pettypes");
        int statusCode = client.getPetStatus();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(statusCode).isEqualTo(200);
        softly.assertAll();
    }

   /* @Test
    @Description("Test Description:Pet Type Response Header Verification")
    public void getPetType_HeaderVerification() throws InvalidResponseException {
        PetTypesApiClient client = new PetTypesApiClient(apiUrl, "/api/pettypes");
        Headers contentType = client.getHeader("Content-Type");
        Headers date1 = client.getHeader("date");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(contentType ).isNotNull() ;
        softly.assertThat(date1).isNotNull();
        softly.assertAll();
    }*/

    @Test
    @Description("Test Description:Pet Type get details verification")
    public void getPetType_ShouldReturnAllResults() throws InvalidResponseException {
        PetTypesApiClient client = new PetTypesApiClient(apiUrl, "api/pettypes");
        PetTypeItem[] getPet = client.getPetTypes();

        SoftAssertions softly = new SoftAssertions();
        System.out.println("The value at index 1" + getPet[0]);
        softly.assertThat(getPet[2].getId()).as("ID's should be unique").isNotSameAs(getPet[3].getId());
        softly.assertThat(getPet[0].getName()).as("the name is test:").isEqualTo("dog");
        softly.assertAll();

    }


   @Test
    @Description("Test Description:Add new pettype using property file and hardcoding value.Post pet type rest controller is used to add pet type details.")
    public void createPetType_checkId_ShouldReturnNewProduct () throws InvalidResponseException, IOException {

        Properties pro = new Properties();
        FileInputStream file = new FileInputStream("src/test/java/data/PostPetTypeData.properties");
        pro.load(file);
        PetTypesApiClient client = new PetTypesApiClient(apiUrl, "/api/pettypes");

        pro.forEach((key, value) -> System.out.println(key + " : " + value));
        PetTypeItem createdProduct = client.createdPetType(PetTypeItem.builder()
                .name(pro.getProperty("name")).id(Integer.parseInt(pro.getProperty("id"))).build());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdProduct.getName()).as("Name should be matching the properties file").isEqualTo(pro.getProperty("name"));
        softly.assertThat(createdProduct.getId()).as("Id is different than the existing one's").isEqualTo(0);

        softly.assertAll();
    }
   @Test
    @Description("Test Description:Specialties Status Code Verification.Get all data for the  Speciality rest controller. Check for service availablity with status ID 200 and following fields id and names.")
    public void getSpecialties_StatusCode() throws InvalidResponseException {
        SpecialtiesApiClient client = new SpecialtiesApiClient(apiUrl, "/api/specialties");
        int statusCode = client.getSpecialtiesStatus();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(statusCode).isEqualTo(200);
        softly.assertAll();
    }

    //Get specialties details
    @Test
    @Description("Test Description:Specialties Get details Verification")
    public void getspecialties_DefaultLimit_ShouldReturn10Results() throws InvalidResponseException {
        SpecialtiesApiClient client = new SpecialtiesApiClient(apiUrl, "/api/specialties");
        SpecialtiesItem[] getproduct = client.getSpecialties();

        SoftAssertions softly = new SoftAssertions();
        System.out.println("The value at index 1" + getproduct[0]);
        softly.assertThat(getproduct[0].getId()).as("The Id is 1:").isEqualTo(1);
        softly.assertThat(getproduct[0].getName()).as("the speciality is radiology :  ").isEqualTo("radiology");
        softly.assertAll();

    }

    @Test
    @Description("Test Description:Vet Status Code Verification.  API response 200 and fields values as id and first name, last name, speciality.")
    public void getVet_StatusCodeVerification() throws InvalidResponseException {
        VetApiClient client = new VetApiClient(apiUrl, "/api/vets");
        int statusCode = client.getVetStatus();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(statusCode).isEqualTo(200);
        softly.assertAll();
    }

    @Test
    @Description("Test Description:Get all vet details verification")
    public void getVet_ShouldReturnAllResults() throws InvalidResponseException {
        VetApiClient client = new VetApiClient(apiUrl, "api/vets");
        VeterinariansItem[] getVet = client.getVets();

        SoftAssertions softly = new SoftAssertions();
        System.out.println("The value at index 1" + getVet[0]);
        softly.assertThat(getVet[2].getId()).as("ID's should be unique").isNotSameAs(getVet[3].getId());
        softly.assertThat(getVet[0].getId()).as("ID's should be 1").isEqualTo(1);
        softly.assertThat(getVet[0].getFirstName()).as("the first name is James:").isEqualTo("James");
        softly.assertThat(getVet[0].getLastName()).as("the first name is Bamboo:").isEqualTo("Bamboo");

        //softly.assertThat(getVet[0].getSpecialties().getId()).as("the specialty id is 2:").isEqualTo(2);
        //softly.assertThat(getVet[1].getSpecialties().get(2).getName()).as("the specialty name: surgery").isEqualTo("surgery");
        softly.assertAll();

    }
/*
    //Create Vet using property file and hardcoding value
    @Test
    @Description("Test Description:Add new pettype using property file and hardcoding value.Post pet type rest controller is used to add pet type details.")
    public void createVet_checkId_ShouldReturnNewProduct () throws InvalidResponseException,IOException,FileNotFoundException {

        Properties pro = new Properties();
        FileInputStream file = new FileInputStream("src/test/java/data/PostVetData.properties");
        pro.load(file);
        VetApiClient client = new VetApiClient(apiUrl, "/api/vets");
        SpecialtiesApiClient spclItem = new SpecialtiesApiClient(apiUrl, "/api/specialties");
        SpecialtiesItem[] getspecialties = spclItem.getSpecialties();

        pro.forEach((key, value) -> System.out.println(key + " : " + value));
        VeterinariansItem createdVet = client.createdVet(VeterinariansItem.builder()
                .id(Integer.parseInt(pro.getProperty("id")))
                .firstName(pro.getProperty("firstname"))
                .lastName(pro.getProperty("lastname"))
                //.specialties()
                .build());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdVet.getId()).as("Id is different than the existing one's").isEqualTo(0);
        softly.assertThat(createdVet.getFirstName()).as("First Name should be matching the properties file").isEqualTo(pro.getProperty("firstName"));
        softly.assertThat(createdVet.getLastName()).as("Last Name should be matching the properties file").isEqualTo(pro.getProperty("lastName"));

        softly.assertAll();
    }*/

}

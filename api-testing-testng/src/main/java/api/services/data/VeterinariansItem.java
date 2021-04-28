package api.services.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class VeterinariansItem{
	@Expose
	private String firstName;

	@Expose
	private String lastName;

	@Expose
	private List<SpecialtiesItem> specialties;

	@Expose
	private int id;

}
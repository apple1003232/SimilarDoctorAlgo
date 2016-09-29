package api;

public class Doctor {
	private final String id; // required, doctor's id
	private final String name; // required, doctor's name
	private String specialty; // required, doctor's specialty
	private String city; // required, where doctor locates
	private Double score; // optional, review score

	// in case there are more optional parameters, I use the Builder pattern
	// here
	public static class Builder {
		// Required parameters
		private final String id;
		private final String name;
		private String specialty;
		private String city;

		// Optional parameters
		private Double score = 0.0;

		public Builder(String id, String name, String specialty, String city) {
			this.id = id;
			this.name = name;
			this.specialty = specialty;
			this.city = city;
		}

		public Builder score(Double val) {
			score = val;
			return this;
		}

		public Doctor build() {
			return new Doctor(this);
		}

	}

	private Doctor(Builder builder) {
		id = builder.id;
		name = builder.name;
		specialty = builder.specialty;
		city = builder.city;
		score = builder.score;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public String getCity() {
		return city;
	}

	public Double getScore() {
		return score;
	}

}

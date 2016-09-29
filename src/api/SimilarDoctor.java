package api;

import java.util.*;

/**
 * A class for returning similar doctors given a specific doctor and a list of
 * doctors. Similar doctors are defined as doctors who have common specialties
 * and locations(city in my example). The similar doctors are sorted in a
 * prioritized order of their review scores, i.e. the doctor with higher review
 * score comes before the one with lower score.
 * 
 * @author Hongchen LI
 *
 */
public class SimilarDoctor {
	/**
	 * Given a specific doctor and a list of doctors, return the similar doctors
	 * @param doc a specific doctor assuming the doctor is valid
	 * @param doctors the list of doctors, assuming the list is not null or empty and there are no duplicate doctor records
	 * @return a list of similar doctors
	 */
	public List<Doctor> similarDoctor(Doctor doc, List<Doctor> doctors) {
		// generate couple of maps to improve the search efficiency
		Map<String, Doctor> idMap = toIdMap(doctors);
		Map<String, List<String>> specMap = toSpecMap(doctors);
		Map<String, List<String>> cityMap = toCityMap(doctors);
		
		// get the given doctor's specialty and city
		String id = doc.getId();
		String specialty = doc.getSpecialty();
		String city = doc.getCity();
		
		// get a list of doctors(id) who have common specialty and location
		List<String> sameSpecialtyIds = specMap.get(specialty);
		List<String> sameCityIds = cityMap.get(city);
		List<String> commonIds = getCommonId(sameSpecialtyIds, sameCityIds, id);
		
		// sort the doctors in order of review scores
		List<Doctor> similarDoctors = idToDoctor(commonIds, idMap);
		Collections.sort(similarDoctors, new Comparator<Doctor>(){
			@Override
			public int compare(Doctor d1, Doctor d2) {
				return d2.getScore().compareTo(d1.getScore());
			}
		});
		
		return similarDoctors;
	}
	/**
	 * Return a list of doctors given a list of doctor ids and a map where key is id and value is doctor
	 * @param commonIds
	 * @param idMap
	 * @return a list of doctors
	 */
	public List<Doctor> idToDoctor(List<String> ids, Map<String, Doctor> idMap) {
		List<Doctor> result = new ArrayList<>();
		for (String id : ids) {
			result.add(idMap.get(id));
		}
		return result;
	}
	/**
	 * This is a algorithm to find the common id numbers given two lists of id numbers.
	 * Reasonably, assuming the number of all the doctors in a given city is smaller than 
	 * the number of the doctors of a specialty nationwide, I'm planning to apply the binary search
	 * algorithm. For each doctor id in a shorter list, use the binary search to see if it exists in
	 * the longer list. Hopefully, the time complexity would be O(nlogm), where m is the size of the 
	 * longer list and n is the size of the shorter list.
	 * Additionally, I'm planning to remove the original given doctor from the final result.
	 * @param sameSpecialtyIds a list of doctor's id in a given specialty
	 * @param sameCityIds a list of doctor's id in a given city
	 * @param id the original doctor's id I intend to remove from the final common id numbers
	 * @return a list of common id numbers
	 */
	public List<String> getCommonId(List<String> sameSpecialtyIds, List<String> sameCityIds, String id) {
		Collections.sort(sameSpecialtyIds);
		Collections.sort(sameCityIds);
		List<String> result = new ArrayList<>();
		for (String str : sameCityIds) {
			if (!str.equals(id) && Collections.binarySearch(sameSpecialtyIds, str) >= 0) {
				result.add(str);
			}
		}
		return result;
	}

	/**
	 * Given a list of doctors, return a map where key is the doctor's id and value is the doctor
	 * @param doctors
	 * @return a map where key is the doctor's id and value is the doctor
	 */
	public Map<String, Doctor> toIdMap(List<Doctor> doctors) {
		Map<String, Doctor> idMap = new HashMap<>();
		for (Doctor doc : doctors) {
			idMap.put(doc.getId(), doc);
		}
		return idMap;
	}
	/**
	 * Given a list of doctors, return a map where key is the specialty and value is a list of doctor ids
	 * @param doctors
	 * @return the map
	 */
	public Map<String, List<String> > toSpecMap(List<Doctor> doctors) {
		Map<String, List<String>> specMap = new HashMap<>();
		for (Doctor doc : doctors) {
			List<String> ids = specMap.get(doc.getSpecialty());
			if (ids == null) {
				specMap.put(doc.getSpecialty(), new ArrayList<String>(Arrays.asList(doc.getId())));
			} else {
				ids.add(doc.getId());
			}
		}
		return specMap;
	}
	/**
	 * Given a list of doctors, return a map where key is the city and value is a list of doctor ids
	 * @param doctors
	 * @return the map
	 */
	public Map<String, List<String>> toCityMap(List<Doctor> doctors) {
		Map<String, List<String>> cityMap = new HashMap<>();
		for (Doctor doc : doctors) {
			List<String> ids = cityMap.get(doc.getCity());
			if (ids == null) {
				cityMap.put(doc.getCity(), new ArrayList<String>(Arrays.asList(doc.getId())));
			} else {
				ids.add(doc.getId());
			}
		}
		return cityMap;
	}
}

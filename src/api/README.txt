Assuming we could have a list of all the doctors' information and the entire directory of doctors fits to the memory. We're trying to provide a class for the algorithm for returning a list of similar doctors in a prioritized order and associated unit tests.

Assuming a doctor has 4 required attributes, i.e. id, name, specialty and location, and 1 optional attribute, i.e. review score. We define similar doctors as doctors who have common specialties and locations. And we define the prioritized order as a order of doctors' review scores, i.e. the doctor with higher review score comes before the one with lower score. Then the basic idea is to first get a list of doctors who have common specialties and locations and then sort them in order of their review scores.

In more details, we sort the doctors in their specialties and their locations(city) respectively. More specifically, we get two maps where the (key, value) pair is (specialty, a list of doctor ids) and (city, a list of doctor ids) respectively. Then according to the specialty and the location of the given doctor, we would get a list of doctors who have common specialty and a list of doctors who have common location. Thus the common doctors among these two list would be the final result after sorting them in the order of their review scores. More specifically, to find the common doctors among the specialty list and the location list, we use the binary search algorithm. In my opinion, it's reasonable to assume that the number of all the doctors in a given city is smaller than the number of the doctors of a specialty nationwide. For each doctor id in a shorter list, use the binary search to see if it exists in the longer list and hopefully it would improve the search efficiency a little bit, since the time complexity is O(NlogM), where M is the size of the longer list and N is the size of the shorter list.

==================================================================================================================================
This repo includes four source files in .java format.

1. Doctor.java
	Define a doctor and the doctor's attributes

2. DoctorTest.java
	Junit test to verify the doctor's constructor and the builder pattern work well

3. SimilarDoctor.java
	Provide a algorithm to find the similar doctors, who have common in several attributes, in the order of their review scores, given a specific doctor and a list of all doctors

4. SimilarDoctorTest.java
	Junit test to verify the algorithm works well.

==================================================================================================================================
TO DO:

This is just a simple demo for the algorithm for finding the similar doctors. In practice, patients possibly would like to search similar doctors with more important attributes like what kind of insurance the doctor accepts and etc. Besides, it would be more reasonable to return similar doctors in a range of distance from the given doctor's location instead of just return the doctors locates in the same city. In this case, we need to add more required attributes, like insurance, to the doctor class and the number of similar doctors would be smaller due to more restrictions. Additionally, the location could be represented by longitude and latitude which is convenient to compute the distance instead of the city. Thus we could return the similar doctors near the given doctor in a customrized distance. Additionally, instead of defining the similar doctors by us, we could let the users to define the word "similar". We could improve the similar doctors recommendation algorithm by letting the users customize the important attributes of a doctor and what we need to do is just finding the doctors who have common in these attributes and sorting them in order.


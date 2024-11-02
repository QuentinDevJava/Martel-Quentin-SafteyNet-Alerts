package com.openclassroom.safetynet.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassroom.safetynet.constants.TypeOfData;
import com.openclassroom.safetynet.model.FirestationResponse;
import com.openclassroom.safetynet.repository.JsonRepository;
import com.openclassroom.safetynet.service.FirestationService;

@SpringBootTest

class FirestationServiceTest {

	@Autowired
	private FirestationService firestationService;

	@MockBean
	private JsonRepository repository;

	@Test
	void findAllByStationNumber() {

		List<FirestationResponse> firestations = Arrays.asList(new FirestationResponse("1509 Culver St", 1),
				new FirestationResponse("1650 Culver St", 1));
		when(repository.loadTypeOfData(TypeOfData.FIRESTATIONS)).thenReturn(Arrays.asList(firestations.get(0), firestations.get(1)));

		List<FirestationResponse> testFirestations = firestationService.findFireStationByStationNumber(1);

		assertThat(testFirestations).isEqualTo(firestations);

	}

	@Test
	void getFirestationByListStationNumber() {

		List<FirestationResponse> firestations = Arrays.asList(new FirestationResponse("1509 Culver St", 1),
				new FirestationResponse("1650 Culver St", 2));
		List<Integer> stationNumbers = Arrays.asList(1, 2);
		when(repository.loadTypeOfData(TypeOfData.FIRESTATIONS)).thenReturn(Arrays.asList(firestations.get(0), firestations.get(1)));

		List<FirestationResponse> testFirestations = firestationService.getFirestationByListStationNumber(stationNumbers);

		assertThat(testFirestations).isEqualTo(firestations);

	}

	@Test
	void getFirestationByAddress() {

		List<FirestationResponse> firestations = Arrays.asList(new FirestationResponse("1509 Culver St", 1),
				new FirestationResponse("1650 Culver St", 2));
		FirestationResponse firestationResult = new FirestationResponse("1509 Culver St", 1);
		when(repository.loadTypeOfData(TypeOfData.FIRESTATIONS)).thenReturn(Arrays.asList(firestations.get(0), firestations.get(1)));

		FirestationResponse testFirestations = firestationService.getFirestationByAddress("1509 Culver St");

		assertThat(testFirestations).isEqualTo(firestationResult);

	}

}

package com.openclassroom.safetynet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassroom.safetynet.constants.JsonPath;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class SearchControllerTest {

	@BeforeAll
	static void setup() throws IOException {
		Files.copy(new File(JsonPath.JSONFILEPATH).toPath(), new File(JsonPath.JSONTESTFILEPATH).toPath(), StandardCopyOption.REPLACE_EXISTING);
		System.setProperty("test.mode", "true");
	}

	@Autowired
	private MockMvc mockMvc;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	void getPersonsByStationNumberTest() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk());
	}

	@Test
	void getAllChildTest() throws Exception {
		mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk());
	}

	@Test
	void getPersonsPhoneNumbersByStationNumberTest() throws Exception {

		mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk());
	}

	@Test
	void getListOfPersonsInfoAndStationNumberByAddressTest() throws Exception {
		mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk());
	}

	@Test
	void getListOfPersonsInfoAndStationNumberByStationNumberTest() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=3,4")).andExpect(status().isOk());
	}

	@Test
	void getPersonsFullInfoWithLastNameTest() throws Exception {
		mockMvc.perform(get("/personInfolastName?lastName=Boyd")).andExpect(status().isOk());
	}

	@Test
	void getMailByCityTest() throws Exception {
		mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk());
	}

}

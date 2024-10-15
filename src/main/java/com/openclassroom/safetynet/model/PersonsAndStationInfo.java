package com.openclassroom.safetynet.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PersonsAndStationInfo(@NotNull @NotEmpty List<MedicalRecordInfo> persons, @NotBlank String station) {

}

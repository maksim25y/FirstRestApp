package ru.mud.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.mud.springcourse.FirstRestApp.dto.SensorDTO;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper mapper;
    @Autowired
    public MeasurementController(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody MeasurementDTO measurementDTO
    , BindingResult bindingResult){
        System.out.println(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

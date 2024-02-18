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
import ru.mud.springcourse.FirstRestApp.models.Measurement;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.services.MeasurementService;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper mapper;
    private final MeasurementService measurementService;
    @Autowired
    public MeasurementController(ModelMapper mapper, MeasurementService measurementService) {
        this.mapper = mapper;
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody MeasurementDTO measurementDTO
    , BindingResult bindingResult){
        System.out.println(measurementDTO);
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(mapToSensor(measurementDTO.getSensorDTO()));
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Sensor sensor = mapToSensor(measurementDTO.getSensorDTO());
        return mapper.map(measurementDTO,Measurement.class);
    }
    private Sensor mapToSensor(SensorDTO sensorDTO){
        return mapper.map(sensorDTO,Sensor.class);
    }
}

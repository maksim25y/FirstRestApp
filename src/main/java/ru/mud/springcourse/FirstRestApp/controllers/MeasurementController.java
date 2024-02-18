package ru.mud.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.mud.springcourse.FirstRestApp.dto.SensorDTO;
import ru.mud.springcourse.FirstRestApp.models.Measurement;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.services.MeasurementService;
import ru.mud.springcourse.FirstRestApp.util.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper mapper;
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    @Autowired
    public MeasurementController(ModelMapper mapper, MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.mapper = mapper;
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@Valid @RequestBody MeasurementDTO measurementDTO
    , BindingResult bindingResult){
        measurementValidator.validate(measurementDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(it->errorMessage.append(it.getField())
                    .append(" - ").append(it.getDefaultMessage()).append(";"));
            throw new MeasurementNotCreatedException(errorMessage.toString());
        }
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(mapToSensor(measurementDTO.getSensor()));
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MeasurementNotCreatedException.class)
    private ResponseEntity<MeasurementErrorResponse>handlerException(MeasurementNotCreatedException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Sensor sensor = mapToSensor(measurementDTO.getSensor());
        return mapper.map(measurementDTO,Measurement.class);
    }
    private Sensor mapToSensor(SensorDTO sensorDTO){
        return mapper.map(sensorDTO,Sensor.class);
    }
}

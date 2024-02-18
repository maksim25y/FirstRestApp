package ru.mud.springcourse.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.mud.springcourse.FirstRestApp.dto.SensorDTO;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.services.SensorService;
import ru.mud.springcourse.FirstRestApp.util.SensorErrorResponse;
import ru.mud.springcourse.FirstRestApp.util.SensorNotCreatedException;
import ru.mud.springcourse.FirstRestApp.util.SensorValidator;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper mapper;
    private final SensorValidator sensorValidator;
    @Autowired
    public SensorController(SensorService sensorService, ModelMapper mapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.mapper = mapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@Valid @RequestBody SensorDTO sensorDTO, BindingResult bindingResult){
        sensorValidator.validate(sensorDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(it->errorMessage.append(it.getField())
                    .append(" - ").append(it.getDefaultMessage()).append(";"));
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SensorNotCreatedException.class)
    private ResponseEntity<SensorErrorResponse>handlerException(SensorNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return mapper.map(sensorDTO,Sensor.class);
    }
}

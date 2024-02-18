package ru.mud.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mud.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.mud.springcourse.FirstRestApp.dto.SensorDTO;
import ru.mud.springcourse.FirstRestApp.models.Measurement;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MeasurementDTO measurement = (MeasurementDTO) o;
        if(measurement.getSensor()!=null){
            if(sensorService.findByName(measurement.getSensor().getName())==null){
                errors.rejectValue("sensor","","Name of sensor incorrect");
            }
        }
    }
}
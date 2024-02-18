package ru.mud.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mud.springcourse.FirstRestApp.dto.SensorDTO;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.services.SensorService;


@Component
@Configuration
public class SensorValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO sensor = (SensorDTO) o;
        if(sensorService.findByName(sensor.getName())!=null){
            errors.rejectValue("name","","Sensor with this value already exist");
        }
    }
}
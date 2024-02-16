package ru.mud.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.repositories.SensorRepository;
@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name) {
        return sensorRepository.findByName(name);
    }
}

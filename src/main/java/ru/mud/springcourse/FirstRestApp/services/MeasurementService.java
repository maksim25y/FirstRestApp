package ru.mud.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mud.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.mud.springcourse.FirstRestApp.models.Measurement;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
import ru.mud.springcourse.FirstRestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final SensorService sensorService;
    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(SensorService sensorService, MeasurementRepository measurementRepository) {
        this.sensorService = sensorService;
        this.measurementRepository = measurementRepository;
    }
    @Transactional
    public void save(Measurement measurement){
        System.out.println(measurement);
        Sensor sensor = measurement.getSensor();
        Sensor sensorByName = sensorService.findByName(sensor.getName());
        measurement.setSensor(sensorByName);
        measurement.setCreatedAt(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int findAllWithRain() {
        return measurementRepository.findAllByRaining(true).size();
    }
}

package ru.mud.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Sensor findByName(String name);
}

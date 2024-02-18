package ru.mud.springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Value should not be empty")
    @Min(value = -100,message = "Value must be in the range [-100,100] ")
    @Max(value = 100,message = "Value must be in the range [-100,100] ")
    private Double value;
    @NotNull(message = "Raining should not be empty")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor",referencedColumnName = "name")
    @NotNull
    private Sensor sensor;

    public Measurement(int id, Double value, Boolean raining, Sensor sensor) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Measurement(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, raining);
    }
}

package ru.mud.springcourse.FirstRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Value should not be empty")
    @Min(value = -100,message = "Value must be in the range [-100,100] ")
    @Max(value = -100,message = "Value must be in the range [-100,100] ")
    private double value;
    @NotEmpty(message = "Raining should not be empty")
    private boolean raining;
    @ManyToOne
    private Sensor sensor;

    public Measurement(int id, double value, boolean raining, Sensor sensor) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}

package ru.mud.springcourse.FirstRestApp.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import ru.mud.springcourse.FirstRestApp.models.Sensor;

public class MeasurementDTO {
    @NotEmpty(message = "Value should not be empty")
    @Min(value = -100,message = "Value must be in the range [-100,100] ")
    @Max(value = -100,message = "Value must be in the range [-100,100] ")
    private double value;
    @NotEmpty(message = "Raining should not be empty")
    private boolean raining;
    //@ManyToOne
    @JsonProperty("sensor")
    private SensorDTO sensor;

    public MeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {
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

    public SensorDTO getSensorDTO() {
        return sensor;
    }

    public void setSensorDTO(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}

package ru.mud.springcourse.FirstRestApp.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import ru.mud.springcourse.FirstRestApp.models.Sensor;
@Component
public class MeasurementDTO {
    @NotNull(message = "Value should not be empty")
    @Min(value = -100,message = "Value must be in the range [-100,100] ")
    @Max(value = 100,message = "Value must be in the range [-100,100] ")
    private Double value;
    @NotNull(message = "Raining should not be empty")
    private Boolean raining;
    @JsonProperty("sensor")
    @NotNull(message = "Sensor cannot be empty")
    private SensorDTO sensor;

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {
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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
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

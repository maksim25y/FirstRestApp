package ru.mud.springcourse.FirstRestApp.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(min=3,max = 30,message = "Name should be from 3-30")
    @NotEmpty(message = "Name of sensor should not be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

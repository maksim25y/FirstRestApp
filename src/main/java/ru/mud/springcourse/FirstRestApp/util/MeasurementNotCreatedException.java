package ru.mud.springcourse.FirstRestApp.util;

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}

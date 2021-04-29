package io.teamway.testproject.teamwayresttest.domain;

import java.time.LocalDateTime;
import java.util.Arrays;

public enum Shift {
    NIGHT(0,8), MORNING(9,16), EVENING(17,24);

    private Integer start;
    private Integer end;

    Shift(Integer start, Integer end)
    {
        this.start = start;
        this.end = end;
    }

    public static Shift of(LocalDateTime dateTime)
    {
        return Arrays.stream(Shift.values()).filter(s -> dateTime.getHour() >= s.start && dateTime.getHour() <= s.end).findFirst().get();
    }
}

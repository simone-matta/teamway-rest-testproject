package io.teamway.testproject.teamwayresttest.domain;


import org.junit.Test;

import java.time.LocalDateTime;

import static io.teamway.testproject.teamwayresttest.domain.Shift.*;
import static org.junit.Assert.assertEquals;

public class ShiftTest {

    @Test
    public void shouldReturnNight()
    {
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 00, 00)), NIGHT);
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 4, 30)), NIGHT);
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 8, 59)), NIGHT);
    }
    @Test
    public void shouldReturnMorning()
    {
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 9, 00)), MORNING);
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 13, 30)), MORNING);
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 16, 59)), MORNING);
    }
    @Test
    public void shouldReturnEvening()
    {
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 17, 00)), EVENING);
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 21, 30)), EVENING);
        assertEquals(Shift.of(LocalDateTime.of(2021, 04, 30, 23, 59)), EVENING);
    }
}
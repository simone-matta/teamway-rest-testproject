package io.teamway.testproject.teamwayresttest.controller;

import io.teamway.testproject.teamwayresttest.entity.TimeSchedule;
import io.teamway.testproject.teamwayresttest.service.TimeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("timebook")
public class TimeScheduleController {

    @Autowired
    TimeScheduleService service;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public TimeSchedule insert( @RequestParam("localDateTime")
                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime localDateTime, @RequestParam("workerId") Long workerId)
    {
        return service.insert(localDateTime, workerId);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public TimeSchedule update(@PathVariable("id") Long id, @RequestParam("localDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime localDateTime)
    {
        return service.update(id, localDateTime);
    }

    @RequestMapping(value = "/listByWorkerId", method = RequestMethod.GET)
    public List<TimeSchedule> listByWorkerId(@RequestParam("workerId") Long workerId)
    {
        return service.listByWorkerId(workerId);
    }

    @RequestMapping(value = "/listByDate", method = RequestMethod.GET)
    public List<TimeSchedule> listByDate(@RequestParam("localDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime localDateTime)
    {
        return service.listByDate(localDateTime);
    }
}

package io.teamway.testproject.teamwayresttest.service;

import io.teamway.testproject.teamwayresttest.entity.TimeSchedule;
import io.teamway.testproject.teamwayresttest.entity.Worker;
import io.teamway.testproject.teamwayresttest.repository.TimeScheduleRepository;
import io.teamway.testproject.teamwayresttest.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeScheduleService {
    @Autowired
    private TimeScheduleRepository timeScheduleRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public TimeSchedule insert(LocalDateTime localDateTime, Long workerId) {
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        checkExistingShift(worker.getId(), localDateTime);
        return timeScheduleRepository.save(new TimeSchedule(worker.getId(), localDateTime));
    }

    public TimeSchedule update(Long localDateTimeId, LocalDateTime newLocalDateTime)
    {
        TimeSchedule timeSchedule = timeScheduleRepository.findById(localDateTimeId).orElseThrow();
        checkExistingShift(timeSchedule.getWorkerId(), newLocalDateTime);
        timeSchedule.setDate(newLocalDateTime.toLocalDate());
        return timeScheduleRepository.save(timeSchedule);
    }

    public List<TimeSchedule> listByWorkerId(Long workerId)
    {
        Worker worker = workerRepository.findById(workerId).orElseThrow();
        return timeScheduleRepository.findByWorkerId(worker.getId());
    }

    public List<TimeSchedule> listByDate(LocalDateTime localDateTime)
    {
        return timeScheduleRepository.findByDate(localDateTime.toLocalDate());
    }

    private List<TimeSchedule> checkExistingShift(Long workerId, LocalDateTime localDateTime)
    {
        List<TimeSchedule> byWorkerIdAndDate = timeScheduleRepository.findByWorkerIdAndDate(workerId, localDateTime.toLocalDate());
        if (!byWorkerIdAndDate.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        return byWorkerIdAndDate;
    }
}

package io.teamway.testproject.teamwayresttest.service;

import io.teamway.testproject.teamwayresttest.entity.TimeSchedule;
import io.teamway.testproject.teamwayresttest.entity.Worker;
import io.teamway.testproject.teamwayresttest.repository.TimeScheduleRepository;
import io.teamway.testproject.teamwayresttest.repository.WorkerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimeScheduleServiceTest {

    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2021, 04,29, 0, 0);
    private static final Long WORKER_ID = 1L;
    private static final Long TIME_SCHEDULE_ID = 10L;
    private static Worker WORKER = new Worker();
    private static TimeSchedule TIME_SCHEDULE = new TimeSchedule();

    @Before
    public void setUp()
    {
        WORKER.setId(WORKER_ID);
        TIME_SCHEDULE.setId(TIME_SCHEDULE_ID);
        TIME_SCHEDULE.setWorkerId(WORKER_ID);
        when(timeScheduleRepository.findByWorkerIdAndDate(WORKER_ID, LOCAL_DATE_TIME.toLocalDate())).thenReturn(Collections.emptyList());
        when(workerRepository.findById(WORKER_ID)).thenReturn(Optional.of(WORKER));
        when(timeScheduleRepository.save(eq(TIME_SCHEDULE))).thenReturn(TIME_SCHEDULE);
        when(timeScheduleRepository.findById(TIME_SCHEDULE_ID)).thenReturn(Optional.of(TIME_SCHEDULE));
    }

    @Mock
    TimeScheduleRepository timeScheduleRepository;
    @Mock
    WorkerRepository workerRepository;

    @InjectMocks
    TimeScheduleService timeScheduleService;

    @Test(expected = ResponseStatusException.class)
    public void insertShouldThrowExceptionWhenWorkerHasShiftInSameDay() {
        when(timeScheduleRepository.findByWorkerIdAndDate(WORKER_ID, LOCAL_DATE_TIME.toLocalDate())).thenReturn(Arrays.asList(TIME_SCHEDULE));
        timeScheduleService.insert(LOCAL_DATE_TIME, WORKER_ID);
    }

    @Test
    public void shouldInsert()
    {
        timeScheduleService.insert(LOCAL_DATE_TIME, WORKER_ID);
        verify(timeScheduleRepository).save(any());
    }

    @Test
    public void shouldUpdate()
    {
        timeScheduleService.update(TIME_SCHEDULE_ID, LOCAL_DATE_TIME);
        verify(timeScheduleRepository).save(any());
    }

    @Test(expected = ResponseStatusException.class)
    public void updateShouldThrowExceptionWhenWorkerHasShiftInSameDay() {
        when(timeScheduleRepository.findByWorkerIdAndDate(WORKER_ID, LOCAL_DATE_TIME.toLocalDate())).thenReturn(Arrays.asList(TIME_SCHEDULE));
        timeScheduleService.update(TIME_SCHEDULE.getId(), LOCAL_DATE_TIME);
    }
}
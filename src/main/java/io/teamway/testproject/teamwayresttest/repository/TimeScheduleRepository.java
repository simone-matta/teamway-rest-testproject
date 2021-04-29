package io.teamway.testproject.teamwayresttest.repository;

import io.teamway.testproject.teamwayresttest.entity.TimeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeScheduleRepository extends JpaRepository<TimeSchedule, Long> {

    @Query("SELECT u FROM TimeSchedule u WHERE u.workerId = ?1 AND u.date = ?2")
    List<TimeSchedule> findByWorkerIdAndDate(Long workerId, LocalDate localDate);

    @Query("SELECT u FROM TimeSchedule u WHERE u.workerId = ?1")
    List<TimeSchedule> findByWorkerId(Long workerId);

    @Query("SELECT u FROM TimeSchedule u WHERE u.date = ?1")
    List<TimeSchedule> findByDate(LocalDate localDate);
}

package io.teamway.testproject.teamwayresttest.repository;

import io.teamway.testproject.teamwayresttest.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}

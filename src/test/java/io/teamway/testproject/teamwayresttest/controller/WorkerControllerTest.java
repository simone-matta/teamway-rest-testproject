package io.teamway.testproject.teamwayresttest.controller;


import io.teamway.testproject.teamwayresttest.entity.Worker;
import io.teamway.testproject.teamwayresttest.repository.WorkerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkerControllerTest {

    private final static Worker WORKER = new Worker();
    private final static Long WORKER_ID = 1L;
    private final static String WORKER_NAME = "Robert";
    private final static String WORKER_SURNAME = "Lewandowski";


    @Mock
    private WorkerRepository repository;

    @InjectMocks
    private WorkerController controller;

    @Captor
    ArgumentCaptor<Worker> workerArgumentCaptor;

    @Before
    public void setUp()
    {
        WORKER.setId(WORKER_ID);
        WORKER.setName(WORKER_NAME);
        WORKER.setSurname(WORKER_SURNAME);
        when(repository.findById(WORKER_ID)).thenReturn(Optional.of(WORKER));
    }

    @Test
    public void shouldUpdateNameAndSurname()
    {
        controller.update(WORKER_ID, "Mario", "Rossi");
        verify(repository).save(workerArgumentCaptor.capture());

        assertEquals(workerArgumentCaptor.getValue().getName(), "Mario");
        assertEquals(workerArgumentCaptor.getValue().getSurname(), "Rossi");
    }

    @Test
    public void shouldUpdateOnlyNameWhenSurnameIsEmpty()
    {
        controller.update(WORKER_ID, "Mario", "");
        verify(repository).save(workerArgumentCaptor.capture());

        assertEquals(workerArgumentCaptor.getValue().getName(), "Mario");
        assertEquals(workerArgumentCaptor.getValue().getSurname(), WORKER_SURNAME);
    }

    @Test
    public void shouldUpdateOnlySurnameWhenNameIsEmpty()
    {
        controller.update(WORKER_ID, "", "Rossi");
        verify(repository).save(workerArgumentCaptor.capture());

        assertEquals(workerArgumentCaptor.getValue().getName(), WORKER_NAME);
        assertEquals(workerArgumentCaptor.getValue().getSurname(), "Rossi");
    }
}
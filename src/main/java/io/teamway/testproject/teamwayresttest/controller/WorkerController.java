package io.teamway.testproject.teamwayresttest.controller;

import io.teamway.testproject.teamwayresttest.entity.Worker;
import io.teamway.testproject.teamwayresttest.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerRepository repository;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Worker insert(@RequestParam("name") String name, @RequestParam("surname") String surname)
    {
        return repository.save(new Worker(name, surname));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Worker update(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("surname") String surname)
    {
        Worker worker = repository.findById(id).orElseThrow();
        if (!name.isEmpty()){
            worker.setName(name);
        }
        if (!surname.isEmpty())
        {
            worker.setSurname(surname);
        }
        return repository.save(worker);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public List<Worker> listAll()
    {
        return repository.findAll();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public String remove(@RequestParam("id") Long id)
    {
        Worker worker = repository.findById(id).orElseThrow();
        repository.delete(worker);

        return "redirect:listAll";
    }







}

package com.bootcamp.springjpa.jpah2;

import com.bootcamp.springjpa.jpah2.Model.Customer;
import com.bootcamp.springjpa.jpah2.Model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/save")
    public Greeting save(@RequestParam(value = "name") String name,@RequestParam(value = "lastname")String lastname) {

        repository.save(new Customer(name,lastname));

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/find")
    public Greeting find(@RequestParam(value = "id") String id) {

        long longid =Long.parseLong(id);
        Customer customer = repository.findById(longid);
        return new Greeting(customer.getId(), String.format(template, customer.getFirstName()));
    }

}

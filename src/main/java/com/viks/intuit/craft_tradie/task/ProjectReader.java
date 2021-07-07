package com.viks.intuit.craft_tradie.task;

import com.viks.intuit.craft_tradie.dao.CustomerRepository;
import com.viks.intuit.craft_tradie.dao.ProjectRepository;
import com.viks.intuit.craft_tradie.entity.Customer;
import com.viks.intuit.craft_tradie.entity.Project;
import com.viks.intuit.craft_tradie.model.CustomerTask;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class ProjectReader implements ItemReader<CustomerTask> {
    LinkedList<CustomerTask> customerTasks = new LinkedList<>();

    {
        customerTasks.add(new CustomerTask());
    }

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public CustomerTask read() throws Exception{

        List<Project> projects = projectRepository.findAll();
        projects.stream().forEach(x-> System.out.println(x.getId() + " " +x.getCustomer().getCustomerName()));
        System.out.println(">>>>>>>>>>>> IN reader");
        return customerTasks.poll();
    }
}

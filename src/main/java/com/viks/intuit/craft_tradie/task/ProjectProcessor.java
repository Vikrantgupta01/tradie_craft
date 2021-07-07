package com.viks.intuit.craft_tradie.task;

import com.viks.intuit.craft_tradie.model.CustomerTask;
import org.springframework.batch.item.ItemProcessor;

public class ProjectProcessor implements ItemProcessor<CustomerTask, CustomerTask> {

    @Override
    public CustomerTask process(CustomerTask customerTask) throws Exception{
        System.out.println(">>>>>>>>>>>> IN processor");

        return customerTask;
    }
}

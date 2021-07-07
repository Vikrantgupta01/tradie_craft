package com.viks.intuit.craft_tradie.task;

import com.viks.intuit.craft_tradie.model.CustomerTask;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ProjectWriter implements ItemWriter<CustomerTask> {

    @Override
    public void write(List<? extends CustomerTask> list) throws Exception {
        System.out.println(">>>>>>>>>>>> IN writer");
    }
}

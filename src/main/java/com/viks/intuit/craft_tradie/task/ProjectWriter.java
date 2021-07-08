package com.viks.intuit.craft_tradie.task;

import com.viks.intuit.craft_tradie.dao.ProjectRepository;
import com.viks.intuit.craft_tradie.entity.Project;
import com.viks.intuit.craft_tradie.entity.ProjectBid;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProjectWriter implements ItemWriter<ProjectBid> {

    private ProjectRepository projectRepository;
    @Override
    public void write(List<? extends ProjectBid> list) throws Exception {
        System.out.println(">>>>>>>>>>>> IN writer winer is "+list.get(0));
        Project project = projectRepository.findById(1L).get();
        System.out.println(project.getWinnerBidId());
    }
}

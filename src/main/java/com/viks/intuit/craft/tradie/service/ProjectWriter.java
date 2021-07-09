package com.viks.intuit.craft.tradie.service;

import com.viks.intuit.craft.tradie.dao.ProjectRepository;
import com.viks.intuit.craft.tradie.entity.Project;
import com.viks.intuit.craft.tradie.entity.ProjectBid;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProjectWriter implements ItemWriter<ProjectBid> {

    private final ProjectRepository projectRepository;

    @Override
    public void write(final List<? extends ProjectBid> list) {
        System.out.println(">>>>>>>>>>>> IN writer winer is " + list.get(0));
        final Project project = this.projectRepository.findById(1L).get();
        System.out.println(project.getWinnerBidId());
    }
}

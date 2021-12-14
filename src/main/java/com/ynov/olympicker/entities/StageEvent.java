package com.ynov.olympicker.entities;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("E")
public class StageEvent extends Event {

    @OneToMany(mappedBy = "event")
    private List<Stage> stages = new ArrayList<>();


//    public Date getStartDate() {
//        return getStages().get(0).getDate();
//    }

//    public Date getEndDate() {
//        return getStages().get(stages.size() - 1).getDate();
//    }

    public List<Stage> getStages() {
        return this.stages.stream().sorted(Comparator.comparing(Stage::getDate)).collect(Collectors.toList());
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public void addStage(Stage stage) {
        this.stages.add(stage);
    }
}

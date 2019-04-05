package com.wts.dag.scheduler;

import java.util.List;

public class Task implements Executor{
    private Long id;
    private String name;
    private int state;

    public Task(Long id, String name, int state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public boolean execute() {
        System.out.println("Task id: [" + id + "], " + "task name: [" + name +"] is running");
        state = 1;
        return true;
    }

    public boolean hasExecuted() {
        return state == 1;
    }
}

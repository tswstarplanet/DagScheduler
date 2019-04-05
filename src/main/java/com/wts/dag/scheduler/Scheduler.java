package com.wts.dag.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Scheduler {
    public void schedule(Digraph digraph) {
        while (true) {
            List<Task> todo = new ArrayList<Task>();
            for (Task task : digraph.getTasks()) {
                if (!task.hasExecuted()) {
                    Set<Task> prevs = digraph.getMap().get(task);
                    if (prevs != null && !prevs.isEmpty()) {
                        boolean toAdd = true;
                        for (Task task1 : prevs) {
                            if (!task1.hasExecuted()) {
                                toAdd = false;
                                break;
                            }
                        }
                        if (toAdd) {
                            todo.add(task);
                        }
                    } else {
                        todo.add(task);
                    }
                }
            }
            if (!todo.isEmpty()) {
                for (Task task : todo) {
                    if (!task.execute()) {
                        throw new RuntimeException();
                    }
                }
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph();
        Task task1 = new Task(1L, "task1", 0);
        Task task2 = new Task(2L, "task2", 0);
        Task task3 = new Task(3L, "task3", 0);
        Task task4 = new Task(4L, "task4", 0);
        Task task5 = new Task(5L, "task5", 0);
        Task task6 = new Task(6L, "task6", 0);
        digraph.addTask(task1);
        digraph.addTask(task2);
        digraph.addTask(task3);
        digraph.addTask(task4);
        digraph.addTask(task5);
        digraph.addTask(task6);
        digraph.addEdge(task1, task2);
        digraph.addEdge(task1, task5);
        digraph.addEdge(task6, task2);
        digraph.addEdge(task2, task3);
        digraph.addEdge(task2, task4);
        Scheduler scheduler = new Scheduler();
        scheduler.schedule(digraph);
    }
}

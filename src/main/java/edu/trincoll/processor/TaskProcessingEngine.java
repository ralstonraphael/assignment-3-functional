package edu.trincoll.processor;

import edu.trincoll.functional.TaskPredicate;
import edu.trincoll.functional.TaskProcessor;
import edu.trincoll.functional.TaskTransformer;
import edu.trincoll.model.Task;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class TaskProcessingEngine {

    // TODO: Implement pipeline processing using Function composition
    public List<Task> processPipeline(
            List<Task> tasks,
            List<Function<List<Task>, List<Task>>> operations) {
        List<Task> result = tasks;
        for (Function<List<Task>, List<Task>> op : operations) {
            result = op.apply(result);
        }
        return result;
    }

    // TODO: Implement using Supplier for lazy evaluation
    public Task getOrCreateDefault(Optional<Task> taskOpt, Supplier<Task> defaultSupplier) {
        return taskOpt.orElseGet(defaultSupplier);
    }

    // TODO: Implement using Consumer for side effects
    public void processTasksWithSideEffects(
            List<Task> tasks,
            Consumer<Task> sideEffect) {
        tasks.forEach(sideEffect);
    }

    // TODO: Implement using BiFunction
    public Task mergeTasks(Task task1, Task task2, BiFunction<Task, Task, Task> merger) {
        return merger.apply(task1, task2);
    }

    // TODO: Implement using UnaryOperator
    public List<Task> transformAll(List<Task> tasks, UnaryOperator<Task> transformer) {
        return tasks.stream().map(transformer).toList();
    }

    // TODO: Implement using custom functional interfaces
    public List<Task> filterAndTransform(
            List<Task> tasks,
            TaskPredicate filter,
            TaskTransformer transformer) {
        return tasks.stream()
                .filter(filter)
                .map(transformer)
                .toList();
    }

    // TODO: Implement batch processing with TaskProcessor
    public void batchProcess(
            List<Task> tasks,
            int batchSize,
            TaskProcessor processor) {
        for (int i = 0; i < tasks.size(); i += batchSize) {
            int end = Math.min(i + batchSize, tasks.size());
            processor.process(tasks.subList(i, end));
        }
    }

    // TODO: Implement Optional chaining
    public Optional<String> getHighestPriorityTaskTitle(List<Task> tasks) {
        return tasks.stream()
                .max(Comparator.comparingInt(t -> t.priority().getWeight()))
                .map(Task::title);
    }

    // TODO: Implement stream generation using Stream.generate
    public Stream<Task> generateTaskStream(Supplier<Task> taskSupplier) {
        return Stream.generate(taskSupplier);
    }

    // TODO: Implement using Comparator composition
    public List<Task> sortByMultipleCriteria(
            List<Task> tasks,
            List<Comparator<Task>> comparators) {
        Comparator<Task> combined = comparators.stream()
                .reduce(Comparator::thenComparing)
                .orElse((a, b) -> 0);

        return tasks.stream()
                .sorted(combined)
                .toList();
    }
}

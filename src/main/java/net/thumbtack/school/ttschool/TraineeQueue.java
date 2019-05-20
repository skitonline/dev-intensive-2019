package net.thumbtack.school.ttschool;

import java.util.ArrayDeque;
import java.util.Queue;

public class TraineeQueue {
    private Queue<Trainee> queue;

    public TraineeQueue() {
        queue = new ArrayDeque<>();
    }

    public void addTrainee(Trainee trainee){
        queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        if (queue.isEmpty())
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        return queue.remove();
    }
}

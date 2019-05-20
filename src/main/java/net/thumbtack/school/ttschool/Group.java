package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {
    private String name, room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
        trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.equals(""))
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.equals(""))
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void  addTrainee(Trainee trainee){
        trainees.add(trainee);
    }

    public void  removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.remove(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  removeTrainee(int index) throws TrainingException {
        if (index < 0 || index >= trainees.size())
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        trainees.remove(index);
    }

    public Trainee  getTraineeByFirstName(String firstName) throws TrainingException {
        Trainee result = null;
        for (Trainee tr : trainees)
            if (tr.getFirstName().equals(firstName)) {
                result = tr;
                break;
            }
        if (result == null)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return result;
    }

    public Trainee  getTraineeByFullName(String fullName) throws TrainingException {
        Trainee result = null;
        for (Trainee tr : trainees)
            if (tr.getFullName().equals(fullName)) {
                result = tr;
                break;
            }
        if (result == null)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return result;
    }

    public void  sortTraineeListByFirstNameAscendant(){
        Comparator<Trainee> comparator =
                (Trainee obj1, Trainee obj2) -> obj1.getFirstName().compareTo(obj2.getFirstName());
        Collections.sort(trainees, comparator);
    }

    public void  sortTraineeListByRatingDescendant(){
        Comparator<Trainee> comparator =
                (Trainee obj1, Trainee obj2) -> ((Integer)obj2.getRating()).compareTo(obj1.getRating());
        Collections.sort(trainees, comparator);
    }

    public void  reverseTraineeList(){
        Collections.reverse(trainees);
    }

    public void  rotateTraineeList(int positions){
        Collections.rotate(trainees, positions);
    }

    public List<Trainee>  getTraineesWithMaxRating() throws TrainingException {
        if (trainees.isEmpty())
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        Comparator<Trainee> comparator =
                (Trainee obj1, Trainee obj2) -> ((Integer)obj1.getRating()).compareTo(obj2.getRating());
        int maxRating = Collections.max(trainees, comparator).getRating();
        List<Trainee> result = new ArrayList<>();
        for (Trainee tr : trainees)
            if (tr.getRating() == maxRating)
                result.add(tr);
        return result;
    }

    public boolean  hasDuplicates(){
        boolean result = false;
        for (int i = 0; i < trainees.size() && !result; i++)
            for (int j = i + 1; j < trainees.size() && !result; j++)
                if (trainees.get(i).equals(trainees.get(j)))
                    result = true;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }
}

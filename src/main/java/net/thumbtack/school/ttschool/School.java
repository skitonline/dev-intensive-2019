package net.thumbtack.school.ttschool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class School {
    private Set<Group> groups;
    private String name;
    private int year;

    public School(String name, int year) throws TrainingException {
        setName(name);
        this.year = year;
        groups = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.equals(""))
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void  addGroup(Group group) throws TrainingException {
        boolean find = false;
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext() && !find)
            if (iterator.next().getName().equals(group.getName()))
                find = true;
        if (find)
            throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        groups.add(group);
    }

    public void  removeGroup(Group group) throws TrainingException {
        if (!groups.remove(group))
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public void  removeGroup(String name) throws TrainingException {
        boolean find = false;
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext() && !find) {
            Group gr = iterator.next();
            if (gr.getName().equals(name)){
                groups.remove(gr);
                find = true;
            }
        }
        if (!find)
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public boolean  containsGroup(Group group){
        return groups.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return year == school.year &&
                Objects.equals(groups, school.groups) &&
                Objects.equals(name, school.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groups, name, year);
    }
}

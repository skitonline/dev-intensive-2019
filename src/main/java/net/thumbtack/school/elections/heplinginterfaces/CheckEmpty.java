package net.thumbtack.school.elections.heplinginterfaces;

public interface CheckEmpty {
    default boolean notContainsEmpty(String ...strings){
        for (String string : strings)
            if (string == null || string.isEmpty())
                return false;
        return true;
    }
}

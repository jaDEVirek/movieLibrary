package utils;

public enum Operations {
    BACK(0),
    SHOW_MOVIES(1),
    CLOSE(8),
    FIND_BY_ACTOR(3),
    FIND_BY_TITTLE(2),
    SORT_BY_DATE(4),
    SORY_BY_GENRE(5),
    SORT_BY_ALL(6),
    CREATE_XML(7);
    private int choice;

    Operations(int choice) {
        this.choice = choice;
    }
}

package entity;

public class DRAReadingLevel extends ReadingLevel {

    public DRAReadingLevel(Integer id, String grade, String level) {
        setId(id);
        setGrade(grade);
        setLevel(level);
    }

    @Override
    public String toString() {
        return "DRA reading level " + getLevel() + ", " + getGrade();
    }
}

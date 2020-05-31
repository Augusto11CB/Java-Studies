package entity;

public class LexileReadingLevel extends ReadingLevel {

    public LexileReadingLevel(Integer id, String grade, String level) {
        setId(id);
        setGrade(grade);
        setLevel(level);
    }

    @Override
    public String toString() {
        return "Lexile reading level " + getLevel() + "L, " + getGrade();
    }
}

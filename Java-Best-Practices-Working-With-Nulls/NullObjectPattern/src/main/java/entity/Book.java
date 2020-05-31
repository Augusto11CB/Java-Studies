package entity;

public class Book {
    private Integer id;

    private Integer authorId;

    private ReadingLevel readingLevel;

    private String title;

    public Book(Integer id, Integer authorId, ReadingLevel readingLevel, String title) {
        this.id = id;
        this.authorId = authorId;
        this.readingLevel = readingLevel;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public ReadingLevel getReadingLevel() {
        return readingLevel != null
                ? readingLevel
                : NullReadingLevel.getInstance();
    }

    public void setReadingLevel(ReadingLevel readingLevel) {
        this.readingLevel = readingLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", readingLevel=" + readingLevel +
                ", title='" + title + '\'' +
                '}';
    }
}

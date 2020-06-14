package aug.bueno.model;

import aug.bueno.annotation.Column;
import aug.bueno.annotation.PrimaryKey;
import lombok.ToString;

@ToString
public class Person {

    @PrimaryKey
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

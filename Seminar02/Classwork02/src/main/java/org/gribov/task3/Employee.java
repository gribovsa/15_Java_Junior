package org.gribov.task3;

import java.util.UUID;

@Entity
@Table(name = "users")
public class Employee {

    @Column(name = "id", primaryKey = true) //указывается на принадлежность к колонке и на принадлежность к уникальному полю
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employee(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

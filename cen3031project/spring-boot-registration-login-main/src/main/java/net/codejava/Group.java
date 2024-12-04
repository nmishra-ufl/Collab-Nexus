package net.codejava;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usergroup")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String type;

    @Column(length = 500)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "user_group_mapping", // Join table name
            joinColumns = @JoinColumn(name = "group_id"), // Foreign key for Group
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key for User
    )
    private Set<User> users = new HashSet<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }

}

/*


    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
 */
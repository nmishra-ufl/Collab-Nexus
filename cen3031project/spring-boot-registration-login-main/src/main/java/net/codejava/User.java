package net.codejava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;

	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 20)
	private String lastName;

	@Column(name = "skills", length = 255)
	private String skills;

	@Column(name = "experience", length = 255)
	private String experience;

	@Column(name = "availability", length = 50)
	private String availability;

	@Column(name = "bio", length = 500)
	private String bio;

	@ManyToMany(mappedBy = "users") // Reference the relationship defined in Group
	private Set<Group> groups = new HashSet<>();

	// Getters and Setters
	public String getSkills() { return skills; }
	public void setSkills(String skills) { this.skills = skills; }

	public String getExperience() { return experience; }
	public void setExperience(String experience) { this.experience = experience; }

	public String getAvailability() { return availability; }
	public void setAvailability(String availability) { this.availability = availability; }

	public String getBio() { return bio; }
	public void setBio(String bio) { this.bio = bio; }

	public Set<Group> getGroups() { return groups; }
	public void setGroups(Set<Group> groups) { this.groups = groups; }

}

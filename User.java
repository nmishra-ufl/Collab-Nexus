package net.codejava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	// Getters and Setters
	public String getSkills() { return skills; }
	public void setSkills(String skills) { this.skills = skills; }

	public String getExperience() { return experience; }
	public void setExperience(String experience) { this.experience = experience; }

	public String getAvailability() { return availability; }
	public void setAvailability(String availability) { this.availability = availability; }

	public String getBio() { return bio; }
	public void setBio(String bio) { this.bio = bio; }


	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}*/

}

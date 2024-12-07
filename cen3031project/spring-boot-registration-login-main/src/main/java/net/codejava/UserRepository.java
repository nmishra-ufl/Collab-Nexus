package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for performing database operations on the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by their email.
	 *
	 * @param email The email address of the user.
	 * @return The User object if found, otherwise null.
	 */
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
}

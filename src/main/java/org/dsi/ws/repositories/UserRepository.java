package org.dsi.ws.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dsi.ws.entities.User;

public class UserRepository {
	
	// Users database simulation
	private static List<User> users = new ArrayList<>();
	
	/*
	 * Static users
	 * */
	static {
		users.add(new User(1L, "HAYTHAM", "DAHRI", "CASABLANCA", "MOROCCO", "1997-08-26"));
		users.add(new User(2L, "IMRANE", "DAHRI", "CASABLANCA", "MOROCCO", "2015-06-15"));
	}
	
	/*
	 * Save a new user if id is null
	 * Update an existed user
	 */
	public User save(User user) {
		// Check if user id is null
		if( user.getId() == null ) {
			// Set new user id
			user.setId(users.get(users.size() - 1).getId() + 1);
			users.add(user);
		} else {
			user.setId(users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().getId());
			users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().setFirstName(user.getFirstName());
			users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().setLastName(user.getLastName());
			users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().setCity(user.getCity());
			users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().setCountry(user.getCountry());
			users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().setBirthDate(user.getBirthDate());
			users.stream().filter(tempUser -> tempUser.getId() == user.getId()).findFirst().get().setFirstName(user.getFirstName());
		}
		// Return persisted user
		return user;
	}
	
	/*
	 * Search a user by his id 
	 */
	public User findById(Long id) {
		for( User user : users ) {
			if( user.getId() == id ) {
				return user;
			}
		}
		return null;
	}
	
	/*
	 * Search all users in the database 
	 */
	public Collection<User> findAll() {
		return users;
	}
	
	/*
	 * Delete an existed user in the database
	 * return true if deleted
	 * return false if not exists
	 */
	public boolean deleteById(Long id) {
		return users.removeIf(user -> user.getId() == id);
	}


}

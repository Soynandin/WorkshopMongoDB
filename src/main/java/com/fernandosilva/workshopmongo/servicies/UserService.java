package com.fernandosilva.workshopmongo.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandosilva.workshopmongo.domain.User;
import com.fernandosilva.workshopmongo.dto.UserDTO;
import com.fernandosilva.workshopmongo.repository.UserRepository;
import com.fernandosilva.workshopmongo.servicies.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User user) {
		Optional<User> newUser = repo.findById(user.getId());
		updateData(newUser, user);
		return repo.save(newUser.orElseThrow());
	}
	
	private void updateData(Optional<User> newUser, User user) {
		newUser.orElseThrow().setName(user.getName());
		newUser.orElseThrow().setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}

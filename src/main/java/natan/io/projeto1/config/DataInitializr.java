package natan.io.projeto1.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import natan.io.projeto1.entity.User;
import natan.io.projeto1.repository.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		List<User> listaDeUsuarios = userRepository.findAll();
		
		if (listaDeUsuarios.isEmpty()) {
		
			createUser("Vitoria","vitora@gmail.com");
			createUser("Vinicius","vinicius@gmail.com");
			createUser("Aline","aline@gmail.com");
			
		}
		
		User user = userRepository.getOne(5L);
		//user.setId(null);
		
		System.out.println(user.getName());
		user.setName("Vinicius Leme 2");
		
		userRepository.save(user);

		
	}
	
	public void createUser(String name, String email) {
		
		User user = new User(email,name);
		userRepository.save(user);
	}

}

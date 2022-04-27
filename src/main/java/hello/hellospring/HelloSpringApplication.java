package hello.hellospring;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberJpaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}


	@Bean
	Hibernate5Module hibernate5Module(){
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		//hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5Module;

	}

	/*
	@Bean
	CommandLineRunner runner(MemberJpaService memberJpaService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Member>> typeReference = new TypeReference<List<Member>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/static/json/user.json");

			List<Member> members = mapper.readValue(inputStream, typeReference);

			memberJpaService.saveMembers(members);
			System.out.println("저장됨");
		};

	}*/
}

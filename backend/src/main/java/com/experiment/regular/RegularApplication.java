package com.experiment.regular;

import com.experiment.regular.entity.Instance;
import com.experiment.regular.entity.Person;
import com.experiment.regular.jackson.Schema;
import com.experiment.regular.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class RegularApplication {


	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(RegularApplication.class, args);
		PersonRepository personRepository = context.getBean(PersonRepository.class);
		populateDB(personRepository);
	}

	private static void populateDB(PersonRepository personRepository) throws IOException {
		//URL resource = RegularApplication.class.getResource("FilteredDataHuman.json");
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/FilteredDataHuman.json");
//		Resource resource = new ClassPathResource("static/FilteredDataHuman.json");
//		System.out.println(resource.exists());
		assert in != null;
		byte[] jsonData = in.readAllBytes();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Schema> data = mapper.readValue(jsonData, new TypeReference<List<Schema>>(){});
		System.out.println("reeded");
		data.forEach(schema -> {
			Person person = Person.builder().id(schema.get_id().get$oid())
					.timestamp(new Timestamp(Long.parseLong(schema.getTimestamp().get$date().get$numberLong())))
					.build();
			Set<Instance> instances = schema.getInstances().entrySet().stream().map(entry ->
				Instance.builder()
						.person(person)
						.instance_index(entry.getKey())
						.velX(entry.getValue().getVel_x())
						.velY(entry.getValue().getVel_y())
						.posX(entry.getValue().getPos_x())
						.posY(entry.getValue().getPos_y())
						.confidence(entry.getValue().getConfidence())
						.build()
			).collect(Collectors.toSet());
			person.setInstances(instances);
			personRepository.save(person);
		});
		System.out.println("saved");

	}

}

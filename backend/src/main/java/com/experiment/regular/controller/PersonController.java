package com.experiment.regular.controller;

import com.experiment.regular.dto.DataResponse;
import com.experiment.regular.dto.HeatMapResponse;
import com.experiment.regular.dto.ResponseDto;
import com.experiment.regular.entity.Person;
import com.experiment.regular.repository.InstanceRepository;
import com.experiment.regular.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/data")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InstanceRepository instanceRepository;

    @GetMapping(value = "/{xvalue}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDto<DataResponse>> getDataByTime(@PathVariable("xvalue") String xVal) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        PageRequest request = PageRequest.of(randomNum, 50);
        Page<Person> page = personRepository.findAll(request);
        List<Person> personList = page.getContent();
        System.out.println(personList.size());
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(xVal);

        List<DataResponse> responses = personList.stream().flatMap(person -> {
            return person.getInstances().stream().map(instance -> {
                        return String.valueOf(exp.getValue(instance));
                    })
                    .map(value -> DataResponse.builder().value(value).timestamp(person.getTimestamp()).build());
        }).toList();
        return new ResponseEntity<>(ResponseDto.<DataResponse>builder().response(responses).build(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/heatmap/{value}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDto<HeatMapResponse>> getHeatMap(@PathVariable("value") String val) {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 20 + 1);
        PageRequest request = PageRequest.of(randomNum, 50);
        Page<Person> page = personRepository.findAll(request);
        List<Person> personList = page.getContent();
        System.out.println(personList.size());
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(val);

        List<HeatMapResponse> responses = personList.stream().flatMap(person -> person.getInstances().stream()
                .map(instance -> HeatMapResponse.builder()
                        .value(String.valueOf(exp.getValue(instance)))
                        .x(instance.getPosX())
                        .y(instance.getPosY())
                        .build())).toList();
        return new ResponseEntity<>(ResponseDto.<HeatMapResponse>builder().response(responses).build(), HttpStatus.ACCEPTED);
    }
}

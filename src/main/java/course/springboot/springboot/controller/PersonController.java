package course.springboot.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import course.springboot.springboot.model.Person;
import course.springboot.springboot.repository.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/personregistration")
	public ModelAndView start() {
		ModelAndView modelAndView = new ModelAndView("registration/personregistration");
		modelAndView.addObject("personobj", new Person());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/saveperson")
	public ModelAndView save(Person person) {
		personRepository.save(person);
		// save and show data in the view
		ModelAndView andView = new ModelAndView("registration/personregistration");
		Iterable<Person> personsIt = personRepository.findAll();
		andView.addObject("persons", personsIt);
		andView.addObject("personobj", new Person());
		return andView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/personlist")
	public ModelAndView persons() {
		ModelAndView andView = new ModelAndView("registration/personregistration");
		Iterable<Person> personsIt = personRepository.findAll();
		andView.addObject("persons", personsIt);
		andView.addObject("personobj", new Person());
		return andView;
	}
	// EDIT

	@GetMapping("/editperson/{idperson}")
	public ModelAndView edit(@PathVariable("idperson") Long idperson) {
		
		Optional<Person> person = personRepository.findById(idperson);
		ModelAndView modelAndView = new ModelAndView("registration/personregistration");
		modelAndView.addObject("personobj", person.get());
		return modelAndView;
	}

}

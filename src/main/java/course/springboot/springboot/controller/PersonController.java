package course.springboot.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	// SAVEPERSON
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

	// PERSON LIST
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

	// DELETE
	@GetMapping("/removeperson/{idperson}")
	public ModelAndView delete(@PathVariable("idperson") Long idperson) {
		personRepository.deleteById(idperson);
		ModelAndView modelAndView = new ModelAndView("registration/personregistration");
		modelAndView.addObject("personobj", personRepository.findAll());
		modelAndView.addObject("personobj", new Person());
		return modelAndView;
	}
	// SEARCH
	@PostMapping("**/searchperson")
	public ModelAndView search(@RequestParam("namesearch") String namesearch) {
		ModelAndView modelAndView = new ModelAndView("registration/personregistration");
		modelAndView.addObject("persons", personRepository.findPersonByName(namesearch));
		modelAndView.addObject("personobj", new Person());
		return modelAndView;

	}

}

package course.springboot.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import course.springboot.springboot.model.Person;
import course.springboot.springboot.repository.PersonRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/personregistration")
	public String start() {
		return "registration/personregistration";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveperson")
	public String save(Person person) {
		personRepository.save(person);
		return "registration/personregistration";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/personlist")
	public ModelAndView persons() {
		ModelAndView andView = new ModelAndView("registration/personregistration");
		Iterable<Person> personsIt = personRepository.findAll();
		andView.addObject("persons", personsIt);
		return andView;
	}
	
}

package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.spring.dao.ContactDAO;
import net.codejava.spring.model.Contact;

@Controller
public class HomeComtroller {
	@Autowired
	private ContactDAO contactDAO;

	
//	  public ModelAndView listContact(ModelAndView model) {
//	      List<Contact> listContact = contactDAO.list();
//	      model.addObject("listContact", listContact);
//	      model.setViewName("home");
//	   
//	      return model; 
//		 
//	  }
//	  
	@RequestMapping(value = {"/","/viewemp"})
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("home1");
		List<Contact> listContact = contactDAO.list();
		mv.addObject("listContact", listContact);
		return mv;
	}

	@RequestMapping(value = "/viewemp/{pageid}")
	public ModelAndView Index1(@PathVariable int pageid) {		
		int total = 3;
		int page = pageid;
		if (pageid != 1) {
			pageid = (pageid - 1) * total + 1;
		}
		System.out.println(pageid);
		ModelAndView mv = new ModelAndView("home1");
		List<Contact> listContact = contactDAO.getContactByPage(pageid, total);
		mv.addObject("listContact", listContact);
		mv.addObject("page", page);
		mv.addObject("num", total);
		mv.addObject("total", contactDAO.list().size());

		return mv;
	}

	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}

	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		contactDAO.saveOrUpdate(contact);
		return new ModelAndView("redirect:/viewemp/1");
	}

	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.delete(contactId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}

//		@Autowired
//		EmpDao dao;// will inject dao from XML file
	//
//		/*
//		 * It displays a form to input data, here "command" is a reserved request
//		 * attribute which is used to display object data into form
//		 */
//		@RequestMapping("/empform")
//		public String showform(Model m) {
//			m.addAttribute("command", new Emp());
//			return "empform";
//		}
	//
//		/*
//		 * It saves object into database. The @ModelAttribute puts request data into
//		 * model object. You need to mention RequestMethod.POST method because default
//		 * request is GET
//		 */
//		@RequestMapping(value = "/save", method = RequestMethod.POST)
//		public String save(@ModelAttribute("emp") Emp emp) {
//			dao.save(emp);
//			return "redirect:/viewemp";// will redirect to viewemp request mapping
//		}
	//
//		/* It provides list of employees in model object */
//		@RequestMapping("/viewemp")
//		public String viewemp(Model m) {
//			List<Emp> list = dao.getEmployees();
//			m.addAttribute("list", list);
//			return "viewemp";
//		}
	//
//		/*
//		 * It displays object data into form for the given id. The @PathVariable puts
//		 * URL data into variable.
//		 */
//		@RequestMapping(value = "/editemp/{id}")
//		public String edit(@PathVariable int id, Model m) {
//			Emp emp = dao.getEmpById(id);
//			m.addAttribute("command", emp);
//			return "empeditform";
//		}
	//
//		/* It updates model object. */
//		@RequestMapping(value = "/editsave", method = RequestMethod.POST)
//		public String editsave(@ModelAttribute("emp") Emp emp) {
//			dao.update(emp);
//			return "redirect:/viewemp";
//		}
	//
//		/* It deletes record for the given id in URL and redirects to /viewemp */
//		@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
//		public String delete(@PathVariable int id) {
//			dao.delete(id);
//			return "redirect:/viewemp";
//		}

}

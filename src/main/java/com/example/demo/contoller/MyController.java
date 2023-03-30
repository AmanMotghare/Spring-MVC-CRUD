package com.example.demo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Employee;
import com.example.demo.service.EmpService;

@Controller
public class MyController {
	
	@Autowired
	EmpService es;
	
	@RequestMapping("/")
	public String index() {
		
		return "index";	
	}
	
	@RequestMapping("/form")
	public String myForm(Model m) {
		
		Employee emp = new Employee();
		m.addAttribute("empKey", emp);
		return "form";
	}
	
	@RequestMapping("/formSubmit")
	public String saveEmployee(@ModelAttribute("empKey") Employee e) {
		
		es.save(e);
		
		return "redirect:/viewAll";
	}
	
	@RequestMapping("/viewAll")
	public String getAllEmployee(Model m) {
		
		List<Employee> list = es.findAll();
		
		m.addAttribute("empList", list);
		
		return "view";
	}
	
	@RequestMapping("/update/{id}")
	public String updateEmployee(@PathVariable("id") int Id, Model m) {
		
		Employee e = es.getReferenceById(Id);
		System.out.println(e);
			
		m.addAttribute("updateEmpKey", e);
		
		return "updateForm";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") int Id) {
		es.deleteById(Id);
		return "redirect:/viewAll";
	}
	
	
	
	
//	@RequestMapping("/testEx")
//	public String testExpression(Model model ) {
//		
//		Employee emp = new Employee("Aman", "Motghare");
//		Employee emp1 = new Employee("Sanjana", "Balpande");
//		
//		model.addAttribute("keyObj", emp);
//		model.addAttribute("keyObj1", emp1);
//		
//		List<Employee> list =  es.findAll();
//		
//		for(Employee emp2 : list) {
//			
//			System.out.println(emp2);
//		}
//		
//		return "pageTest";
//	}
}


package com.usersystem.UserSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.usersystem.UserSystem.dto.UserDetailsDto;

import com.usersystem.UserSystem.entity.UserDetails;
import com.usersystem.UserSystem.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/userDetails")
public class UserDetailsController {

	@Autowired
	private UserRepository repo;

	@GetMapping({ "", "/" })
	public String showUser(Model model) {
		List<UserDetails> userDetails = repo.findAll();
		model.addAttribute("userDetails", userDetails);

		return "userDetails/index";
	}

	@GetMapping("/create")
	public String createUserPage(@Valid Model model) {
		UserDetailsDto udetailsDta = new UserDetailsDto();
		model.addAttribute("udetailsDta", udetailsDta);
		return "userDetails/createUser";
	}

	@PostMapping("/create")
	public String createUser(@Valid @ModelAttribute("detailsDto") UserDetailsDto detailsDto, BindingResult result) {

		UserDetails details = new UserDetails();
		details.setFname(detailsDto.getFname());
		details.setLname(detailsDto.getLname());
		details.setContactNumber(detailsDto.getContactNumber());
		details.setIsenabled(detailsDto.isIsenabled());
		repo.save(details);
		return "redirect:/userDetails";

	}

	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int userId,
			@RequestParam boolean isenabled) {

		if(isenabled == true)
		{
			UserDetails details = repo.findById(userId).get();
			model.addAttribute("details", details);
			UserDetailsDto udetailsDta = new UserDetailsDto();
			udetailsDta.setFname(details.getFname());
			udetailsDta.setLname(details.getLname());
			udetailsDta.setContactNumber(details.getContactNumber());
			udetailsDta.setIsenabled(details.isIsenabled());
			model.addAttribute("udetailsDta", udetailsDta);
			return "userDetails/editUser";
		}
		else
			return "redirect:/userDetails";

		
	}

	@PostMapping("/edit")
	public String editUser(Model model, @RequestParam int userId,@Valid @ModelAttribute UserDetailsDto detailsDto,
			BindingResult result, @RequestParam boolean isenabled) {

			UserDetails details = repo.findById(userId).get();
			model.addAttribute("details", details);
			details.setFname(detailsDto.getFname());
			details.setLname(detailsDto.getLname());
			details.setContactNumber(detailsDto.getContactNumber());
			details.setIsenabled(detailsDto.isIsenabled());
			repo.save(details);
			return "redirect:/userDetails";

		

	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam int userId) {

		UserDetails details = repo.findById(userId).get();
		repo.delete(details);
		return "redirect:/userDetails";

	}

	@GetMapping("/enable")
	public String EnableUser(@RequestParam int userId, Model model,@Valid @ModelAttribute UserDetailsDto detailsDto,
			BindingResult result) {

		UserDetails details = repo.findById(userId).get();
		model.addAttribute("details", details);
		details.setIsenabled(true);
		repo.save(details);
		return "redirect:/userDetails";

	}

	@GetMapping("/disable")
	public String DisableUser(@RequestParam int userId, Model model , @Valid @ModelAttribute UserDetailsDto detailsDto,
			BindingResult result) {

		UserDetails details = repo.findById(userId).get();
		model.addAttribute("details", details);
		details.setIsenabled(false);
		repo.save(details);
		return "redirect:/userDetails";

	}
}

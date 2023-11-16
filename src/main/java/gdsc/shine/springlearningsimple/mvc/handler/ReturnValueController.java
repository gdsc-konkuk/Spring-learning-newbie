package gdsc.shine.springlearningsimple.mvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gdsc.shine.springlearningsimple.mvc.domain.User;

@Controller
@RequestMapping("/return-value")
public class ReturnValueController {

	@GetMapping("/message")
	@ResponseBody
	public String string() {
		return "message";
	}

	@GetMapping("/users")
	@ResponseBody
	public User responseBodyForUser() {
		return new User("name", "email");
	}

	// public void responseEntity(@PathVariable Long id) {
	// 	return ResponseEntity.ok(new User("name", "email"));
	// }
	//
	// public void responseEntityFor400() {
	// 	return ResponseEntity.badRequest().build();
	// }
	//
	// public void thymeleaf() {
	// 	return "sample";
	// }
}

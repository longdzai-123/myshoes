package HoangLong.MyShoes.WEBcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	@GetMapping("/")
	public String index() {
		return "client/login";
	}
}

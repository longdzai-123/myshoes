package HoangLong.MyShoes.APIcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HoangLong.MyShoes.dto.PageDTO;
import HoangLong.MyShoes.dto.ResponseDTO;
import HoangLong.MyShoes.dto.UserDTO;
import HoangLong.MyShoes.dto.UserPrincipal;
import HoangLong.MyShoes.dto.searchdto.SearchUserDTO;
import HoangLong.MyShoes.dto.statistic.UserStatistic;
import HoangLong.MyShoes.security.JwtTokenProvider;
import HoangLong.MyShoes.service.UserService;
import HoangLong.MyShoes.utils.FileStore;

@RestController
@RequestMapping("/api")
public class UserAPIController {
	@Autowired
	UserService userService;
	@Autowired 
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	// login
	@PostMapping("/login")
	public String Login(@RequestParam(name = "username", required = true) String username,
		              @RequestParam(name = "password", required = true) String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			return jwtTokenProvider.createToken(username);
		} catch (AuthenticationException e) {
			return e.getMessage();
		}		
	}
	
	@GetMapping("/member/me")
	public UserDTO me() {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getById(userPrincipal.getId());
	}
	
	// register
	@PostMapping("/user/add")
	public ResponseDTO<UserDTO> add(@ModelAttribute @Valid UserDTO userDTO) {
		userDTO.setAvatar(FileStore.getFileName(userDTO.getFile(), "user-"));
		userService.create(userDTO);
		return ResponseDTO.<UserDTO>builder().status(200).data(userDTO).build();
	}
	
	@PutMapping("/member/user/update")
	public ResponseDTO<Void> update(@ModelAttribute @Valid UserDTO userDTO){
		if(userDTO.getFile() != null && !userDTO.getFile().isEmpty()) {
			userDTO.setAvatar(FileStore.getFileName(userDTO.getFile(), "user-"));
		}
		userService.update(userDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@DeleteMapping("/admin/user/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		userService.delete(id);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@GetMapping("/admin/user/{id}")
	public ResponseDTO<UserDTO> get(@PathVariable("id") int id){
		UserDTO userDTO = userService.getById(id);
		return ResponseDTO.<UserDTO>builder().status(200).data(userDTO).build();
	}
	
	@GetMapping("/admin/user/search")
	public ResponseDTO<PageDTO<UserDTO>> search(@RequestBody SearchUserDTO searchUserDTO){
		if(searchUserDTO.getPage() == null) {
			searchUserDTO.setPage(0);
		}
		if(searchUserDTO.getSize() == null) {
			searchUserDTO.setSize(10);
		}
		if(searchUserDTO.getKeyword() == null){
			searchUserDTO.setKeyword("");
		}
		PageDTO<UserDTO> pageDTO = userService.search(searchUserDTO);
		return ResponseDTO.<PageDTO<UserDTO>>builder().status(200).data(pageDTO).build();
		
	}
	
	@PutMapping("/member/user/password")
	public ResponseDTO<Void> updatePassword(@RequestBody UserDTO userDTO){
		userService.updatePassword(userDTO);
		return ResponseDTO.<Void>builder().status(200).build();
	}
	
	@GetMapping("/admin/user/statistic")
	public ResponseDTO<List<UserStatistic>> thongKeTheoThang(){
		List<UserStatistic> statistics = userService.thongKeTheoThang();
		return ResponseDTO.<List<UserStatistic>>builder().status(200).data(statistics).build();
	}
}

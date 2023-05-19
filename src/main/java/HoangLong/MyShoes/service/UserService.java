package HoangLong.MyShoes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import HoangLong.MyShoes.dto.PageDTO;
import HoangLong.MyShoes.dto.UserDTO;
import HoangLong.MyShoes.dto.UserPrincipal;
import HoangLong.MyShoes.dto.searchdto.SearchUserDTO;
import HoangLong.MyShoes.dto.statistic.UserStatistic;
import HoangLong.MyShoes.entity.User;
import HoangLong.MyShoes.repo.UserRepo;

@Service
@Transactional
public class UserService implements UserDetailsService{
    @Autowired
    UserRepo userRepo;
    
    public void create(UserDTO userDTO) {
    	User user = new ModelMapper().createTypeMap(UserDTO.class, User.class)
    			.addMappings(map->map.skip(User::setPassword))
    			.map(userDTO);
    	user.setPassword(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()));
    	
    	userRepo.save(user);
    	
    	userDTO.setId(user.getId()); 
    }
    
    public void update(UserDTO userDTO) {
    	User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
    	
    	user.setName(userDTO.getName());
    	user.setBirthdate(userDTO.getBirthdate());
    	user.setEmail(userDTO.getEmail());
    	user.setRoles(userDTO.getRoles());
    	
    	if(userDTO.getAvatar() != null) {
    		user.setAvatar(userDTO.getAvatar());
    	}
    	userRepo.save(user);
    }
    
    public void delete(int id) {
    	userRepo.deleteById(id);
    }
    
    public UserDTO getById(int id) {
    	User user = userRepo.findById(id).orElseThrow(NoResultException::new);
    	UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
    	return userDTO;
    }
    
    public PageDTO<UserDTO> search(SearchUserDTO searchUserDTO){
    	
    	Pageable pageable = PageRequest.of(searchUserDTO.getPage(), searchUserDTO.getSize());
    	
    	Page<User> pageUser = userRepo.searchByName("%"+ searchUserDTO.getKeyword() +"%", pageable); 
    	
    	PageDTO<UserDTO> pageDTO = new PageDTO<UserDTO>();	
    	
    	List<UserDTO> userDTOs = new ArrayList<UserDTO>();
    	
    	for (User user : pageUser.getContent()) {
			UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
			userDTOs.add(userDTO);
		}
    	pageDTO.setTotalPages(pageUser.getTotalPages());
    	pageDTO.setTotalElements(pageUser.getTotalElements());
    	pageDTO.setContents(userDTOs);
    	
    	return pageDTO;
    } 
    
    public void updatePassword(UserDTO userDTO) {
    	User user = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException::new);
    	if(userDTO.getPassword() != null) {
    		user.setPassword(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()));
    		userRepo.save(user);
    	}
    }
    
    public List<UserStatistic> thongKeTheoThang(){
    	List<Object[]> list =  userRepo.thongKeTheoMonth();
    	List<UserStatistic> statistics = new ArrayList<UserStatistic>();
    	for (Object[] obj : list) {
			UserStatistic statistic = new UserStatistic((long)obj[0],(int)obj[1]);
			statistics.add(statistic);
		}
    	return statistics;	
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("not found");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (String role : user.getRoles()) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			authorities.add(authority);
		}
		UserPrincipal userPrincipal = new UserPrincipal(user.getUsername(), user.getPassword(), authorities);
		userPrincipal.setId(user.getId());
		userPrincipal.setName(user.getName());
		userPrincipal.setRoles(user.getRoles());
		return userPrincipal;
	}
	
	
}

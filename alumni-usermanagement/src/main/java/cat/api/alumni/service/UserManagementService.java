package cat.api.alumni.service;


import cat.api.alumni.document.User;
import cat.api.alumni.dto.UserDto;
import cat.api.alumni.dto.UserEmailDto;
import cat.api.alumni.helper.DtoHelper;
import cat.api.alumni.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserManagementService implements IUserManagementService {

    @Autowired
    UserManagementRepository userRepository;


    public Mono<UserDto> addUser(Mono<UserEmailDto> userEmailDto) {
        Mono <User> user = userEmailDto.map(DtoHelper::convertToUserFromEmailDto);
        user.flatMap(userRepository::save).subscribe();

        return user.map(DtoHelper::convertToDto);

    }

    @Override
    public Mono<UserDto> getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid).map(DtoHelper::convertToDto);
    }

    public Mono<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(DtoHelper::convertToDto);
    }
}
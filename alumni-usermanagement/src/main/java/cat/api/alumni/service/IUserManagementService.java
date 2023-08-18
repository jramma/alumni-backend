package cat.api.alumni.service;

import cat.api.alumni.dto.UserDto;
import cat.api.alumni.dto.UserEmailDto;
import reactor.core.publisher.Mono;

public interface IUserManagementService {

    public Mono<UserDto> addUser(Mono<UserEmailDto> userEmailDto);

    public Mono<UserDto> getUserByUuid(String uuid);

    public Mono<UserDto> getUserByEmail(String email);

}

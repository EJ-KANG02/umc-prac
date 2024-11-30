package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserCommandService userCommandService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request) {
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

}

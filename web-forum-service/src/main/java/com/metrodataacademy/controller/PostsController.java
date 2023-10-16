package com.metrodataacademy.controller;

import com.metrodataacademy.domain.constant.ConstantVariable;
import com.metrodataacademy.domain.constant.ExceptionMessage;
import com.metrodataacademy.domain.dto.AuthorizationDto;
import com.metrodataacademy.domain.dto.request.ReqPostDto;
import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.exception.AuthorizationException;
import com.metrodataacademy.service.impl.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/threads")
public class PostsController {
    private final PostServiceImpl postsServiceImpl;

    @PostMapping("/comment")
    public ResponseEntity<ResBaseDto> insertCommentThreadsPosts(@RequestBody ReqPostDto reqCommentThreadsPostDto, HttpServletRequest servletRequest){
        AuthorizationDto authDto = (AuthorizationDto) servletRequest.getAttribute(ConstantVariable.USER);
//        String userId = authDto.getId();
//        boolean isRoleUser = authDto.getRoles().stream().anyMatch(item -> ConstantVariable.ROLE_USER.equals(item.getName()));
//        if (!isRoleUser)
//            throw new AuthorizationException(ExceptionMessage.ATTEMPT_TO_REQUEST_NON_ROLE_USER);
        return postsServiceImpl.insertCommentThreadsPosts(reqCommentThreadsPostDto, authDto);
    }

    @GetMapping("/comment")
    public ResponseEntity<ResBaseDto> getListComment(@RequestParam String threadsId, @RequestParam String page){
        return postsServiceImpl.getListPost(threadsId, page);
    }
}

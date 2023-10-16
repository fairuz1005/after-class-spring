package com.metrodataacademy.controller;

import com.metrodataacademy.domain.constant.ConstantVariable;
import com.metrodataacademy.domain.dto.AuthorizationDto;
import com.metrodataacademy.domain.dto.request.ReqCreateThreadsDto;
import com.metrodataacademy.domain.dto.request.ReqGetListThreads;
import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.exception.AuthorizationException;
import com.metrodataacademy.service.impl.ThreadsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/threads")
public class ThreadsController {

    private final ThreadsServiceImpl threadsService;

    /**
     * this controller used to insert data to table threads
     * @param threadsDto
     * @param servletRequest
     * @return Response Entity
     */

    @PostMapping("/create")
    public ResponseEntity<ResBaseDto> insertThread(@RequestBody ReqCreateThreadsDto threadsDto, HttpServletRequest servletRequest) {
        AuthorizationDto authDto = (AuthorizationDto) servletRequest.getAttribute(ConstantVariable.USER);
//        String userId = authDto.getId();
//        boolean isRoleUser = authDto.getRoles().stream().anyMatch(item -> ConstantVariable.ROLE_USER.equals(item.getName()));
//        if (!isRoleUser)
//            throw new AuthorizationException(ExceptionMessage.ATTEMPT_TO_REQUEST_NON_ROLE_USER);
        return threadsService.insertThreads(threadsDto, authDto);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getListThreads(@Valid @RequestBody ReqGetListThreads reqGetListThreads, HttpServletRequest httpServletRequest) {
//        AuthorizationDto authDto = (AuthorizationDto) httpServletRequest.getAttribute(ConstantVariable.USER);
//        boolean isRoleAdmin = authDto.getRoles().stream().anyMatch(item -> ConstantVariable.ROLE_ADMIN.equals(item.getName()));
//        if (!isRoleAdmin)
//            throw new AuthorizationException(ExceptionMessage.ATTEMPT_TO_REQUEST_NON_ROLE_ADMIN);
        return threadsService.getListThreads(reqGetListThreads) ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResBaseDto> deleteThread(@RequestParam String id, HttpServletRequest servletRequest) {
//        AuthorizationDto authDto = (AuthorizationDto) servletRequest.getAttribute(ConstantVariable.USER);
//        boolean isRoleAdmin = authDto.getRoles().stream().anyMatch(item -> ConstantVariable.ROLE_ADMIN.equals(item.getName()));
//        if (!isRoleAdmin){
//            throw new AuthorizationException(ExceptionMessage.ATTEMPT_TO_REQUEST_NON_ROLE_ADMIN);
//        }

        return threadsService.deleteThreads(id);
    }

    @GetMapping("")
    public ResponseEntity<ResBaseDto> getById(@RequestParam String id){
        return threadsService.getThreadsById(id);
    }
}

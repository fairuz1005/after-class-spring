package com.metrodataacademy.controller;

import com.metrodataacademy.domain.constant.ConstantVariable;
import com.metrodataacademy.domain.dto.AuthorizationDto;
import com.metrodataacademy.domain.dto.request.ReqCreateArticlesDto;

import com.metrodataacademy.domain.dto.request.ReqGetArticles;
import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.domain.entity.Articles;
import com.metrodataacademy.service.impl.ArticlesServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticlesController {

    private final ArticlesServiceImpl articlesService;

    @PostMapping("/list")
    public ResponseEntity<?> getArticlesList(@RequestBody ReqGetArticles articlesRequest){
        return articlesService.getAllArticleList(articlesRequest);
    }

    @GetMapping("")
    public ResponseEntity<ResBaseDto> getArticlesById(@RequestParam String id){
//        UUID uuid = UUID.fromString(id);
        return articlesService.getArticlesById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResBaseDto> deleteArticles(@RequestParam String id){
        return articlesService.deleteArticle(id);
    }

    /**
     *
     * @param reqCreateArticlesDto
     * @param servletRequest
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResBaseDto> insertArticle(@RequestBody ReqCreateArticlesDto reqCreateArticlesDto, HttpServletRequest servletRequest) {
//        AuthorizationDto authDto = (AuthorizationDto) servletRequest.getAttribute(ConstantVariable.USER);
//        boolean isRoleAdmin = authDto.getRoles().stream().anyMatch(item -> ConstantVariable.ROLE_ADMIN.equals(item.getName()));
//        if (!isRoleAdmin){
//            throw new AuthorizationException(ExceptionMessage.ATTEMPT_TO_REQUEST_NON_ROLE_ADMIN);
//        }

        return articlesService.insertArticle(reqCreateArticlesDto, servletRequest);
    }

    /**
     *
     * @param id
     * @param reqCreateArticlesDto
     * @param servletRequest
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<ResBaseDto> updateArticle(@RequestParam String id, @RequestBody ReqCreateArticlesDto reqCreateArticlesDto, HttpServletRequest servletRequest) {
        AuthorizationDto authDto = (AuthorizationDto) servletRequest.getAttribute(ConstantVariable.USER);
//        boolean isRoleAdmin = authDto.getRoles().stream().anyMatch(item -> ConstantVariable.ROLE_ADMIN.equals(item.getName()));
//        if (!isRoleAdmin){
//            throw new AuthorizationException(ExceptionMessage.ATTEMPT_TO_REQUEST_NON_ROLE_ADMIN);
//        }
        return articlesService.updateArticle(id, reqCreateArticlesDto, authDto);
    }
}

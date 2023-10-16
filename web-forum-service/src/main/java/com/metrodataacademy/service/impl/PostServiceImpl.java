package com.metrodataacademy.service.impl;

import com.metrodataacademy.domain.constant.ConstantVariable;
import com.metrodataacademy.domain.dto.AuthorizationDto;
import com.metrodataacademy.domain.dto.request.ReqPostDto;
import com.metrodataacademy.domain.dto.response.ResBaseDto;
import com.metrodataacademy.domain.dto.response.ResGetListPostDto;
import com.metrodataacademy.domain.entity.Articles;
import com.metrodataacademy.domain.entity.Post;
import com.metrodataacademy.domain.entity.StagingUser;
import com.metrodataacademy.domain.mapper.PostMapper;
import com.metrodataacademy.domain.mapper.StagingUserMapper;
import com.metrodataacademy.repository.PostRepository;
import com.metrodataacademy.repository.ThreadsRepository;
import com.metrodataacademy.service.intrf.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postsRepository;
    private final ThreadsRepository threadsRepository;
    private final ThreadsServiceImpl threadsService;
    private final PostMapper postMapper;
    private final StagingUserMapper stagingUserMapper;

    public ResponseEntity<ResBaseDto> insertCommentThreadsPosts(ReqPostDto reqCommentThreadsPostDto, AuthorizationDto authorizationDto){
        try{
            Post post = new Post();
            post.setContent(reqCommentThreadsPostDto.getContent());
            post.setThreads(threadsRepository.findById(reqCommentThreadsPostDto.getIdThreads()).get());
            post.setActive(true);

            StagingUser stagingUser = new StagingUser();
            stagingUser.setId(authorizationDto.getId());
            stagingUser.setName(authorizationDto.getName());

            post.setAuthor(stagingUser);
            postsRepository.save(post);

            ResGetListPostDto resGetListPostDto = postMapper.postToResGetListPostDto(post);
            resGetListPostDto.setAuthor(stagingUserMapper.stagingUserToResStagingUserDto(stagingUser));
            return new ResponseEntity<>(new ResBaseDto(resGetListPostDto,"Success"),HttpStatus.OK);
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(e.getStatusCode(), e.getReason());
        }
    }


    @Override
    public ResponseEntity<ResBaseDto> getListPost(String threadsId, String page) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page) -1,
                ConstantVariable.DATA_PER_PAGE);

        List<ResGetListPostDto> resGetListPostDtoList = new ArrayList<>();
        Page<Post> pageResult = postsRepository.findByThreads_Id(threadsId, pageable);
        pageResult.forEach(response -> {
            ResGetListPostDto resGetListPostDto = postMapper.postToResGetListPostDto(response);
            resGetListPostDtoList.add(resGetListPostDto);
        });

        return new ResponseEntity<>(new ResBaseDto(resGetListPostDtoList, ConstantVariable.SUCCESS), HttpStatus.OK);
    }
}






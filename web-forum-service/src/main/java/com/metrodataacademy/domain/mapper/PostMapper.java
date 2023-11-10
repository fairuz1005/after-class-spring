package com.metrodataacademy.domain.mapper;

import com.metrodataacademy.domain.dto.response.ResGetPostDto;
import com.metrodataacademy.domain.dto.response.ResStagingUserDto;
import com.metrodataacademy.domain.entity.Post;
import com.metrodataacademy.domain.entity.StagingUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "postId", source = "id")
    @Mapping(target = "threadsId", expression = "java(post.getThreads().getId())")
    ResGetPostDto postToResGetListPostDto(Post post);

    @AfterMapping
    default void mapStagingUserOfPostToResStagingUserDto(@MappingTarget ResGetPostDto resGetPostDto, Post post){
        StagingUser stagingUser = post.getAuthor();
        ResStagingUserDto resStagingUserDto = new ResStagingUserDto();

        resStagingUserDto.setName(stagingUser.getName());
        resStagingUserDto.setId(stagingUser.getId());

        resGetPostDto.setAuthor(resStagingUserDto);
    }
}

package com.metrodataacademy.domain.mapper;

import com.metrodataacademy.domain.dto.response.ResGetListPostDto;
import com.metrodataacademy.domain.entity.Post;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "postId", source = "id")
    @Mapping(target = "threadsId", expression = "java(post.getThreads().getId())")
    ResGetListPostDto postToResGetListPostDto(Post post);
}

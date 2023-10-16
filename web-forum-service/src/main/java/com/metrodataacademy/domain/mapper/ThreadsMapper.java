package com.metrodataacademy.domain.mapper;

import com.metrodataacademy.domain.dto.request.ReqCreateThreadsDto;
import com.metrodataacademy.domain.dto.response.ResGetListThreadsDto;
import com.metrodataacademy.domain.entity.Threads;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThreadsMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "totalViews", source = "counter")
    @Mapping(target = "totalPostComments", ignore = true)
    ResGetListThreadsDto threadsToResGetListThreads(Threads threads);

    Threads ReqCreateThreadsDtoToThreads(ReqCreateThreadsDto reqCreateThreadsDto);
}

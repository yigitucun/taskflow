package com.ali.taskflow.board.mapper;

import com.ali.taskflow.board.dto.requests.CreateBoardRequest;
import com.ali.taskflow.board.entity.Board;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board toEntity(CreateBoardRequest request);
}

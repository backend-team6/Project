package com.group.board.board.repository;

import java.util.List;

import com.group.board.board.domain.BoardDTO;
import com.group.board.board.dto.BoardWriteDTO;
import com.group.board.board.dto.UpdateDTO;

public interface BoardRepository {
	public List<BoardDTO> findAll();

	public BoardDTO findByBoardNo(int no);

	public void write(BoardWriteDTO boardWriteDTO);

	public void update(UpdateDTO updateDTO);

	public void incrementReadCount(int no);
}

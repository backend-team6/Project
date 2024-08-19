package com.group.board.board.service;

import java.util.List;

import com.group.board.board.domain.BoardDTO;
import com.group.board.board.dto.BoardWriteDTO;
import com.group.board.board.dto.UpdateDTO;
import com.group.board.board.repository.BoardRepository;
import com.group.board.board.repository.BoardRepositoryImpl;

public class BoardService {
	private static BoardService boardService = new BoardService();

	BoardRepository boardRepository;

	private BoardService() {
		this.boardRepository = BoardRepositoryImpl.getInstance();
	}

	public static BoardService getInstance() {
		return boardService;
	}

	public List<BoardDTO> getBoardList() {
		return boardRepository.findAll();
	}

	public BoardDTO getBoardInfo(int no) {
		return boardRepository.findByBoardNo(no);
	}

	public void incrementReadCount(int no) {
		boardRepository.incrementReadCount(no);
	}

	public void writeBoard(BoardWriteDTO boardWriteDTO) {
		boardRepository.write(boardWriteDTO);
	}

	public void update(UpdateDTO updateDTO) {
		boardRepository.update(updateDTO);
	}
}

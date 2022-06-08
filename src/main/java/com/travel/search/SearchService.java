package com.travel.search;

import com.travel.board.BoardRepository;
import com.travel.domain.Board;
import com.travel.domain.Festival;
import com.travel.festival.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final BoardRepository boardRepository;
    private final FestivalRepository festivalRepository;

    public List<Board> searchBoardLocal(String keyword) {
        return boardRepository.findByLocalContaining(keyword);
    }

    public List<Board> searchBoardContent(String keyword) {
        return boardRepository.findByContentContaining(keyword);
    }

    public List<Festival> searchFestivalLocal(String keyword) {
        return festivalRepository.findByLocalContaining(keyword);
    }

    public List<Festival> searchFestivalName(String keyword) {
        return festivalRepository.findByNameContaining(keyword);
    }
}

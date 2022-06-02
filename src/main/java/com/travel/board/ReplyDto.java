package com.travel.board;

import com.travel.domain.Reply;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@RequiredArgsConstructor
@ToString
public class ReplyDto {
    private final Reply reply;
    private List<Reply> rReplyList = new ArrayList<>();
}

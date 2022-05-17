package com.datasource;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.service.noticeboard.read.NoticeBoardReadService;
import com.datasource.service.noticeboard.write.NoticeBoardWriteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class NoticeBoardApplicationTests {

    @Autowired
    NoticeBoardWriteService freeBoardWriteService;

    @Autowired
    NoticeBoardWriteService gameBoardWriteService;

    @Autowired
    NoticeBoardReadService freeBoardDecoratorReadService;

    @Autowired
    NoticeBoardReadService gameBoardDecoratorReadService;

    @Test
    void 자유게시판_저장() {
        long seq = this.freeBoardWriteService.insertPosts(new Posts("freeMemberId_1", "freeMemberId_1 이건 본문 내용 입니다."));

        Assertions.assertTrue(seq > 0);
    }

    @Test
    void 게임게시판_저장() {
        long seq = this.gameBoardWriteService.insertPosts(new Posts("gameMemberId_1", "gameMemberId_1 이건 본문 내용 입니다."));

        Assertions.assertTrue(seq > 0);
    }

    @Test
    void 모든_게시판_저장() {
        this.자유게시판_저장();
        this.게임게시판_저장();
    }

    @Test
    void 자유게시판_조회() {
        Posts insertPosts = new Posts("freeMemberId_1", "freeMemberId_1 이건 본문 내용 입니다.");
        this.freeBoardWriteService.insertPosts(insertPosts);

        Posts findPosts = this.freeBoardDecoratorReadService.findPosts(insertPosts.getSeq());
        NoticeBoardControl findNoticeBoardControl = this.freeBoardDecoratorReadService.findNoticeBoardControl(findPosts.getSeq());

        Assertions.assertTrue(findPosts.getSeq() > 0 && findNoticeBoardControl.getSeq() > 0);
    }

    @Test
    void 게임게시판_조회() {
        Posts insertPosts = new Posts("gameMemberId_1", "gameMemberId_1 이건 본문 내용 입니다.");
        this.gameBoardWriteService.insertPosts(insertPosts);

        Posts findPosts = this.gameBoardDecoratorReadService.findPosts(insertPosts.getSeq());
        NoticeBoardControl findNoticeBoardControl = this.gameBoardDecoratorReadService.findNoticeBoardControl(findPosts.getSeq());

        Assertions.assertTrue(findPosts.getSeq() > 0 && findNoticeBoardControl.getSeq() > 0);
    }

    @Test
    void 자유게시판_유형_정보_전체_조회() {
        int insertIndex = 10;

        for(int i = 0; i < insertIndex; i++) {
            String nowIndex = String.valueOf(insertIndex + 1);
            this.freeBoardWriteService.insertPosts(new Posts("freeMemberId_" + nowIndex, "freeMemberId_" + nowIndex+ " 이건 본문 내용 입니다."));
        }

        List<NoticeBoardControl> noticeBoardControls = this.freeBoardDecoratorReadService.findNoticeBoardTypeControl();

        Assertions.assertEquals(insertIndex, noticeBoardControls.size());
    }

    @Test
    void 게임게시판_유형_정보_전체_조회() {
        int insertIndex = 10;

        for(int i = 0; i < insertIndex; i++) {
            String nowIndex = String.valueOf(insertIndex + 1);
            this.gameBoardWriteService.insertPosts(new Posts("gameMemberId_" + nowIndex, "gameMemberId_" + nowIndex+ " 이건 본문 내용 입니다."));
        }

        List<NoticeBoardControl> noticeBoardControls = this.gameBoardDecoratorReadService.findNoticeBoardTypeControl();

        Assertions.assertEquals(insertIndex, noticeBoardControls.size());
    }
}

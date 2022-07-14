package com.datasource;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.read.noticeBoard.FreeBoardControlReadMapper;
import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardControlWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.GameBoardWriteMapper;
import com.datasource.service.noticeboard.read.NoticeBoardMapperReadService;
import com.datasource.service.process.ServiceProcessExecuteImpl;
import com.datasource.service.process.noticeboard.NoticeBoardSaveProcessImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceProcessExecuteApplicationTests {
    @Autowired
    GameBoardWriteMapper gameBoardWriteMapper;

    @Autowired
    FreeBoardWriteMapper freeBoardWriteMapper;

    @Autowired
    FreeBoardControlWriteMapper freeBoardControlWriteMapper;

    @Autowired
    FreeBoardControlReadMapper freeBoardControlReadMapper;

    @Autowired
    NoticeBoardMapperReadService freeBoardDecoratorReadService;

    @Autowired
    ServiceProcessExecuteImpl serviceProcessExecuteImpl;

    @Test
    void serviceProcessExecute_게시물저장(){
        Posts insertFreeBoardPosts = new Posts("freeMemberId_1", "freeMemberId_1 이건 serviceProcessExecuteImpl 본문 내용 입니다.");
        this.serviceProcessExecuteImpl.masterTransactionalProcess(new NoticeBoardSaveProcessImpl(this.gameBoardWriteMapper, this.freeBoardWriteMapper)
                .execute(NoticeBoardTypeEnum.FREE_BOARD, insertFreeBoardPosts));

        Posts insertGameBoardPosts = new Posts("gameMemberId_1", "gameMemberId_1 이건 serviceProcessExecuteImpl 본문 내용 입니다.");
        this.serviceProcessExecuteImpl.masterTransactionalProcess(new NoticeBoardSaveProcessImpl(this.gameBoardWriteMapper, this.freeBoardWriteMapper)
                .execute(NoticeBoardTypeEnum.GAME_BOARD, insertGameBoardPosts));

        Assertions.assertTrue(insertFreeBoardPosts.getSeq() > 0 && insertGameBoardPosts.getSeq() > 0);
    }

    @Test
    void serviceProcessExecute_게시물저장_NoticeBoardWriteMapperFactory_생성자() {
        Posts insertFreeBoardPosts = new Posts("freeMemberId_1", "freeMemberId_1 이건 serviceProcessExecuteImpl 본문 내용 입니다.");
        this.serviceProcessExecuteImpl.masterTransactionalProcess(new NoticeBoardSaveProcessImpl()
                .execute(NoticeBoardTypeEnum.FREE_BOARD, insertFreeBoardPosts));

        Posts insertGameBoardPosts = new Posts("gameMemberId_1", "gameMemberId_1 이건 serviceProcessExecuteImpl 본문 내용 입니다.");
        this.serviceProcessExecuteImpl.masterTransactionalProcess(new NoticeBoardSaveProcessImpl()
                .execute(NoticeBoardTypeEnum.GAME_BOARD, insertGameBoardPosts));

        Assertions.assertTrue(insertFreeBoardPosts.getSeq() > 0 && insertGameBoardPosts.getSeq() > 0);
    }

    @Test
    void serviceProcessExecute_freeboard_control_게시물저장(){
        Posts insertFreeBoardControlPosts = new Posts("freeMemberId_1", "freeMemberId_1 이건 serviceProcessExecuteImpl 본문 내용 입니다.");
        this.serviceProcessExecuteImpl.masterTransactionalProcess(new NoticeBoardSaveProcessImpl(this.gameBoardWriteMapper, this.freeBoardControlWriteMapper)
                .execute(NoticeBoardTypeEnum.FREE_BOARD, insertFreeBoardControlPosts));

        NoticeBoardControl noticeBoardControl = this.freeBoardDecoratorReadService.findNoticeBoardControl(this.freeBoardControlReadMapper, insertFreeBoardControlPosts.getSeq());

        Assertions.assertTrue(insertFreeBoardControlPosts.getSeq() > 0 && noticeBoardControl.getSeq() > 0);
    }
}

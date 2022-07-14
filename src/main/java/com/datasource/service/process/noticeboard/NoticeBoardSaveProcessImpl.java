package com.datasource.service.process.noticeboard;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.enums.NoticeBoardTypeEnum;
import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.GameBoardWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.pattern.NoticeBoardWriteMapperFactory;
import com.datasource.service.noticeboard.write.NoticeBoardWriteService;
import com.datasource.service.noticeboard.write.impl.FreeBoardWriteService;
import com.datasource.service.noticeboard.write.impl.GameBoardWriteService;
import com.datasource.service.process.ServiceProcessExecute;

import java.util.Objects;

/**
 * <pre>
 *     게시물 유형에 맞춰 저장하는 클래스
 * </pre>
 */
public final class NoticeBoardSaveProcessImpl implements ServiceProcessExecute {
    private NoticeBoardTypeEnum noticeBoardTypeEnum;
    private Posts posts;

    private final GameBoardWriteMapper gameBoardWriteMapper;
    private final FreeBoardWriteMapper freeBoardWriteMapper;

    public NoticeBoardSaveProcessImpl() {
        this(NoticeBoardWriteMapperFactory.getInstance(GameBoardWriteMapper.class)
                , NoticeBoardWriteMapperFactory.getInstance(FreeBoardWriteMapper.class));
    }

    public NoticeBoardSaveProcessImpl(GameBoardWriteMapper gameBoardWriteMapper
            , FreeBoardWriteMapper freeBoardWriteMapper) {
        this.gameBoardWriteMapper = gameBoardWriteMapper;
        this.freeBoardWriteMapper = freeBoardWriteMapper;
    }

    /**
     * <pre>
     *     게시물 저장에 필요한 매개변수 초기화
     * </pre>
     *
     * @param noticeBoardTypeEnum
     * @param posts
     * @return
     */
    public ServiceProcessExecute execute(NoticeBoardTypeEnum noticeBoardTypeEnum, Posts posts) {
        this.noticeBoardTypeEnum = noticeBoardTypeEnum;
        this.posts = posts;

        return this;
    }

    /**
     * <pre>
     *     {@link ServiceProcessExecute#execute()}
     * </pre>
     */
    @Override
    public void execute() {
        Objects.requireNonNull(this.noticeBoardTypeEnum
                , "execute(NoticeBoardTypeEnum noticeBoardTypeEnum, Posts posts) 메서드 선행 호출필요");
        Objects.requireNonNull(this.posts
                , "execute(NoticeBoardTypeEnum noticeBoardTypeEnum, Posts posts) 메서드 선행 호출필요");

        NoticeBoardWriteService noticeBoardWriteService;

        switch (this.noticeBoardTypeEnum) {
            case GAME_BOARD: noticeBoardWriteService = new GameBoardWriteService(this.gameBoardWriteMapper);
            break;
            case FREE_BOARD: noticeBoardWriteService = new FreeBoardWriteService(this.freeBoardWriteMapper);
            break;
            default: throw new IllegalStateException("지원하지 않는 게시물 유형 입니다.");
        }

        noticeBoardWriteService.insertPosts(this.posts);
    }
}

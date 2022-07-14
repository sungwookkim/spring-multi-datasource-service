package com.datasource.repo.mybatis.write.noticeBoard.pattern;

import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardControlWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.FreeBoardWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.GameBoardWriteMapper;
import com.datasource.repo.mybatis.write.noticeBoard.NoticeBoardWriteMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 *     게시물 Write Mapper 팩토리
 * </pre>
 */
@Component
public final class NoticeBoardWriteMapperFactory {
    private static final Map<String, NoticeBoardWriteMapper> noticeBoardWriteMapperMap = new HashMap<>();

    public NoticeBoardWriteMapperFactory(FreeBoardWriteMapper freeBoardWriteMapper
            , GameBoardWriteMapper gameBoardWriteMapper
            , FreeBoardControlWriteMapper freeBoardControlWriteMapper) {
        noticeBoardWriteMapperMap.put(FreeBoardWriteMapper.class.getSimpleName()
                , freeBoardWriteMapper);
        noticeBoardWriteMapperMap.put(GameBoardWriteMapper.class.getSimpleName()
                , gameBoardWriteMapper);
        noticeBoardWriteMapperMap.put(FreeBoardControlWriteMapper.class.getSimpleName()
                , freeBoardControlWriteMapper);
    }

    /**
     * <pre>
     *     게시물 Write Mapper 반환
     * </pre>
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<? extends NoticeBoardWriteMapper> clazz) {
        return Optional.ofNullable((T) noticeBoardWriteMapperMap.get(clazz.getSimpleName()))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 맵퍼 유형 입니다."));
    }
}

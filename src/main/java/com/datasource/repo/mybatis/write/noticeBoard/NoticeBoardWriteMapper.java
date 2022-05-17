package com.datasource.repo.mybatis.write.noticeBoard;

import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.repo.core.noticeBoard.write.NoticeBoardWrite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * {@link NoticeBoardWrite} Mybatis용
 */
@Mapper
public interface NoticeBoardWriteMapper extends NoticeBoardWrite {

    /**
     * <pre>
     *     게시물 유형 제어 정보 저장
     * </pre>
     */
    @Insert("INSERT INTO notice_board_control(reference_seq, notice_board_type)" +
            " VALUES(#{referenceSeq}, #{noticeBoardType})")
    @Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "seq")
    long insertPostsControl(NoticeBoardControl noticeBoardControl);
}

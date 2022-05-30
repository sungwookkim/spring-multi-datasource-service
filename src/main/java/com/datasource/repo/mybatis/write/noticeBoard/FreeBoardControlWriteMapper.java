package com.datasource.repo.mybatis.write.noticeBoard;

import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.repo.core.noticeBoard.write.NoticeBoardWrite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * <pre>
 *     {@link NoticeBoardWrite} Mybatis용 자유 게시판
 * </pre>
 */
@Mapper
public interface FreeBoardControlWriteMapper extends FreeBoardWriteMapper {

    /**
     * <pre>
     *     자유 게시판용 제어 정보 저장
     * </pre>
     */
    @Insert("INSERT INTO free_board_control(reference_seq, notice_board_type)" +
            " VALUES(#{referenceSeq}, #{noticeBoardType})")
    @Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "seq")
    @Override
    long insertPostsControl(NoticeBoardControl noticeBoardControl);
}

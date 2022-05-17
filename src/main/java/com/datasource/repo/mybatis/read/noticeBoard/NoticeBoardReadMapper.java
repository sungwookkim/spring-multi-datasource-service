package com.datasource.repo.mybatis.read.noticeBoard;

import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import com.datasource.repo.core.noticeBoard.read.NoticeBoardRead;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <pre>
 *     {@link NoticeBoardRead} Mybatis용
 * </pre>
 */
@Mapper
public interface NoticeBoardReadMapper extends NoticeBoardRead {

    /**
     * <pre>
     *     게시물 유형 제어 정보 읽기
     *     Seq로 조회
     * </pre>
     */
    @ConstructorArgs({
        @Arg(column = "reference_seq", javaType = Long.class)
        , @Arg(column = "notice_board_type", javaType = String.class)
    })
    @Select("SELECT * FROM notice_board_control WHERE reference_seq = #{referenceSeq}")
    NoticeBoardControl findNoticeBoardControl(long referenceSeq);

    /**
     * <pre>
     *     게시물 유형 제어 정보 읽기
     *     Type으로 조회
     * </pre>
     */
    @ConstructorArgs({
        @Arg(column = "reference_seq", javaType = Long.class)
        , @Arg(column = "notice_board_type", javaType = String.class)
    })
    @Select("SELECT * FROM notice_board_control WHERE notice_board_type = #{noticeBoardType}")
    List<NoticeBoardControl> findNoticeBoardTypeControl(String noticeBoardType);
}

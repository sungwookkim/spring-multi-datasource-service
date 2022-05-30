package com.datasource.repo.mybatis.read.noticeBoard;

import com.datasource.entity.noticeBoard.control.NoticeBoardControl;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <pre>
 *     {@link NoticeBoardReadMapper} Mybatis용 자유 게시판
 * </pre>
 */
@Mapper
public interface FreeBoardControlReadMapper extends FreeBoardReadMapper {

    /**
     * <pre>
     *     자유 게시물용 유형 제어 정보 읽기
     *     Seq로 조회
     * </pre>
     */
    @ConstructorArgs({
            @Arg(column = "reference_seq", javaType = Long.class)
            , @Arg(column = "notice_board_type", javaType = String.class)
    })
    @Select("SELECT * FROM free_board_control WHERE reference_seq = #{referenceSeq}")
    @Override
    NoticeBoardControl findNoticeBoardControl(long referenceSeq);

    /**
     * <pre>
     *     자유 게시물용  유형 제어 정보 읽기
     *     Type으로 조회
     * </pre>
     */
    @ConstructorArgs({
            @Arg(column = "reference_seq", javaType = Long.class)
            , @Arg(column = "notice_board_type", javaType = String.class)
    })
    @Select("SELECT * FROM free_board_control WHERE notice_board_type = #{noticeBoardType}")
    @Override
    List<NoticeBoardControl> findNoticeBoardTypeControl(String noticeBoardType);
}

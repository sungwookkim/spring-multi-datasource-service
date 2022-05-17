package com.datasource.repo.mybatis.read.noticeBoard;

import com.datasource.entity.noticeBoard.Posts;
import com.datasource.repo.core.noticeBoard.read.NoticeBoardRead;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <pre>
 *     {@link NoticeBoardReadMapper} Mybatis용 자유 게시판
 * </pre>
 */
@Mapper
public interface FreeBoardReadMapper extends NoticeBoardReadMapper {

    /**
     * {@link NoticeBoardRead#findPosts(long)} 자유 게시판 글 조회
     */
    @ConstructorArgs({
            @Arg(column = "member_id", javaType = String.class)
            , @Arg(column = "text", javaType = String.class)
    })
    @Select("SELECT * FROM free_board WHERE seq = #{seq}")
    @Override
    Posts findPosts(long seq);
}

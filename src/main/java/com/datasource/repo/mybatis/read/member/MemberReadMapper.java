package com.datasource.repo.mybatis.read.member;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.read.MemberRead;
import org.apache.ibatis.annotations.*;

/**
 * <pre>
 *     회원 조회 인터페이스(Mybatis)
 * </pre>
 */
@Mapper
public interface MemberReadMapper extends MemberRead {

    @ConstructorArgs({
        @Arg(column = "id", javaType = String.class)
        , @Arg(column = "name", javaType = String.class)
        , @Arg(column = "age", javaType = Integer.class)
    })
    @Select(value = "SELECT * FROM member WHERE id = #{id}")
    @Override
    Member findId(String id);
}

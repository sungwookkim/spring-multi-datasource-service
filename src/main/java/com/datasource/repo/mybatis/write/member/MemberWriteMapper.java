package com.datasource.repo.mybatis.write.member;

import com.datasource.entity.member.Member;
import com.datasource.repo.core.member.write.MemberWrite;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * <pre>
 *     회원 저장, 수정, 삭제 인터페이스(Mybatis)
 * </pre>
 */
@Mapper
public interface MemberWriteMapper extends MemberWrite {

    /**
     * {@link MemberWrite#save(Member)}
     */
    @Insert("INSERT INTO member(id, name, age) VALUES (#{id}, #{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "memberSeq", keyColumn = "member_seq")
    @Override
    void save(Member member);
}

package com.datasource;

import com.datasource.entity.member.Member;
import com.datasource.service.member.MemberReadService;
import com.datasource.service.member.MemberWriteService;
import com.datasource.service.member.failExample.MemberFailExampleService;
import com.datasource.service.member.successExample.read.MemberSuccessExampleFactory;
import com.datasource.service.member.successExample.read.MemberSuccessExampleMasterReadService;
import com.datasource.service.member.successExample.read.MemberSuccessExampleSlaveReadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DatasourceApplicationTests {

	@Autowired
	MemberFailExampleService memberNomalFailExampleService;

	@Autowired
	MemberFailExampleService memberPropagationFailExampleService;

	@Autowired
	MemberFailExampleService memberReadOnlyFailExampleService;


	@Autowired
	MemberWriteService memberSuccessExampleSlaveReadWriteService;

	@Autowired
	MemberWriteService memberSuccessExampleMasterReadWriteService;

	@Autowired
	MemberWriteService memberSuccessExampleDecoratorWriteService;


	@Autowired
	MemberReadService memberSuccessExampleSlaveReadService;

	@Autowired
	MemberReadService memberSuccessExampleMasterReadService;

	@Autowired
	MemberReadService memberSuccessExampleDecoratorService;


	@Autowired
	MemberSuccessExampleFactory memberSuccessExampleFactory;

	/**
	 * 아무 속성이 없는 테스트
	 */
	@Test
	void 회원조회_읽기_쓰기가_같은_클래스_내에_있는_실패_예제() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberNomalFailExampleService, id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 회원가입성공_읽기_쓰기가_같은_클래스_내에_있는_실패_예제() {
		Member member = new Member("data_id_4", "data_name_1", 40);

		this.saveMemberTest(this.memberNomalFailExampleService, member);
	}

	@Test
	void 회원가입중복실패_읽기_쓰기가_같은_클래스_내에_있는_실패_예제() {
		Member member = new Member("data_id_1", "data_name_1", 40);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			this.saveMemberTest(this.memberNomalFailExampleService, member);
		});
	}

	/**
	 * readOnly 속성 테스트
	 */
	@Test
	void 회원조회_읽기_쓰기가_같은_클래스_내에_있는_실패_예제_readOnly() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberReadOnlyFailExampleService, id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 회원가입성공_읽기_쓰기가_같은_클래스_내에_있는_실패_예제_readOnly() {
		Member member = new Member("data_id_4", "data_name_1", 40);

		this.saveMemberTest(this.memberReadOnlyFailExampleService, member);
	}

	@Test
	void 회원가입중복실패_읽기_쓰기가_같은_클래스_내에_있는_실패_예제_readOnly() {
		Member member = new Member("data_id_1", "data_name_1", 40);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			this.saveMemberTest(this.memberReadOnlyFailExampleService, member);
		});
	}

	/**
	 * Propagation 테스트
	 */
	@Test
	void 회원조회_읽기_쓰기가_같은_클래스_내에_있는_실패_예제_Propagation() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberPropagationFailExampleService, id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 회원가입성공_읽기_쓰기가_같은_클래스_내에_있는_실패_예제_Propagation() {
		Member member = new Member("data_id_4", "data_name_1", 40);

		this.saveMemberTest(this.memberPropagationFailExampleService, member);
	}

	@Test
	void 회원가입중복실패_읽기_쓰기가_같은_클래스_내에_있는_실패_예제_Propagation() {
		Member member = new Member("data_id_1", "data_name_1", 40);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			this.saveMemberTest(this.memberPropagationFailExampleService, member);
		});
	}

	/**
	 * Read/Write 클래스 분리 테스트
	 */
	@Test
	void 회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_class() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberSuccessExampleSlaveReadService, id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 회원가입성공_읽기_쓰기가_다른_클래스에_있는_성공_예제_class() {
		Member member = new Member("data_id_4", "data_name_1", 40);

		this.saveMemberTest(this.memberSuccessExampleSlaveReadWriteService, member);
	}

	@Test
	void 회원가입중복실패_읽기_쓰기가_다른_클래스에_있는_성공_예제_class() {
		Member member = new Member("data_id_1", "data_name_1", 40);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			this.saveMemberTest(this.memberSuccessExampleSlaveReadWriteService, member);
		});
	}

	/**
	 * Read/Write 클래스 분리 테스트(master 트랜잭션 사용)
	 */
	@Test
	void 회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_master_class() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberSuccessExampleMasterReadService, id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 이름으로_회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_master_class() {
		final String name = "data_name_1";

		Member member = this.getMemberNameTest(this.memberSuccessExampleMasterReadService, name);

		Assertions.assertEquals(name, member.getName());
	}

	@Test
	void 회원가입성공_읽기_쓰기가_다른_클래스에_있는_성공_예제_master_class() {
		Member member = new Member("data_id_4", "data_name_1", 40);

		this.saveMemberTest(this.memberSuccessExampleMasterReadWriteService, member);
	}

	/**
	 * Factory 클래스 이용
	 */
	@Test
	void 회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_factory_master_class() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberSuccessExampleFactory.getInstance(MemberSuccessExampleMasterReadService.class), id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 이름으로_회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_factory_slave_class() {
		final String name = "data_name_1";

		Member member = this.getMemberNameTest(this.memberSuccessExampleFactory.getInstance(MemberSuccessExampleSlaveReadService.class), name);

		Assertions.assertEquals(name, member.getName());
	}

	/**
	 * Read/Write 클래스 분리 테스트(Decorator)
	 */
	@Test
	void 회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_decorator() {
		final String id = "data_id_1";

		Member member = this.getMemberTest(this.memberSuccessExampleDecoratorService, id);

		Assertions.assertEquals(id, member.getId());
	}

	@Test
	void 이름으로_회원조회_읽기_쓰기가_다른_클래스에_있는_성공_예제_decorator() {
		final String name = "data_name_1";

		Assertions.assertThrows(IllegalStateException.class, () -> {
			Member member = this.getMemberNameTest(this.memberSuccessExampleDecoratorService, name);

			Assertions.assertEquals(name, member.getName());
		});
	}

	@Test
	void 회원가입성공_읽기_쓰기가_다른_클래스에_있는_성공_예제_decorator() {
		Member member = new Member("data_id_4", "data_name_1", 40);

		this.saveMemberTest(this.memberSuccessExampleDecoratorWriteService, member);
	}

	/**
	 * private Method
	 */
	private Member getMemberTest(MemberReadService memberReadService, String id) {
		return Optional.ofNullable(memberReadService.findId(id))
				.filter(v -> !"".equals(v.getId()))
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 입니다."));
	}

	private Member getMemberNameTest(MemberReadService memberReadService, String name) {
		return Optional.ofNullable(memberReadService.findName(name))
				.filter(v -> !"".equals(v.getName()))
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 입니다."));
	}

	private void saveMemberTest(MemberWriteService memberWriteService, Member member) {
		memberWriteService.save(member);
	}

}

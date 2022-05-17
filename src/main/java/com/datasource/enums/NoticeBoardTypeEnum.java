package com.datasource.enums;

/**
 * <pre>
 *     게시판 종류 Enum 클래스
 * </pre>
 */
public enum NoticeBoardTypeEnum {
    // 자유 게시판
    FREE_BOARD("FB")
    // 게임 게시판
    , GAME_BOARD("GB");

    private final String type;

    NoticeBoardTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static NoticeBoardTypeEnum find(String code) {
        NoticeBoardTypeEnum[] noticeBoardTypeEnums = NoticeBoardTypeEnum.values();

        for(NoticeBoardTypeEnum noticeBoardTypeEnum : noticeBoardTypeEnums) {
            if(code.equals(noticeBoardTypeEnum.getType())) {
                return noticeBoardTypeEnum;
            }
        }

        throw new IllegalStateException("존재하지 않는 코드 입니다.");
    }
}

package lotto.util.enumerator;

public enum LottoRank {
    FIRST("6개 일치 (2,000,000,000원)"),
    SECOND("5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD("5개 일치 (1,500,000원)"),
    FOURTH("4개 일치 (50,000원)"),
    FIFTH("3개 일치 (5,000원)"),
    NONE("다음 기회에...");

    private final String content;

    LottoRank(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

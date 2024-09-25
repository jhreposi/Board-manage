package com.example.board.dto;

import com.example.board.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
public class SearchRequest {
    private String startDate;
    private String endDate;
    private String category;
    private String keyword;
    private int perPage;        //페이지당 아이템 수
    private String sortBy;      //요청한 정렬기준 컬럼
    private String sortByName;  //변환한 정렬기준 컬럼
    private String sortOrder;   //정렬 옵션 desc asc
    private int currentPage;

    private String board;       //요청한 게시판

    private List<Integer> categoryIds; //db 조회용

    public SearchRequest() {
        this.currentPage = 1;
    }

    // null 이거나 빈값 일시 기본값 설정
    // 정렬 기준 컬럼인 날일, 조회수를 db컬럼에 일치하게 요청하지 않고 date, view로 추상화해서
    // sortColumn을 이용해 db에서 사용 할 컬럼명을 sortByName에 설정한다
    public void defaultSearchValue() {
        this.startDate = defaultStartDate();
        this.endDate = defaultEndDate();
        if (!StringUtil.isNullOrEmpty(this.keyword)) {
            this.keyword.trim();
        }
        this.perPage = defaultPerPage();
        this.sortBy = defaultSortBy();
        this.sortByName = SortColumn.fromSortCriteria(this.sortBy).name();
        this.sortOrder = defaultSortOrder();
    }

    private String defaultStartDate() {
        if (StringUtil.isNullOrEmpty(startDate)) {
            return LocalDate.now().minusMonths(1).toString();
        }
        return startDate;
    }

    private String defaultEndDate() {
        return StringUtil.isNullOrEmpty(endDate) ? LocalDate.now().toString() : endDate;
    }

    private int defaultPerPage() {
        return perPage == 0 ? 10 : perPage;
    }

    private String defaultSortBy() {
        return StringUtil.isNullOrEmpty(sortBy) ? "date" : sortBy;
    }

    private String defaultSortOrder() {
        return StringUtil.isNullOrEmpty(sortOrder) ? "DESC" : sortOrder;
    }

    @Getter
    protected enum SortColumn {
        created_at("date"),
        view_count("view");

        private final String sortCriteria;

        SortColumn(String sortCriteria) {
            this.sortCriteria = sortCriteria;
        }

        public static SortColumn fromSortCriteria(String criteria) {
            for (SortColumn column : values()) {
                if (column.sortCriteria.equals(criteria)) {
                    return column;
                }
            }
            return created_at;
        }
    }
}

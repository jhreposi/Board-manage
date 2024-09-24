package com.example.board.dto;

import com.example.board.util.StringUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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

    public SearchRequest() {
        this.currentPage = 1;
    }

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

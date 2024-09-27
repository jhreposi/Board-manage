package com.example.board.dto;

import com.example.board.util.StringUtil;
import lombok.Getter;
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
    private int perPage;        //한 페이지 아이템 수
    private String sortBy;      //요청한 정렬기준
    private String sortByName;  //정렬 기준을 db에 맞게 변환한 정렬 기준 컬럼
    private String sortOrder;   //정렬 옵션 desc asc
    private int currentPage;

    private int boardType;      //게시판 구별 db조회용

    public SearchRequest() {
        this.currentPage = 1;
    }

    // null 이거나 빈값 일시 기본값 설정
    // 정렬 기준 컬럼인 날일, 조회수를 db컬럼에 일치하게 요청하지 않고 date, view로 추상화해서
    // sortColumn을 이용해 db에서 사용 할 컬럼명을 sortByName에 설정한다
    public void defaultSearchValue() {
        this.startDate = defaultStartDate();
        this.endDate = defaultEndDate();
        this.keyword = defaultKeyword();
        this.perPage = defaultPerPage();
        this.sortBy = defaultSortBy();
        this.sortByName = setSortByNameFrom();
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

    private String defaultKeyword() {
        return StringUtil.isNullOrEmpty(keyword) ? keyword : keyword.trim();
    }

    private int defaultPerPage() {
        return perPage == 0 ? 10 : perPage;
    }

    private String defaultSortBy() {
        return StringUtil.isNullOrEmpty(sortBy) ? "date" : sortBy;
    }

    private String setSortByNameFrom() {
        return SortColumn.fromSortCriteria(sortBy).name();
    }

    private String defaultSortOrder() {
        return StringUtil.isNullOrEmpty(sortOrder) ? "DESC" : sortOrder;
    }

    @Getter
    private enum SortColumn {
        created_at("date"),
        view_count("view");

        private final String sortCriteria;

        SortColumn(String sortCriteria) {
            this.sortCriteria = sortCriteria;
        }

        private static SortColumn fromSortCriteria(String criteria) {
            for (SortColumn column : values()) {
                if (column.sortCriteria.equals(criteria)) {
                    return column;
                }
            }
            return created_at;
        }
    }
}

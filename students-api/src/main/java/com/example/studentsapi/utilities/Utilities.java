package com.example.studentsapi.utilities;

import org.springframework.data.domain.Pageable;

public class Utilities {

    public static int getCurrentPage(Pageable pageable) {
        int page = pageable.getPageNumber();
        if (pageable.getPageNumber() != 0) {
            page -= 1;
        }
        return page;
    }

    private Utilities() {}
}

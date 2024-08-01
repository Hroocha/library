package com.hibernate.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LibraryController {

    @Autowired
    LibraryService libraryService;



}

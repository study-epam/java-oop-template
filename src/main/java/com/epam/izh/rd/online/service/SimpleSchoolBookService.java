package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false;
        } else {
            schoolBookBookRepository.save(book);
            return true;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
//        return new Book[0];
    }

    /**
     * Метод должен находить количество сохраненных книг по конкретному имени книги.
     */
    @Override
    public int getNumberOfBooksByName(String name) {
        if(schoolBookBookRepository.findByName(name) == null){
            return 0;
        }
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
//        return 0;
    }

    /**
     * Метод должен возвращать автора книги по названию книги.
     *
     * То есть приждется сходить и в репозиторий с книгами и в сервис авторов.
     *
     * Если такой книги не найдено, метод должен вернуть null.
     */
    @Override
    public Author findAuthorByBookName(String name) {
        if (schoolBookBookRepository.findByName(name).length == 0) {
            return null;
        } else {
            return  authorService.findByFullName(schoolBookBookRepository.findByName(name)[0].getAuthorName(),
                    schoolBookBookRepository.findByName(name)[0].getAuthorLastName());
        }
    }
}

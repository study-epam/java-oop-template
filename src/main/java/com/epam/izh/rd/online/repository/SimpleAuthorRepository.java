package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];



    //TODO: check save()
    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null || authors == null) {
            return false;
        } else {
            if (authors.length == 0) {
                authors = new Author[1];
                authors[0] = author;
            } else {
                authors = Arrays.copyOf(authors, authors.length + 1);
                authors[authors.length - 1] = author;

            }
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastName)) {
                return author;
            }
        }
        return null;
    }

    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     */
    @Override
    public boolean remove(Author author) {
//        if (findByFullName(author.getName(), author.getLastName()) == null || authors == null || authors.length == 0 || checkNull(author)) {
//            return false;
//        } else {
//
//            return true;
//        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }

}

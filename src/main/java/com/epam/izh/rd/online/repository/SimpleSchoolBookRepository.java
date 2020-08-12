package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    @Override
    public boolean save(SchoolBook book) {
//        if(schoolBooks == null){
//            return false;
//        } else {
            if(schoolBooks == null){
                schoolBooks = new SchoolBook[1];
                schoolBooks[0] = book;
            } else {
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length +1);
                schoolBooks[schoolBooks.length - 1] = book;
            }
            return true;
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */
    @Override
    public SchoolBook[] findByName(String name) {
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equals(name)) {
                return schoolBooks;
            }
        }
        return null;
//        return new SchoolBook[0];
    }

    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     */
    @Override
    public boolean removeByName(String name) {
        SchoolBook [] schoolBookTmp = new SchoolBook[schoolBooks.length - 1];
        if(findByName(name) == null || schoolBooks == null || schoolBooks.length == 0){
            return false;
        }else {
            for(int i = 0; i < schoolBooks.length; i++){
                if(schoolBooks[i].equals(name)){
                    System.arraycopy(schoolBooks, 0, schoolBookTmp, 0, i);
                    System.arraycopy(schoolBooks, i + 1, schoolBookTmp, i,schoolBooks.length - i - 1);

                }
            }
            return true;
        }
    }


    @Override
    public int count() {
        return schoolBooks.length;
    }
}

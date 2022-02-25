package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher1 = new Publisher("Pierson", "24400 Dundee rd", "Wheeling", "IL", "60090");

        publisherRepository.save(publisher1);

        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "222333112323");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(publisher1);
        publisher1.getBooks().add(book1);

        authorRepository.save(eric);
        bookRepository.save(book1);
        publisherRepository.save(publisher1);

        Author rJohnson = new Author("Rod", "Johnson");
        Book noEjb = new Book("EBJ", "123213123123");
        noEjb.setPublisher(publisher1);
        publisher1.getBooks().add(noEjb);

        rJohnson.getBooks().add(noEjb);
        noEjb.getAuthors().add(rJohnson);

        authorRepository.save(rJohnson);
        bookRepository.save(noEjb);
        publisherRepository.save(publisher1);

        System.out.println("***************Started in BootStrap ********************");
        System.out.println("***************Number of books: " + bookRepository.count());
        System.out.println("***************Number of Authors: " + authorRepository.count());
        System.out.println("***************Number of Publisher: " + publisherRepository.count());
        System.out.println("***************Number of Books by a Publisher: " + publisher1.getBooks().size());

    }
}

package com.vaadin.example.crm.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vaadin.example.crm.HasLogger;
import com.vaadin.example.crm.entity.Company;
import com.vaadin.example.crm.entity.Contact;
import com.vaadin.example.crm.entity.Country;
import com.vaadin.example.crm.entity.Deal;
import com.vaadin.example.crm.entity.DealState;
import com.vaadin.example.crm.entity.Note;
import com.vaadin.example.crm.entity.Role;
import com.vaadin.example.crm.entity.User;

import de.svenjacobs.loremipsum.LoremIpsum;

@Component
public class DataGenerator implements HasLogger {

    private static final String[] FIRST_NAME = new String[] { "Ori", "Amanda", "Octavia", "Laurel", "Lael", "Delilah", "Jason", "Skyler", "Arsenio", "Haley",
            "Lionel", "Sylvia", "Jessica", "Lester", "Ferdinand", "Elaine", "Griffin", "Kerry", "Dominique" };
    private static final String[] LAST_NAME = new String[] { "Carter", "Castro", "Rich", "Irwin", "Moore", "Hendricks", "Huber", "Patton", "Wilkinson",
            "Thornton", "Nunez", "Macias", "Gallegos", "Blevins", "Mejia", "Pickett", "Whitney", "Farmer", "Henry", "Chen", "Macias", "Rowland", "Pierce",
            "Cortez", "Noble", "Howard", "Nixon", "Mcbride", "Leblanc", "Russell", "Carver", "Benton", "Maldonado", "Lyons" };

    private final Random random = new Random(1L);

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private DealStateRepository dealStateRepository;
    private CompanyRepository companyRepository;
    private ContactRepository contactRepository;
    private DealRepository dealRepository;

    @Autowired
    public DataGenerator(UserRepository userRepository, DealStateRepository dealStateRepository, CompanyRepository companyRepository,
            ContactRepository contactRepository, DealRepository dealRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dealStateRepository = dealStateRepository;
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
        this.dealRepository = dealRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void loadData() {
        if (userRepository.count() != 0L) {
            getLogger().info("Using existing database");
            return;
        }

        getLogger().info("Generating demo data");

        createDeletableUsers();

        createDealStates();

        createCompanies();

        createContacts();

        createDeals();

        getLogger().info("Demo data done.");

    }

    private void createDeals() {
        List<DealState> dealStates = dealStateRepository.findAll();
        List<Contact> contacts = contactRepository.findAll();
        List<User> kams = userRepository.findAll();

        LoremIpsum ipsum = new LoremIpsum();

        for (int i = 0; i < 100; i++) {

            Deal deal = new Deal();

            deal.setName("Deal " + i);
            deal.setAmount((double) (random.nextInt(2048) * 1000));

            deal.setContact(contacts.get(random.nextInt(contacts.size())));
            deal.setCompany(deal.getContact().getCompany());

            deal.setDealOwner(kams.get(random.nextInt(kams.size())));

            deal.setState(dealStates.get(random.nextInt(dealStates.size())));
            if (deal.getState().getName().startsWith("Closed")) {

                LocalDate randomDate = LocalDate.now().minusDays(random.nextInt(120));
                deal.setClosed(java.sql.Date.valueOf(randomDate));
            }

            Deal savedDeal = dealRepository.save(deal);

            int numnotes = random.nextInt(6) - 1;
            if (numnotes > 0) {

                ArrayList<Note> notes = new ArrayList<>();
                savedDeal.setNotes(notes);

                for (int j = 0; j < numnotes; j++) {
                    User author = kams.get(random.nextInt(kams.size()));
                    String content = ipsum.getWords(random.nextInt(50) + 2);

                    LocalDate randomDate = LocalDate.now().minusDays(random.nextInt(240));
                    Date added = java.sql.Date.valueOf(randomDate);

                    notes.add(new Note(content, added, author));
                }
                dealRepository.save(savedDeal);
            }

        }

    }

    private void createContacts() {

        List<Company> companies = companyRepository.findAll();
        List<User> kams = userRepository.findAll();

        LoremIpsum ipsum = new LoremIpsum();

        for (int i = 0; i < 50; i++) {

            Company company = companies.get(random.nextInt(companies.size()));

            String first = getRandom(FIRST_NAME);
            String last = getRandom(LAST_NAME);
            String name = first + " " + last;
            String email = first + "." + last + "@" + company.getName().split(" ")[0] + ".com";
            email = email.toLowerCase();

            if (!contactRepository.findByNameIgnoreCase(name).isEmpty()) {
                continue; // rare, but might happen
            }

            Contact savedContact = contactRepository.save(new Contact(name, email, company));

            int numnotes = random.nextInt(6) - 1;
            if (numnotes > 0) {

                ArrayList<Note> notes = new ArrayList<>();
                savedContact.setNotes(notes);

                for (int j = 0; j < numnotes; j++) {
                    User author = kams.get(random.nextInt(kams.size()));
                    String content = ipsum.getWords(random.nextInt(50) + 2);

                    LocalDate randomDate = LocalDate.now().minusDays(random.nextInt(240));
                    Date added = java.sql.Date.valueOf(randomDate);

                    notes.add(new Note(content, added, author));
                }
                contactRepository.save(savedContact);
            }
        }
    }

    private void createCompanies() {
        companyRepository.save(new Company("ACME Inc.", null, Country.USA, "California", userRepository.findByEmailIgnoreCase("peter@vaadin.com")));
        companyRepository.save(new Company("Alibaba", "Yes, the big one.", Country.CHINA, null, userRepository.findByEmailIgnoreCase("peter@vaadin.com")));
        companyRepository.save(new Company("Vaadin", "We're here too!", Country.FINLAND, null, userRepository.findByEmailIgnoreCase("mary@vaadin.com")));
    }

    private void createDealStates() {
        dealStateRepository.save(new DealState("New", true, 1));
        dealStateRepository.save(new DealState("Negotiating", true, 2));
        dealStateRepository.save(new DealState("Offer", true, 3));
        dealStateRepository.save(new DealState("Closed Won", true, 4));
        dealStateRepository.save(new DealState("Closed Lost", true, 5));
    }

    private <T> T getRandom(T[] array) {
        return array[random.nextInt(array.length)];
    }

    private void createDeletableUsers() {
        userRepository.save(createUser("peter@vaadin.com", "Peter", passwordEncoder.encode("peter"), Role.USER));
        userRepository.save(createUser("mary@vaadin.com", "Mary", passwordEncoder.encode("mary"), Role.ADMIN));
    }

    private User createUser(String email, String name, String passwordHash, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPasswordHash(passwordHash);
        user.setRole(role);
        return user;
    }
}

package app.persistence.repositories;

import org.springframework.stereotype.Repository;

import app.persistence.entities.Transaction;
import lib.persistence.repositories.BaseRepository;

@Repository
public class TransactionRepository extends BaseRepository<Transaction> {

}

package ir.pep.account.core.transaction;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;


import ir.pep.account.core.card.Card;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static ir.pep.account.querydsl.QNiaCard.niaCard;
import static ir.pep.account.querydsl.QNiaTransaction.niaTransaction;

@Singleton
public class TransactionRepositoryImpl implements TransactionRepository {
    public static final QBean<Transaction> Q_BEAN = Projections.bean(
            Transaction.class,
            niaTransaction.id,
            Projections.bean(Card.class,
                    niaCard.id,
                    niaCard.cardNumber,
                    niaCard.enabled,
                    niaCard.createdAt
            ).as("card"),
            niaTransaction.amount,
            niaTransaction.type,
            niaTransaction.time,
            niaTransaction.status,
            niaTransaction.enabled,
            niaTransaction.createdAt
    );

    public static final QBean<TransactionSummeryDto> SUMMARY_BEAN = Projections.bean(
            TransactionSummeryDto.class,
            niaCard.cardNumber.as("cardNumber"),
            niaTransaction.amount.as("amount"),
            niaTransaction.time.as("time"),
            niaTransaction.type.as("type")
    );

    private final SQLQueryFactory queryFactory;

    public TransactionRepositoryImpl(DataSource dataSource) {
        Configuration configuration = new Configuration(PostgreSQLTemplates.builder().build());
        this.queryFactory = new SQLQueryFactory(configuration, dataSource);
    }

    @Transactional
    @Override
    public void insert(Transaction transaction) {
        queryFactory.insert(niaTransaction)
                .set(niaTransaction.id, transaction.getId())
                .set(niaTransaction.cardId, transaction.getCard().getId())
                .set(niaTransaction.amount, transaction.getAmount())
                .set(niaTransaction.type, transaction.getType())
                .set(niaTransaction.time, transaction.getTime())
                .set(niaTransaction.status, transaction.getStatus())
                .set(niaTransaction.enabled, transaction.isEnabled())
                .set(niaTransaction.createdAt, transaction.getCreatedAt())
                .execute();

    }


    @Override
    public Optional<Transaction> get(String id) {
        Transaction transaction = queryFactory.select(Q_BEAN)
                .from(niaTransaction)
                .join(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaTransaction.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return queryFactory.select(Q_BEAN)
                .from(niaTransaction)
                .join(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }

    @Override
    public List<Transaction> getSuccessfulTransactions(String cardId) {
        return queryFactory
                .select(Q_BEAN)
                .from(niaTransaction)
                .join(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(
                        niaTransaction.cardId.eq(cardId),
                        niaTransaction.status.eq("SUCCESS"),
                        niaTransaction.enabled.isTrue()
                )
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }


    @Override
    public List<Transaction> getCardId(String cardId) {
        return queryFactory
                .select(Q_BEAN)
                .from(niaTransaction)
                .join(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaTransaction.cardId.eq(cardId))
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }

    @Override
    public List<Transaction> getCardNumber(String cardNumber) {
        return queryFactory
                .select(Q_BEAN)
                .from(niaTransaction)
                .join(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaCard.cardNumber.eq(cardNumber))
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }

    @Override
    public List<TransactionSummeryDto> getTransactionSummeries(String cardNumber) {
        return queryFactory
                .select(SUMMARY_BEAN)
                .from(niaTransaction)
                .join(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaCard.cardNumber.eq(cardNumber))
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }
}


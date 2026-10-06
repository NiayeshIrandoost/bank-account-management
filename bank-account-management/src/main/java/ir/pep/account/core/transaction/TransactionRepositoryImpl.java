package ir.pep.account.core.transaction;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.SQLQueryFactory;


import ir.pep.account.core.card.Card;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static ir.pep.account.querydsl.model.QNiaCard.niaCard;
import static ir.pep.account.querydsl.model.QNiaTransaction.niaTransaction;

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
            niaTransaction.time,
            niaTransaction.status,
            niaTransaction.enabled,
            niaTransaction.createdAt
    );

    private final SQLQueryFactory queryFactory;

    public TransactionRepositoryImpl(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Transactional
    @Override
    public void insert(Transaction transaction) {
        queryFactory.insert(niaTransaction)
                .set(niaTransaction.id, transaction.getId())
                .set(niaTransaction.cardId, transaction.getCard().getId())
                .set(niaTransaction.amount, transaction.getAmount())
                .set(niaTransaction.status, transaction.getStatus())
                .set(niaTransaction.enabled, transaction.isEnabled())
                .set(niaTransaction.createdAt, transaction.getCreatedAt())
                .execute();

    }


    @Override
    public Optional<Transaction> get(String id) {
        Transaction transaction = queryFactory.select(Q_BEAN)
                .from(niaTransaction)
                .leftJoin(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaTransaction.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(transaction);
    }

    @Override
    public List<Transaction> getCardId(String cardId) {
        return queryFactory
                .select(Q_BEAN)
                .from(niaTransaction)
                .leftJoin(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaTransaction.cardId.eq(cardId))
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }

    @Override
    public List<Transaction> getCardNumber(String cardNumber) {
        return queryFactory
                .select(Q_BEAN)
                .from(niaTransaction)
                .innerJoin(niaCard).on(niaTransaction.cardId.eq(niaCard.id))
                .where(niaCard.cardNumber.eq(cardNumber))
                .orderBy(niaTransaction.time.desc())
                .fetch();
    }
}


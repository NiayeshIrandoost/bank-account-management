package ir.pep.account.core.card;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static ir.pep.account.querydsl.model.QNiaCard.niaCard;

@Singleton
public class CardRepositoryImpl implements CardRepository {
    private final SQLQueryFactory queryFactory;
    public static final QBean<Card> Q_BEAN = Projections.bean(
            Card.class,
            niaCard.id,
            niaCard.cardNumber,
            niaCard.enabled,
            niaCard.createdAt
    );


    public CardRepositoryImpl(DataSource dataSource) {
        Configuration configuration = new Configuration(PostgreSQLTemplates.builder().build());
        this.queryFactory = new SQLQueryFactory(configuration, dataSource);
    }

    @Override
    @Transactional
    public void insert(Card card) {
        queryFactory.insert(niaCard)
                .set(niaCard.id, card.getId())
                .set(niaCard.cardNumber, card.getCardNumber())
                .set(niaCard.enabled, card.isEnabled())
                .set(niaCard.createdAt, card.getCreatedAt())
                .execute();

    }

    @Override
    public Optional<Card> getId(String id) {
        Card card = queryFactory.select(Q_BEAN)
                .from(niaCard)
                .where(niaCard.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(card);
    }


    @Override
    public List<Card> get(List<String> ids) {
        return queryFactory.select(Q_BEAN)
                .from(niaCard)
                .where(niaCard.id.in(ids))
                .fetch();
    }

    @Override
    public List<Card> getAll() {
        return queryFactory.select(Q_BEAN)
                .from(niaCard)
                .orderBy(niaCard.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Card> getCards(List<String> cardNumbers) {
        return queryFactory.select(Q_BEAN)
                .from(niaCard)
                .where(niaCard.cardNumber.in(cardNumbers))
                .fetch();
    }

    @Override
    @Transactional
    public void update(Card card) {
        queryFactory.update(niaCard)
                .set(niaCard.cardNumber, card.getCardNumber())
                .set(niaCard.enabled, card.isEnabled())
                .where(niaCard.id.eq(card.getId()))
                .execute();

    }


    @Override
    @Transactional
    public void updateStatus(String id, boolean enabled) {
        queryFactory.update(niaCard)
                .set(niaCard.enabled, enabled)
                .where(niaCard.id.eq(id))
                .execute();

    }

    @Override
    @Transactional
    public void delete(String id) {
        queryFactory.delete(niaCard)
                .where(niaCard.id.eq(id))
                .execute();

    }

    @Override
    @Transactional
    public void updateCardNumber(String id, String cardNumber) {
        queryFactory.update(niaCard)
                .set(niaCard.cardNumber, cardNumber)
                .where(niaCard.id.eq(id))
                .execute();

    }

}

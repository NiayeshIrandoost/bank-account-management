package ir.pep.account.querydsl.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QNiaCard is a Querydsl query type for QNiaCard
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QNiaCard extends com.querydsl.sql.RelationalPathBase<QNiaCard> {

    private static final long serialVersionUID = -1830491894;

    public static final QNiaCard niaCard = new QNiaCard("nia_card");

    public final StringPath cardNumber = createString("cardNumber");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath id = createString("id");

    public final com.querydsl.sql.PrimaryKey<QNiaCard> niaCardPk = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<QNiaTransaction> _niaTransactionCardFk = createInvForeignKey(id, "card_id");

    public QNiaCard(String variable) {
        super(QNiaCard.class, forVariable(variable), "public", "nia_card");
        addMetadata();
    }

    public QNiaCard(String variable, String schema, String table) {
        super(QNiaCard.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QNiaCard(String variable, String schema) {
        super(QNiaCard.class, forVariable(variable), schema, "nia_card");
        addMetadata();
    }

    public QNiaCard(Path<? extends QNiaCard> path) {
        super(path.getType(), path.getMetadata(), "public", "nia_card");
        addMetadata();
    }

    public QNiaCard(PathMetadata metadata) {
        super(QNiaCard.class, metadata, "public", "nia_card");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(cardNumber, ColumnMetadata.named("card_number").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(4).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
        addMetadata(enabled, ColumnMetadata.named("enabled").withIndex(3).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.VARCHAR).withSize(50).notNull());
    }

}


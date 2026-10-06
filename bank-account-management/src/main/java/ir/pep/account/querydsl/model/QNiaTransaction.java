package ir.pep.account.querydsl.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QNiaTransaction is a Querydsl query type for QNiaTransaction
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QNiaTransaction extends com.querydsl.sql.RelationalPathBase<QNiaTransaction> {

    private static final long serialVersionUID = -433350748;

    public static final QNiaTransaction niaTransaction = new QNiaTransaction("nia_transaction");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final StringPath cardId = createString("cardId");

    public final DateTimePath<java.time.Instant> createdAt = createDateTime("createdAt", java.time.Instant.class);

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath id = createString("id");

    public final StringPath status = createString("status");

    public final DateTimePath<java.time.Instant> time = createDateTime("time", java.time.Instant.class);

    public final com.querydsl.sql.PrimaryKey<QNiaTransaction> niaTransactionPk = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<QNiaCard> niaTransactionCardFk = createForeignKey(cardId, "id");

    public QNiaTransaction(String variable) {
        super(QNiaTransaction.class, forVariable(variable), "public", "nia_transaction");
        addMetadata();
    }

    public QNiaTransaction(String variable, String schema, String table) {
        super(QNiaTransaction.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QNiaTransaction(String variable, String schema) {
        super(QNiaTransaction.class, forVariable(variable), schema, "nia_transaction");
        addMetadata();
    }

    public QNiaTransaction(Path<? extends QNiaTransaction> path) {
        super(path.getType(), path.getMetadata(), "public", "nia_transaction");
        addMetadata();
    }

    public QNiaTransaction(PathMetadata metadata) {
        super(QNiaTransaction.class, metadata, "public", "nia_transaction");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(amount, ColumnMetadata.named("amount").withIndex(3).ofType(Types.NUMERIC).withSize(20).withDigits(2));
        addMetadata(cardId, ColumnMetadata.named("card_id").withIndex(2).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(7).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
        addMetadata(enabled, ColumnMetadata.named("enabled").withIndex(6).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(status, ColumnMetadata.named("status").withIndex(5).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(time, ColumnMetadata.named("time").withIndex(4).ofType(Types.TIMESTAMP).withSize(29).withDigits(6));
    }

}


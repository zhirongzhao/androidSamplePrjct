package com.sample.androidsampleprjct.dao.lottery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.sample.androidsampleprjct.util.db.DaoSession;
import com.sample.androidsampleprjct.vo.SSQ;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LT_SSQ".
*/
public class SSQDao extends AbstractDao<SSQ, Long> {

    public static final String TABLENAME = "LT_SSQ";

    /**
     * Properties of entity SSQ.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Red1 = new Property(1, int.class, "red1", false, "RED1");
        public final static Property Red2 = new Property(2, int.class, "red2", false, "RED2");
        public final static Property Red3 = new Property(3, int.class, "red3", false, "RED3");
        public final static Property Red4 = new Property(4, int.class, "red4", false, "RED4");
        public final static Property Red5 = new Property(5, int.class, "red5", false, "RED5");
        public final static Property Red6 = new Property(6, int.class, "red6", false, "RED6");
        public final static Property Red7 = new Property(7, int.class, "red7", false, "RED7");
        public final static Property Red8 = new Property(8, int.class, "red8", false, "RED8");
        public final static Property Blu = new Property(9, int.class, "blu", false, "BLU");
        public final static Property Expect = new Property(10, String.class, "expect", false, "EXPECT");
        public final static Property RegDate = new Property(11, String.class, "regDate", false, "REG_DATE");
        public final static Property IsWin = new Property(12, String.class, "isWin", false, "IS_WIN");
    };


    public SSQDao(DaoConfig config) {
        super(config);
    }
    
    public SSQDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LT_SSQ\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"RED1\" INTEGER NOT NULL ," + // 1: red1
                "\"RED2\" INTEGER NOT NULL ," + // 2: red2
                "\"RED3\" INTEGER NOT NULL ," + // 3: red3
                "\"RED4\" INTEGER NOT NULL ," + // 4: red4
                "\"RED5\" INTEGER NOT NULL ," + // 5: red5
                "\"RED6\" INTEGER NOT NULL ," + // 6: red6
                "\"RED7\" INTEGER NOT NULL ," + // 7: red7
                "\"RED8\" INTEGER NOT NULL ," + // 8: red8
                "\"BLU\" INTEGER NOT NULL ," + // 9: blu
                "\"EXPECT\" TEXT NOT NULL ," + // 10: expect
                "\"REG_DATE\" TEXT," + // 11: regDate
                "\"IS_WIN\" TEXT);"); // 12: isWin
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LT_SSQ\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SSQ entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRed1());
        stmt.bindLong(3, entity.getRed2());
        stmt.bindLong(4, entity.getRed3());
        stmt.bindLong(5, entity.getRed4());
        stmt.bindLong(6, entity.getRed5());
        stmt.bindLong(7, entity.getRed6());
        stmt.bindLong(8, entity.getRed7());
        stmt.bindLong(9, entity.getRed8());
        stmt.bindLong(10, entity.getBlu());
        stmt.bindString(11, entity.getExpect());
 
        String regDate = entity.getRegDate();
        if (regDate != null) {
            stmt.bindString(12, regDate);
        }
 
        String isWin = entity.getIsWin();
        if (isWin != null) {
            stmt.bindString(13, isWin);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SSQ readEntity(Cursor cursor, int offset) {
        SSQ entity = new SSQ( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // red1
            cursor.getInt(offset + 2), // red2
            cursor.getInt(offset + 3), // red3
            cursor.getInt(offset + 4), // red4
            cursor.getInt(offset + 5), // red5
            cursor.getInt(offset + 6), // red6
            cursor.getInt(offset + 7), // red7
            cursor.getInt(offset + 8), // red8
            cursor.getInt(offset + 9), // blu
            cursor.getString(offset + 10), // expect
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // regDate
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // isWin
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SSQ entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRed1(cursor.getInt(offset + 1));
        entity.setRed2(cursor.getInt(offset + 2));
        entity.setRed3(cursor.getInt(offset + 3));
        entity.setRed4(cursor.getInt(offset + 4));
        entity.setRed5(cursor.getInt(offset + 5));
        entity.setRed6(cursor.getInt(offset + 6));
        entity.setRed7(cursor.getInt(offset + 7));
        entity.setRed8(cursor.getInt(offset + 8));
        entity.setBlu(cursor.getInt(offset + 9));
        entity.setExpect(cursor.getString(offset + 10));
        entity.setRegDate(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setIsWin(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SSQ entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SSQ entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
